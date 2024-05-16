package uz.pdp.back;

import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carmodel.CarModel;

import java.util.List;
import java.util.Set;

import static uz.pdp.back.config.ThreadSafeBeansContainer.carModelService;
import static uz.pdp.back.config.ThreadSafeBeansContainer.carService;

public class Test {
    public static void main(String[] args) {
        List<String> modelIDByCarID = carService.get().findModelIDByCarID("96984b03-077c-4a7e-94da-52cd8a0a3785");
        for (String id : modelIDByCarID) {
            CarModel read = carModelService.get().read(id);
            System.out.println(read);
        }
    }
}