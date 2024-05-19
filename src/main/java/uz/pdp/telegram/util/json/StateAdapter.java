package uz.pdp.telegram.util.json;import com.google.gson.*;
import uz.pdp.telegram.state.user.State;
import uz.pdp.telegram.state.user.*;

import java.lang.reflect.Type;

public class StateAdapter implements JsonSerializer<State>, JsonDeserializer<State> {

    @Override
    public JsonElement serialize(State state, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(state.getClass().getSimpleName() + "." + state.toString());
    }
    @Override
    public State deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String[] parts = json.getAsString().split("\\.");
        String enumName = parts[parts.length - 1];
        if (parts.length == 1) {
            try {
                return DefaultState.valueOf(parts[0]);
            } catch (IllegalArgumentException ignore) {
            }
            try {
                return GenerateUserPassportState.valueOf(parts[0]);
            } catch (IllegalArgumentException ignore) {
            }
            try {
                return OrderState.valueOf(parts[0]);
            } catch (IllegalArgumentException ignore) {
            }
            try {
                return SelectSalonMenuState.valueOf(parts[0]);
            } catch (IllegalArgumentException ignore) {
            }
        } else if (parts.length == 2) {
            try {
                Class<?> clazz = Class.forName("uz.pdp.telegram.state.user." + parts[0]);
                if (clazz.isEnum()) {
                    return (State) Enum.valueOf((Class<Enum>) clazz, enumName);
                }
            } catch (ClassNotFoundException | IllegalArgumentException ignore) {
            }
        }
    throw new JsonParseException("Invalid state format");
    }
}