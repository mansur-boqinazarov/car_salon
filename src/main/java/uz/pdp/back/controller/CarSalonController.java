package uz.pdp.back.controller;

import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.back.model.user.User;

import java.util.List;
import java.util.stream.IntStream;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;
import static uz.pdp.back.utils.Util.enterInt;
import static uz.pdp.back.utils.Util.enterStr;

public class CarSalonController {

    public static void start(){
        String ownerID = ownerID();
        String addressID = addressCreateAndID();
        String salonName = enterStr("Avto salon nomini kiriting: ");
        String phoneNumber = enterStr("Avto salon telefon raqamini kiriting: ");
        carSalonService.get().create(new CarSalon(ownerID, addressID, salonName, phoneNumber));
    }

    private static String addressCreateAndID(){
        System.out.println("Addressni tanlash kerak");
        List<Address> addresses = addressService.get().readAll();
        IntStream.range(0, addresses.size())
                .forEach(i-> System.out.println((i+1) + " - " + addresses.get(i)));
        return addresses.get(enterInt("Tanlang...") - 1).getId();
    }

    private static String ownerID(){
        System.out.println("Avto salon egasini tanlash");
        List<User> users = userService.get().readAll();
        IntStream.range(0, users.size())
                .forEach(i-> System.out.println((i+1) + " - " + users.get(i)));
        return users.get(enterInt("Tanlang...") - 1).getId();
    }

}