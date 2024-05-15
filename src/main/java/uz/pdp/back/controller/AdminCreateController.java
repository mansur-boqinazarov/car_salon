package uz.pdp.back.controller;

import uz.pdp.back.enums.Role;
import uz.pdp.back.model.passport.Passport;
import uz.pdp.back.model.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static uz.pdp.back.config.Configuration.*;
import static uz.pdp.back.utils.Util.*;

public class AdminCreateController {

    public static void start(){
        System.out.println("1. Yangi account yaratish");
        System.out.println("2. Dasturni tugatish");
        if (enterInt("Tanlang...") == 1) {
            String gmail = enterStr("gmail address kiriting: ");
            String password = enterStr("password kiriting: ");
            String firstName = enterStr("Ismingizni kiriting: ");
            String lastName = enterStr("Familiyangizni kiriting: ");
            String fatherName = enterStr("Otangizni ismini kiriting: ");
            Date birthDate = birhtDate(enterStr("(dd-MM-yyyy) ko'rinishida tug'ilgan sanangizni kiriting: "));
            String gender = enterStr("Jinsingizni kiriting((E)rkak/A(yol)): ");
            String passportNumber = enterStr("Passport nomerini kiriting: ");
            String passportSeries = enterStr("Passport Seriyasini kiriting: ");

            Passport passport = new Passport(firstName, lastName, fatherName, birthDate, gender, passportNumber, passportSeries);

            passportService.get().create(passport);

            userService.get().create(
                    new User(gmail, password, passport.getId(), Role.ADMIN)
            );
        }
    }
    private static Date birhtDate(String birthDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(birthDate);
        } catch (ParseException ignore) {
            return null;
        }
    }
}