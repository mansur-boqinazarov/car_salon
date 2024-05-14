package uz.pdp.back.runner;

import uz.pdp.back.enums.FuelRoute;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.model.carname.CarName;

import java.util.List;
import java.util.stream.IntStream;

import static uz.pdp.back.config.Configuration.*;
import static uz.pdp.back.utils.Util.enterInt;
import static uz.pdp.back.utils.Util.enterStr;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 13/53/24
 */
public class CarNameController {
    public static void start(){
        System.out.println("1. Moshina ma`lumotlarini kiritish");
        System.out.println("2. Stop");
        if(enterInt("Tanlang...") == 1){
            String modelID = modelID();
            String name = enterStr("Mashina nomini kiriting: ");
            String color = enterStr("Mashina rangini kiriting: ");
            System.out.println("Mashina yoqilg'i turi");
            System.out.println("1. "+FuelRoute.GASOLINE);
            System.out.println("2. "+FuelRoute.DIESEL);
            System.out.println("3. "+FuelRoute.HYBRID);
            System.out.println("4. "+FuelRoute.ELECTRIC_CAR);
            FuelRoute fuelRoute = null;
             switch (enterInt("tanlang...")){
                case 1 -> fuelRoute = FuelRoute.GASOLINE;
                case 2 -> fuelRoute = FuelRoute.DIESEL;
                case 3 -> fuelRoute = FuelRoute.HYBRID;
                case 4 -> fuelRoute = FuelRoute.ELECTRIC_CAR;
                default -> System.out.println("XATO");
            }
            carNameService.get().create(new CarName(modelID, name, color, fuelRoute));
        }
    }
    private static String modelID(){
        List<CarModel> carModels = carModelService.get().readAll();
        IntStream.range(0, carModels.size())
                .forEach(i-> System.out.println((i+1) + " - " + carModels.get(i)));
        return carModels.get(enterInt("tanlang...") - 1).getId();
    }
}
