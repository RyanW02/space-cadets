package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class KeepAliveSynPacket extends Packet {
  private final long seq;

  public KeepAliveSynPacket(long seq) {
    super(PacketType.KEEP_ALIVE_SYN);
    this.seq = seq;
  }

  public long getSeq() {
    return seq;
  }
}
