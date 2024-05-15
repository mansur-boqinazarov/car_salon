package uz.pdp.back.controller;

import uz.pdp.back.model.address.Address;

import static uz.pdp.back.config.Configuration.addressService;
import static uz.pdp.back.utils.Util.enterStr;

/**
 * @author To'lqin Ruzimbayev
 * @since 15/May/2024 10/11/28
 */
public class AddressController {
    public static void start(){
        String city = enterStr("Shaharni nomini kiriting: ");
        String district = enterStr("Tumanni nomini kiriting: ");
        String street = enterStr("Ko'cha nomini kiriting: ");
        String apartmentNumber = enterStr("Uy raqamini kiriting: ");
        addressService.get().create(new Address(city, district, street, apartmentNumber));
    }
}
