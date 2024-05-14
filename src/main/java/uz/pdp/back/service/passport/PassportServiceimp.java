package uz.pdp.back.service.passport;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.passport.Passport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/31/39
 */
public class PassportServiceimp implements PassportService {
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.passport");
    Database<Passport> database = new Database<>();

    @Override
    public void create(Passport model) {
        Type type = new TypeToken<List<Passport>>(){}.getType();
        List<Passport> list = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            list = database.getAll(type, fileURL);
        }
        list.add(model);
        database.save(list, fileURL);
    }

    @Override
    public Passport read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Passport car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<Passport>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<Passport> readAll() {
        Type type = new TypeToken<List<Passport>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
