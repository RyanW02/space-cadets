package chat.client;

import chat.protocol.Packet;
import chat.protocol.PacketSource;
import chat.protocol.impl.DisconnectPacket;
import chat.protocol.impl.HelloPacket;
import chat.protocol.impl.JoinPacket;
import chat.protocol.impl.KeepAliveSynPacket;
import chat.protocol.impl.LeavePacket;
import chat.protocol.impl.MessageRecvPacket;
import chat.protocol.impl.MessageSendPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {
  private final String username;

  private Gson gson = new Gson();
  private Socket sock = null;

  public Client(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void connect(String host, short port) throws IOException {
    sock = new Socket(host, port);
  }

  public void login() throws IOException {
    write(new HelloPacket(username));
  }

  public void readMessages() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

    try {
      String raw;
      while ((raw = br.readLine()) != null && !sock.isClosed()) {
        Packet packet = gson.fromJson(raw, Packet.class);
        handlePacket(packet);
      }
    } catch (SocketException ex) {
      // We can ignore sockets being closed
    }
  }

  public void sendMessage(String message) throws IOException {
    write(new MessageSendPacket(message));
  }

  private void handlePacket(Packet packet) throws IOException {
    if (packet.getPacketType().getSource() == PacketSource.CLIENT) {
      System.err.println("Received client-side packet from server");
      disconnect();
      return;
    }

    switch (packet.getPacketType()) {
      case KEEP_ALIVE_SYN -> {
        responseKeepAlive(((KeepAliveSynPacket) packet).getSeq());
        break;
      }
      case DISCONNECT -> {
        String reason = ((DisconnectPacket) packet).getReason();
        System.out.printf("Disconnected: %s%n", reason);
        disconnect();
        break;
      }
      case JOIN -> {
        String username = ((JoinPacket) packet).getUsername();
        System.out.printf("[+] %s%n", username);
        break;
      }
      case LEAVE -> {
        String username = ((LeavePacket) packet).getUsername();
        System.out.printf("[-] %s%n", username);
        break;
      }
      case MESSAGE_RECV -> {
        MessageRecvPacket data = (MessageRecvPacket) packet;
        System.out.printf("%s: %s%n", data.getUsername(), data.getMessage());
        break;
      }
    }
  }

  private void write(Packet packet) throws IOException {
    OutputStream os = sock.getOutputStream();
    os.write(gson.toJson(packet).getBytes());
    os.write('\n');
    os.flush();
  }

  public void disconnect() {
    try {
      sock.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isConnected() {
    return !sock.isClosed();
  }

  private void responseKeepAlive(long seq) throws IOException {
    write(new KeepAliveSynPacket(seq));
  }
}
