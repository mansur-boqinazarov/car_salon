package uz.pdp.back.service.carModel;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/32/26
 */
public class CarModelServiceimp implements CarModelService {
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.carModel");
    Database<CarModel> database = new Database<>();

    @Override
    public void create(CarModel car) {
        Type type = new TypeToken<List<CarModel>>(){}.getType();
        List<CarModel> carList = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            carList = database.getAll(type, fileURL);
        }
        carList.add(car);
        database.save(carList, fileURL);
    }

    @Override
    public CarModel read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(CarModel car) {

    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<CarModel>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<CarModel> readAll() {
        Type type = new TypeToken<List<Car>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
