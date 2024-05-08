package uz.pdp.backend.dto;

import uz.pdp.backend.enums.cars.carmodel.CarModel;
import uz.pdp.backend.enums.cars.carnames.CarName;
import uz.pdp.backend.enums.cars.colors.CarColor;
import uz.pdp.backend.enums.cars.fuelroute.FuelRoute;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/12/24
 */
public record CarDTO(UUID autosalonID, CarModel carModel,
                     CarName name, CarColor color,
                     Date madeDate, double price,
                     List<String> urlPhoto, String dvigatel,
                     int carCount, FuelRoute fuelRoute) implements DTO{
}