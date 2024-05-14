package uz.pdp.back;

import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.model.carname.CarName;
import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.back.runner.*;

import java.awt.*;

import static uz.pdp.back.config.Configuration.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 13/09/40
 */
public class Test {
    public static void main(String[] args) {

        System.out.println(carNameService.get().readAll().size());

    }
}
