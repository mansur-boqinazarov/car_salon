package uz.pdp.back.service.car;

import com.google.gson.reflect.TypeToken;
import uz.pdp.back.databases.Database;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;


import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static uz.pdp.back.config.ThreadSafeBeansContainer.carModelService;
import static uz.pdp.back.config.ThreadSafeBeansContainer.carNameService;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/31/25
 */
public class CarServiceImp implements CarService{
    private final String fileURL = ResourceBundle.getBundle("files").getString("files.car");
    Database<Car> database = new Database<>();

    @Override
    public void create(Car car) {
        Type type = new TypeToken<List<Car>>(){}.getType();
        List<Car> carList = new ArrayList<>();
        if(database.getAll(type, fileURL) != null){
            carList = database.getAll(type, fileURL);
        }
        carList.add(car);
        database.save(carList, fileURL);
    }

    @Override
    public Car read(String id) {
        return readAll().stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public void update(Car car) {
    }

    @Override
    public void delete(String id) {
        Type type = new TypeToken<List<Car>>(){}.getType();
        database.delete(id,type,fileURL);
    }

    @Override
    public List<Car> readAll() {
        Type type = new TypeToken<List<Car>>(){}.getType();
        return database.getAll(type, fileURL);
    }

    @Override
    public List<Car> findCarById(String id) {
        return readAll().stream()
                .filter(car -> car.getSalonID().equals(id))
                .toList();
    }

    @Override
    public List<String> findModelIDByCarID(String id) {
        List<Car> cars = findCarById(id);
        Set<String> modelIDSet = new HashSet<>();
        cars.forEach(car -> modelIDSet.add(carNameService.get().read(car.getCarNameID()).getModelID()));
        return new ArrayList<>(modelIDSet);
    }
}