package uz.pdp.backend.service.carservice;

import uz.pdp.backend.model.car.Car;
import uz.pdp.backend.service.baseservice.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/06/25
 */
public interface CarService extends BaseService<Car> {
    List<Car> readAllBySalonId(UUID id);
}
