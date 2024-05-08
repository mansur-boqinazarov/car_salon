package uz.pdp.backend.service.carsalonservice;

import uz.pdp.backend.model.carsalon.CarSalon;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/06/03
 */
public class CarSalonServiceimp implements CarSalonService{

    private final String urlFile = "src/main/resources/databases/CarSalon.json";
    private final String nameFile = "CarSalon.json";

    @Override
    public void create(CarSalon carSalon) {

    }

    public CarSalon read(UUID id) {
        return null;
    }

    @Override
    public void update(CarSalon carSalon) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<CarSalon> readAll() {
        return List.of();
    }
}
