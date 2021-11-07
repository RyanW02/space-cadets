package chat.server;

import chat.protocol.Packet;
import chat.protocol.PacketSource;
import chat.protocol.impl.DisconnectPacket;
import chat.protocol.impl.HelloPacket;
import chat.protocol.impl.KeepAliveAckPacket;
import chat.protocol.impl.MessageSendPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable {
  private static Gson gson = new GsonBuilder().create();

  private final short port;

  private ClientManager clientManager = new ClientManager();

  public Server(short port) {
    this.port = port;
  }

  @Override
  public void run() {
    try {
      ServerSocket sock = new ServerSocket(port);
      while (true) {
        Socket clientSock = sock.accept();
        System.out.printf("Accepted connection from %s\n", clientSock.getInetAddress().getHostAddress());
        Thread t = new Thread(() -> handleConnection(clientSock));
        t.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleConnection(Socket sock) {
    Client client = null;
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      client = readHello(sock, br);

      if (client == null) {
        return;
      }

      clientManager.addClient(client);
      clientManager.startKeepAliveLoop(client);
      clientManager.publishJoin(client.getUsername());

      String raw;
      while ((raw = br.readLine()) != null) {
        Packet packet = gson.fromJson(raw, Packet.class);
        handlePacket(client, packet);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (client != null) {
        clientManager.removeClient(client);
        clientManager.publishLeave(client.getUsername());
      }
    }
  }

  /**
   * If returns null, client did not respond in time
   */
  private Client readHello(Socket sock, BufferedReader br) throws IOException {
    try {
      String raw = br.readLine();
      HelloPacket hello = gson.fromJson(raw, HelloPacket.class);

      // Check if name is already registered
      if (clientManager.exists(hello.getUsername())) {
        write(sock, new DisconnectPacket("Username already taken"));
        sock.close();
        return null;
      }

      return new Client(hello.getUsername(), sock);
    } catch (JsonSyntaxException e) {
      e.printStackTrace(); // TODO: Remove line

      write(sock, new DisconnectPacket("Invalid hello packet"));
      sock.close();

      return null;
    }
  }

  private void handlePacket(Client client, Packet packet) {
    if (packet.getPacketType().getSource() == PacketSource.SERVER) {
      disconnect(client, "Client sent serverside packet");
      return;
    }

    switch (packet.getPacketType()) {
      case KEEP_ALIVE_ACK -> {
        client.setLastAck(((KeepAliveAckPacket) packet).getSeq());
        break;
      }
      case HELLO -> {
        disconnect(client, "Received duplicate HELLO packet");
        break;
      }
      case MESSAGE_SEND -> {
        clientManager.publishMessage(client.getUsername(), ((MessageSendPacket) packet).getMessage());
        break;
      }
    }
  }

  static void write(Socket sock, Packet packet) throws IOException {
    OutputStream os = sock.getOutputStream();
    os.write(gson.toJson(packet).getBytes());
    os.write('\n');
    os.flush();
  }

  public void disconnect(Client client, String reason) {
    clientManager.disconnect(client, reason);
  }
}
