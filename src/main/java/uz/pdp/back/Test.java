package uz.pdp.back;


import uz.pdp.back.controller.CarController;
import uz.pdp.back.controller.LocationController;

import static uz.pdp.back.config.Configuration.addressService;

public class Test {
    public static void main(String[] args) {
        CarController.start();
    }
}