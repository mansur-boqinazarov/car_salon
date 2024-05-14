package uz.pdp.back.service.picture;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.picture.Picture;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/30/41
 */
public class PictureServiceimp implements PictureService {
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.picture");
    Database<Picture> database = new Database<>();

    @Override
    public void create(Picture model) {
        Type type = new TypeToken<List<Picture>>(){}.getType();
        List<Picture> list = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            list = database.getAll(type, fileURL);
        }
        list.add(model);
        database.save(list, fileURL);
    }

    @Override
    public Picture read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Picture car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<Picture>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<Picture> readAll() {
        Type type = new TypeToken<List<Picture>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
