package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class KeepAliveAckPacket extends Packet {
  private final long seq;

  public KeepAliveAckPacket(long seq) {
    super(PacketType.KEEP_ALIVE_ACK);
    this.seq = seq;
  }

  public long getSeq() {
    return seq;
  }
}
