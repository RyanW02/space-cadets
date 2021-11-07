package chat.server;

import chat.protocol.Packet;
import chat.protocol.impl.DisconnectPacket;
import chat.protocol.impl.KeepAliveSynPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager {

  // username -> client
  private final Map<String, Client> clients = Collections.synchronizedMap(new HashMap<>());
  private Thread keepAliveThread = null;

  public boolean exists(String username) {
    return clients.containsKey(username);
  }

  public void addClient(Client client) {
    clients.put(client.getUsername(), client);
  }

  public void removeClient(Client client) {
    clients.remove(client.getUsername());
  }

  public void removeClient(String username) {
    clients.remove(username);
  }

  public void publishMessage(String username, String message) {
    List<Client> toDisconnect = new ArrayList<>();

    clients.values().forEach(client -> {
      try {
        client.publishMessage(username, message);
      } catch (IOException e) {
        toDisconnect.add(client);
      }
    });

    toDisconnect.forEach(client -> disconnect(client, "An exception occurred"));
  }

  public void publishJoin(String username) {
    List<Client> toDisconnect = new ArrayList<>();

    clients.values().forEach(client -> {
      try {
        client.publishJoin(username);
      } catch (IOException e) {
        toDisconnect.add(client);
      }
    });

    toDisconnect.forEach(client -> disconnect(client, "An exception occurred"));
  }

  public void publishLeave(String username) {
    List<Client> toDisconnect = new ArrayList<>();

    clients.values().forEach(client -> {
      try {
        client.publishLeave(username);
      } catch (IOException e) {
        toDisconnect.add(client);
      }
    });

    toDisconnect.forEach(client -> disconnect(client, "An exception occurred"));
  }

  public void disconnect(Client client, String reason) {
    try {
      Packet packet = new DisconnectPacket(reason);
      client.write(packet);
      client.getSocket().close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      removeClient(client);
      publishLeave(client.getUsername());

      if (keepAliveThread != null) {
        keepAliveThread.interrupt();
      }
    }
  }

  public void startKeepAliveLoop(Client client) {
    keepAliveThread = new Thread(() -> {
      while (true) {
        long now = System.currentTimeMillis();

        try {
          client.write(new KeepAliveSynPacket(now));
        } catch (IOException e) {
          keepAliveThread = null;
          disconnect(client, e.getMessage());
          return;
        }

        try {
          Thread.sleep(Constants.KEEP_ALIVE_TIMEOUT.toMillis());
        } catch (InterruptedException e) { // Disconnected
          return;
        }

        if (client.getLastAck() != now) {
          keepAliveThread = null;
          disconnect(client, "Haven't received keepalive");
          return;
        }
      }
    });
  }
}
