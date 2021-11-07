package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class HelloPacket extends Packet {
  private final String username;

  public HelloPacket(String name) {
    super(PacketType.HELLO);
    this.username = name;
  }

  public String getUsername() {
    return username;
  }
}
