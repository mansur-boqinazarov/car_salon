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
import java.util.UUID;

public class DataBase<T extends BaseModel> {
    /**
     * Berilgan model obyektini ma'lumotlar bazasiga saqlash.
     *
     * @param model     Saqlanadigan model obyekti.
     * @param FILE_URL  Obyekt saqlanadigan faylning URL manzili.
     */
    public <T extends BaseModel> void SAVE(T model, String FILE_URL) {
        Gson gson = new GsonBuilder().create();
        Path path = Paths.get(FILE_URL);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            gson.toJson(model, writer);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ma'lum bir ID boyicha model obyektini fayldan olish.
     *
     * @return Olingan model obyekti, agar topilmasa null qaytariladi.
     */
    public <T extends BaseModel> T GET(UUID id, String FILE_URL, Class<T> clazz) {
        Gson gson = new Gson();
        Path path = Paths.get(FILE_URL);
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

    /**
     * Barcha model obyektlarni fayldan olish.
     *
     * @param FILE_URL  Obyekt olinadigan faylning URL manzili.
     * @param clazz     Obyekt klassi.
     * @return          Olingan barcha model obyektlarining ro'yxati.
     */
    public  <T extends BaseModel> List<T> GET_ALL(String FILE_URL, Class<T> clazz) {
        Path path = Paths.get(FILE_URL);
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

    /**
     * Ma'lum bir ID boyicha model obyektini fayldan o'chirish.
     * @param id        O'chirish kerak bo'lgan modelning unikal ID'si.
     * @param FILE_URL  Obyekt olinadigan faylning URL manzili.
     * @param FILE_NAME O'chirilgan fayl nomi.
     * @param clazz     Obyekt klassi.
     */
    public  void DELETE(UUID id, String FILE_URL, String FILE_NAME, Class<T> clazz) {
        Path path = Paths.get(FILE_URL);
        List<T> models = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                T model = gson.fromJson(line, clazz);
                if (!model.getId().equals(id))
                    models.add(model);
            }
            Path path1 = Paths.get("src/main/resources/databases", FILE_NAME);
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