package uz.pdp.back.runner;


import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carname.CarName;
import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.back.model.picture.Picture;

import java.util.List;
import java.util.stream.IntStream;

import static uz.pdp.back.config.Configuration.*;
import static uz.pdp.back.utils.Util.*;

public class CarController {
    public static void start(){
        String salonID = salonID();
        String carNameID = carNameID();
        String pictureID = pictureID();
        double price = enterDouble("Mashina narxini kiriting: ");
        int year = enterInt("Mashina ishlab chiqarilgan yilni kiriting: ");
        carService.get().create(new Car(salonID, carNameID, pictureID, price, year));
    }
    private static String salonID(){
        List<CarSalon> carSalons = carSalonService.get().readAll();
        IntStream.range(0, carSalons.size())
                .forEach(i-> System.out.println((i+1) + " - " + carSalons.get(i)));
        return carSalons.get(enterInt("Tanlang...") - 1).getId();
    }
    private static String carNameID(){
        List<CarName> carNames = carNameService.get().readAll();
        IntStream.range(0, carNames.size())
                .forEach(i-> System.out.println((i+1) + " - " + carNames.get(i)));
        return carNames.get(enterInt("Tanlang...") - 1).getId();
    }
    private static String pictureID(){
        List<Picture> pictures = pictureService.get().readAll();
        IntStream.range(0, pictures.size())
                .forEach(i-> System.out.println((i+1) + " - " + pictures.get(i)));
        return pictures.get(enterInt("Tanlang...") - 1).getId();
    }
}
