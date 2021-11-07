package chat.protocol;

import com.google.gson.annotations.JsonAdapter;

@JsonAdapter(PacketAdapter.class)
public abstract class Packet {
  protected final PacketType type;

  public Packet(PacketType type) {
    this.type = type;
  }

  public PacketType getPacketType() {
    return type;
  }
}
