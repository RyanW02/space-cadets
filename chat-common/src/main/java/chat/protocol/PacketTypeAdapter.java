package chat.protocol;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PacketTypeAdapter implements JsonSerializer<PacketType>, JsonDeserializer<PacketType> {

  @Override
  public PacketType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return PacketType.values()[json.getAsInt()];
  }

  @Override
  public JsonElement serialize(PacketType src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.ordinal());
  }
}
