package uz.pdp.telegram.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import lombok.Setter;

import java.io.*;

public class JsonSerializer<T> {
    private final String filePath;
    @Setter
    private Gson gson;

    public JsonSerializer(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void serialize(T data) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException ignore) {
        }
    }

    public T deserialize(Class<T> type) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException | JsonSyntaxException | JsonIOException e) {
            return null; // Fayl o'qib olishda xatolik bo'lganida null qaytariladi
        }
    }
}