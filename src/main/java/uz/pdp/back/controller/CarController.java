package uz.pdp.back.controller;


import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;
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
                .forEach(i-> System.out.println((i+1) + " - " + carSalons.get(i).getCarSalonName()));
        return carSalons.get(enterInt("Tanlang...") - 1).getId();
    }
    private static String carNameID(){
        List<CarModel> carModels = carModelService.get().readAll();
        IntStream.range(0, carModels.size())
                .forEach(i-> System.out.println((i+1) + " - " + carModels.get(i).getModelName()));
        String idCarModel = carModels.get(enterInt("Modelni tanlang...") - 1).getId();
        List<CarName> carNames = carNameService.get().readAll();
        List<CarName> list = carNames.stream()
                .filter(car -> car.getModelID().equals(idCarModel))
                .toList();
        IntStream.range(0, list.size())
                .forEach(i-> System.out.println((i+1) + " - " + list.get(i).getModelName()));
        return list.get(enterInt("Tanlang...") - 1).getId();
    }
    private static String pictureID(){
        List<Picture> pictures = pictureService.get().readAll();
        IntStream.range(0, pictures.size())
                .forEach(i-> System.out.println((i+1) + " - " + pictures.get(i).getName()));
        return pictures.get(enterInt("Tanlang...") - 1).getId();
    }
}