package uz.pdp.back.service.location;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.location.Location;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/32/05
 */
public class LocationServiceimp implements LocationService{
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.location");
    Database<Location> database = new Database<>();

    @Override
    public void create(Location model) {
        Type type = new TypeToken<List<Location>>(){}.getType();
        List<Location> list = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            list = database.getAll(type, fileURL);
        }
        list.add(model);
        database.save(list, fileURL);
    }

    @Override
    public Location read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Location car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<Location>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<Location> readAll() {
        Type type = new TypeToken<List<Location>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
