package chat.protocol.impl;

import chat.protocol.Packet;
import chat.protocol.PacketType;

public class MessageRecvPacket extends Packet {
  private final String username, message;

  public MessageRecvPacket(String username, String message) {
    super(PacketType.MESSAGE_RECV);
    this.username = username;
    this.message = message;
  }

  public String getUsername() {
    return username;
  }

  public String getMessage() {
    return message;
  }
}
