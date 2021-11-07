package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class LeavePacket extends Packet {
  private final String username;

  public LeavePacket(String username) {
    super(PacketType.LEAVE);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
