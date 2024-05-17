package uz.pdp.telegram.util.json;

import com.google.gson.*;
import com.google.gson.JsonSerializer;
import uz.pdp.telegram.state.State;

import java.lang.reflect.Type;

public class StateAdapter implements JsonSerializer<State>, JsonDeserializer<State> {
    @Override
    public JsonElement serialize(State src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public State deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String className = jsonObject.get("type").getAsString();
        JsonElement jsonData = jsonObject.get("data");
        try {
            Class<?> clazz = Class.forName(className);
            return context.deserialize(jsonData, clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
