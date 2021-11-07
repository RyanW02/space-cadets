package chat.protocol;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class PacketAdapter implements JsonDeserializer<Packet> {
  @Override
  public Packet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    PacketType type = context.deserialize(json.getAsJsonObject().get("type"), PacketType.class);
    return context.deserialize(json, type.getType());
  }
}
