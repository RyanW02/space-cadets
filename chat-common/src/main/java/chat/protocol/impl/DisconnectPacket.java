package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class DisconnectPacket extends Packet {
  private final String reason;

  public DisconnectPacket(String reason) {
    super(PacketType.DISCONNECT);
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }
}
