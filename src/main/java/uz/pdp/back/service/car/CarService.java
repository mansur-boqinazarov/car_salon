package uz.pdp.back.service.car;

import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.service.base.BaseService;

import java.util.List;
import java.util.Set;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 11/30/48
 */
public interface CarService extends BaseService<Car> {
    List<Car> findCarById(String id);
    List<String> findModelIDByCarID(String id);
}
