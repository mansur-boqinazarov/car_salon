package uz.pdp.backend.service.carsalonservice;

import uz.pdp.backend.databases.DataBase;
import uz.pdp.backend.model.carsalon.CarSalon;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/06/03
 */
public class CarSalonServiceimp implements CarSalonService{

    private static DataBase<CarSalon> dataBase = new DataBase<>();

    private final String FILE_URL = "src/main/resources/databases/CarSalon.json";
    private final String FILE_NAME = "CarSalon.json";

    @Override
    public void create(CarSalon carSalon) {
        dataBase.SAVE(carSalon, FILE_URL);
    }

    public CarSalon read(UUID id) {
        return dataBase.GET(id, FILE_URL, CarSalon.class);
    }

    @Override
    public void update(CarSalon carSalon) {

    }

    @Override
    public void delete(UUID id) {
        dataBase.DELETE(id, FILE_URL, FILE_NAME, CarSalon.class);
    }

    @Override
    public List<CarSalon> readAll() {
        return dataBase.GET_ALL(FILE_URL, CarSalon.class);
    }
}