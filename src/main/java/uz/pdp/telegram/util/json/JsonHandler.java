package uz.pdp.telegram.util.json;

import com.google.gson.GsonBuilder;
import uz.pdp.telegram.state.State;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class JsonHandler {
    private static final String FILE_URL = ResourceBundle.getBundle("files").getString("files.userTypeStates");
    private static final JsonSerializer<ConcurrentHashMap<Long, State>> serializer = new JsonSerializer<>(FILE_URL);
    private static final JsonSerializer<ConcurrentHashMap<Long, State>> deserializer = new JsonSerializer<>(FILE_URL);

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(State.class, new StateAdapter());
        gsonBuilder.registerTypeAdapter(State.class, new StateInstanceCreator());
        deserializer.setGson(gsonBuilder.create());
    }

    public static void saveToJson(ConcurrentHashMap<Long, State> map){
        serializer.serialize(map);
    }

    public static ConcurrentHashMap<Long, State> loadFromJson(){
        return null;/*deserializer.deserialize(new TypeToken<ConcurrentHashMap<Long, State>>(){}.getType());*/
    }
}
