package chat.protocol;

import chat.protocol.impl.*;
import com.google.gson.annotations.JsonAdapter;

import java.lang.reflect.Type;

@JsonAdapter(PacketTypeAdapter.class)
public enum PacketType {
  KEEP_ALIVE_SYN(PacketSource.SERVER),
  KEEP_ALIVE_ACK(PacketSource.CLIENT),
  HELLO(PacketSource.CLIENT),
  DISCONNECT(PacketSource.SERVER),
  JOIN(PacketSource.SERVER),
  LEAVE(PacketSource.SERVER),
  MESSAGE_SEND(PacketSource.CLIENT),
  MESSAGE_RECV(PacketSource.SERVER),
  ;

  private final PacketSource source;

  PacketType(PacketSource source) {
    this.source = source;
  }

  public PacketSource getSource() {
    return source;
  }

  public Type getType() {
    return switch (this) {
      case KEEP_ALIVE_SYN -> KeepAliveSynPacket.class;
      case KEEP_ALIVE_ACK -> KeepAliveAckPacket.class;
      case HELLO -> HelloPacket.class;
      case DISCONNECT -> DisconnectPacket.class;
      case JOIN -> JoinPacket.class;
      case LEAVE -> LeavePacket.class;
      case MESSAGE_SEND -> MessageSendPacket.class;
      case MESSAGE_RECV -> MessageRecvPacket.class;
    };
  }
}
