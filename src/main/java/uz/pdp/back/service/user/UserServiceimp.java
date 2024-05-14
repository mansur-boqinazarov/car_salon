package uz.pdp.back.service.user;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.user.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/30/07
 */
public class UserServiceimp implements UserService {
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.user");
    Database<User> database = new Database<>();

    @Override
    public void create(User model) {
        Type type = new TypeToken<List<User>>(){}.getType();
        List<User> list = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            list = database.getAll(type, fileURL);
        }
        list.add(model);
        database.save(list, fileURL);
    }

    @Override
    public User read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(User car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<User>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<User> readAll() {
        Type type = new TypeToken<List<User>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
