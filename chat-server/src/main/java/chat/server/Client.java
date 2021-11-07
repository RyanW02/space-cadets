package chat.server;

import chat.protocol.Packet;
import chat.protocol.impl.JoinPacket;
import chat.protocol.impl.LeavePacket;
import chat.protocol.impl.MessageRecvPacket;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class Client {
  private final String username;
  private final Socket sock;
  private final AtomicLong lastAck = new AtomicLong(0);

  public Client(String username, Socket sock) {
    this.username = username;
    this.sock = sock;
  }

  // Utility methods
  public void write(Packet packet) throws IOException {
    Server.write(getSocket(), packet);
  }

  // Getters
  public String getUsername() {
    return username;
  }

  public Socket getSocket() {
    return sock;
  }

  public long getLastAck() {
    return lastAck.get();
  }

  // Setters
  public void setLastAck(long lastAck) {
    this.lastAck.set(lastAck);
  }

  // Methods
  public void publishMessage(String username, String message) throws IOException {
    write(new MessageRecvPacket(username, message));
  }

  public void publishJoin(String username) throws IOException {
    write(new JoinPacket(username));
  }

  public void publishLeave(String username) throws IOException {
    write(new LeavePacket(username));
  }
}
