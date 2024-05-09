package uz.pdp.backend.model.car;

import lombok.*;
import uz.pdp.backend.enums.cars.carmodel.CarModel;
import uz.pdp.backend.enums.cars.colors.CarColor;
import uz.pdp.backend.enums.cars.carnames.CarName;
import uz.pdp.backend.enums.cars.fuelroute.FuelRoute;
import uz.pdp.backend.model.basemodel.BaseModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Car extends BaseModel {
    private UUID autoSalonID;
    private CarModel model;
    private CarName name;
    private CarColor color;
    private Date madeDate;
    private double price;
    private List<String> urlPhoto;  // kamida 3 ta rasm bo'ladi shuning uxhun stringlar listi dedim
    private String divigatel;
    private int carCount;
    private FuelRoute fuelRoute;
}