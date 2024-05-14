package uz.pdp.back.runner;

import uz.pdp.back.model.address.Address;
import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.back.model.user.User;

import java.util.List;
import java.util.stream.IntStream;

import static uz.pdp.back.config.Configuration.*;
import static uz.pdp.back.utils.Util.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 12/30/35
 */
public class CarSalonController {
    public static void start(){
        System.out.println("1. Car salon create qilish");
        System.out.println("2. Dasturni tugatish");
        if(enterInt("Tanlang...") == 1){
            String ownerID = ownerID();
            String addressID = addressCreateAndID();
            String salonName = enterStr("Avto salon nomini kiriting: ");
            String phoneNumber = enterStr("Avto salon telefon raqamini kiriting: ");
            carSalonService.get().create(new CarSalon(ownerID, addressID, salonName, phoneNumber));
        }
    }
    private static String addressCreateAndID(){
        String city = enterStr("Shahar nomini kiriting: ");
        String district = enterStr("Tuman nomini kiriting: ");
        String street = enterStr("Ko'cha nomini kiriting: ");
        String apartmentNumber = enterStr("Bino manzil raqamini kiriting: ");
        Address address = new Address(city, district, street, apartmentNumber);
        addressService.get().create(address);
        return address.getId();
    }
    private static String ownerID(){
        System.out.println("Avto salon egasini tanlash");
        List<User> users = userService.get().readAll();
        IntStream.range(0, users.size())
                .forEach(i-> System.out.println((i+1) + " - " + users.get(i)));
        return users.get(enterInt("Tanlang...") - 1).getId();
    }
}
