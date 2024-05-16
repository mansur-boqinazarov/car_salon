package uz.pdp.back.controller;

import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.back.model.location.Location;

import java.util.List;
import java.util.stream.IntStream;

import static uz.pdp.back.config.ThreadSafeBeansContainer.carSalonService;
import static uz.pdp.back.config.ThreadSafeBeansContainer.locationService;
import static uz.pdp.back.utils.Util.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 13/33/21
 */
public class LocationController {
    public static void start(){
            List<CarSalon> carSalons = carSalonService.get().readAll();
            IntStream.range(0, carSalons.size())
                    .forEach(i-> System.out.println((i+1) + " - " + carSalons.get(i)));
            String salonID = carSalons.get(enterInt("Tanlang...") - 1).getId();
            double latitude = enterDouble("latitude: ");
            double longitude = enterDouble("longitude: ");
            Location location = new Location(salonID, latitude, longitude);
            locationService.get().create(location);
    }
}
