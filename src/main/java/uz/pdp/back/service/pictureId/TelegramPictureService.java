package uz.pdp.back.service.pictureId;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.service.base.BaseService;
import uz.pdp.telegram.model.picture.TelegramPictureID;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 17/May/2024 12/01/06
 */
public class TelegramPictureService implements BaseService<TelegramPictureID> {
    private static final String fileURL = ResourceBundle.getBundle("files").getString("files.urlTelegramPicture");
    Database<TelegramPictureID> database = new Database<>();

    @Override
    public void create(TelegramPictureID telegramPictureID) {
        Type type = new TypeToken<List<TelegramPictureID>>(){}.getType();
        List<TelegramPictureID> list = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            list = database.getAll(type, fileURL);
        }
        list.add(telegramPictureID);
        database.save(list, fileURL);
    }

    @Override
    public TelegramPictureID read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(TelegramPictureID telegramPictureID) {

    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<TelegramPictureID>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<TelegramPictureID> readAll() {
        Type type = new TypeToken<List<TelegramPictureID>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
