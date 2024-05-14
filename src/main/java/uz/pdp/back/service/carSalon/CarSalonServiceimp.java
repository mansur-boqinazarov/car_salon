package uz.pdp.back.service.carSalon;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carsalon.CarSalon;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/32/36
 */
public class CarSalonServiceimp implements CarSalonService {
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.carSalon");
    Database<CarSalon> database = new Database<>();

    @Override
    public void create(CarSalon car) {
        Type type = new TypeToken<List<CarSalon>>(){}.getType();
        List<CarSalon> carList = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            carList = database.getAll(type, fileURL);
        }
        carList.add(car);
        database.save(carList, fileURL);
    }

    @Override
    public CarSalon read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(CarSalon car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<CarSalon>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<CarSalon> readAll() {
        Type type = new TypeToken<List<CarSalon>>(){}.getType();
        return database.getAll(type, fileURL);
    }
}
