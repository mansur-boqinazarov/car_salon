package uz.pdp.backend.service.carservice;

import uz.pdp.backend.databases.DataBase;
import uz.pdp.backend.model.car.Car;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/06/53
 */
public class CarServiceimp implements CarService{

    private static DataBase<Car> dataBase = new DataBase<>();

    private final String FILE_URL = "src/main/resources/databases/Car.json";
    private final String FILE_NAME = "Car.json";

    @Override
    public void create(Car car) {
        dataBase.SAVE(car, FILE_URL);
    }

    @Override
    public Car read(UUID id) {
        return dataBase.GET(id, FILE_URL, Car.class);
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(UUID id) {
        dataBase.DELETE(id, FILE_URL, FILE_NAME, Car.class);
    }

    @Override
    public List<Car> readAll() {
        return dataBase.GET_ALL(FILE_URL, Car.class);
    }
}
