package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class MessageSendPacket extends Packet {
  private final String message;

  public MessageSendPacket(String message) {
    super(PacketType.MESSAGE_SEND);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
