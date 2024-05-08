package uz.pdp.backend.service.carservice;

import uz.pdp.backend.model.car.Car;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/06/53
 */
public class CarServiceimp implements CarService{

    private final String urlFile = "src/main/resources/databases/Car.json";
    private final String nameFile = "Car.json";

    @Override
    public void create(Car car) {

    }

    @Override
    public Car read(UUID id) {
        return null;
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<Car> readAll() {
        return List.of();
    }
}
