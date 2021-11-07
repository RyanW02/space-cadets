package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class JoinPacket extends Packet {
  private final String username;

  public JoinPacket(String username) {
    super(PacketType.JOIN);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
