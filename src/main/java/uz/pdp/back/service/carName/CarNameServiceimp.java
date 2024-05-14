package uz.pdp.back.service.carName;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.carname.CarName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/32/58
 */
public class CarNameServiceimp implements CarNameService {
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.carName");
    Database<CarName> database = new Database<>();

    @Override
    public void create(CarName car) {
        Type type = new TypeToken<List<CarName>>(){}.getType();
        List<CarName> carList = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            carList = database.getAll(type, fileURL);
        }
        carList.add(car);
        database.save(carList, fileURL);
    }

    @Override
    public CarName read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(CarName car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<CarName>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<CarName> readAll() {
        Type type = new TypeToken<List<CarName>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
