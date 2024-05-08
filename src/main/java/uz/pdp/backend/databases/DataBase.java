package uz.pdp.backend.databases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.backend.model.basemodel.BaseModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DataBase<T extends BaseModel> {
    public <T extends BaseModel> void SAVE(T model, String FILE_NAME) {
        Gson gson = new GsonBuilder().create();
        Path path = Paths.get(FILE_NAME);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            gson.toJson(model, writer);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends BaseModel> T GET(String id, String FILE_NAME, Class<T> clazz) {
        Gson gson = new Gson();
        Path path = Paths.get(FILE_NAME);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                T model = gson.fromJson(line, clazz);
                if (model.getId().equals(id))
                    return model;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public  <T extends BaseModel> List<T> GET_ALL(String FILE_NAME, Class<T> clazz) {
        Path path = Paths.get(FILE_NAME);
        List<T> models = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                T model = gson.fromJson(line, clazz);
                models.add(model);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return models;
    }

    public  void DELETE(String id, String FILE_NAME, String DELETING_FILE_NAME, Class<T> clazz) {
        Path path = Paths.get(FILE_NAME);
        List<T> models = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                T model = gson.fromJson(line, clazz);
                if (!model.getId().equals(id))
                    models.add(model);
            }
            Path path1 = Paths.get("database", DELETING_FILE_NAME);
            Files.deleteIfExists(path1);
                    Files.createFile(path1);
            try(BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                for (T model : models) {
                    gson.toJson(model, writer);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}