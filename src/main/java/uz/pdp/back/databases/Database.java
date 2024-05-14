package uz.pdp.back.databases;

import com.google.gson.Gson;
import uz.pdp.back.model.basemodel.BaseModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database<T> {

    private final Gson gson = new Gson();

    public Database() {
    }

    public void save(List<T> models, String FILE_URL) {
        Path path = Path.of(FILE_URL);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(gson.toJson(models));
            writer.newLine();
        } catch (IOException ignore) {}
    }

    public List<T> getAll(Type type, String FILE_URL) {
        Path path = Path.of(FILE_URL);
        List<T> models = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            models = gson.fromJson(reader, type);
        } catch (IOException ignore) {
        }
        return models;
    }
    public void delete(String id, Type type, String FILE_URL) {
        Path path = Path.of(FILE_URL);
        List<T> models = getAll(type, FILE_URL);
        models.removeIf(model -> {
            if (model instanceof BaseModel baseModel) {
                return baseModel.getId().equals(id);
            }
            return false;
        });
        save(models, FILE_URL);
    }
}