package uz.pdp.back.controller;

import uz.pdp.back.model.carmodel.CarModel;

import static uz.pdp.back.config.Configuration.carModelService;
import static uz.pdp.back.utils.Util.enterInt;
import static uz.pdp.back.utils.Util.enterStr;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 13/50/29
 */
public class CarModelController {
    public static void start(){
        System.out.println("1. Mashina modelini yaratish ");
        System.out.println("2. Stop");
        if(enterInt("Tanlang...") == 1){
            String name = enterStr("Modelni kiriting(BMW, CHEVRALET): ");
            carModelService.get().create(new CarModel(name));
        }
        else return;
    }

}
