package uz.pdp.frontend.ui;

import uz.pdp.backend.enums.roles.Role;
import uz.pdp.backend.model.carsalon.CarSalon;
import uz.pdp.backend.model.user.Passport;
import uz.pdp.backend.model.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static uz.pdp.config.ThreadSafeBeansContainer.*;
import static uz.pdp.frontend.util.UtilMenu.*;
import static uz.pdp.frontend.util.Util.*;
import static uz.pdp.backend.service.isvalid.IsValid.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 09/03/04
 */
public class UI {

    public static User user = new User();
    public static CarSalon carSalon = new CarSalon();

    public static void main(String[] args) throws Exception{
        mainMenu();
    }
    private static void mainMenu(){
        utilMainMenu();
        switch (enterInt("Tanlang...")){
            case 1 -> signIn();
            case 2 -> signUp();
            case 3 -> {}
            default -> {
                mainMenu();
            }
        }
    }
    private static void signIn(){
        String phoneNumber = enterStr("Telefon raqamingizni kiriting: ");
        String password = enterStr("Passwordingizni kiriting: ");
        try{
            user = userService.get().login(phoneNumber, password);
            if(user != null){
                superAdminMenu();
            }
            else System.out.println("Bunday foydalanuvchi ro'yxatdan o'tmagan");
        }
        catch (Exception ignore){
            System.out.println("Bunday foydalanuvchi ro'yxatdan o'tmagan");
        }
    }
    private static void superAdminMenu() throws Exception{
        utilSuperAdminMenu();
        switch (enterInt("Tanlang...")){
            case 1 -> allCarSalonList();
            case 2 -> createNewCarSalon();
            case 3 -> deleteCarSalon();
            case 4 -> mainMenu();
            default -> {}
        }
        superAdminMenu();
    }
    private static void allCarSalonList(){
        List<CarSalon> carSalons = carSalonService.get().readAll();
        carSalons.forEach(System.out::println);
    }
    private static void createNewCarSalon() throws InterruptedException {
        System.out.println("Avto salonlarga litsenziya berish bo'limiga xush kelibsiz!");
        TimeUnit.SECONDS.sleep(2);
        List<User> users = listBossAutoSalons();
        IntStream.range(0, users.size())
                .forEach(i-> System.out.println((i+1) + " - " + users.get(i)));
        int choose = enterInt("Avtosalon egasin tanlang: ") - 1;
        UUID avtoSalonAdminID = users.get(choose).getId();
        String salonName = enterStr("Avto salonning ismini kiriting: ");
        String salonLocation = enterStr("Salonning manzilini kiriting: ");
        String avtoSalonRaqami = enterStr("Avto salonning telefon raqamini kiriting: ");
        carSalonService.get().create(new CarSalon(avtoSalonAdminID, salonName, salonLocation, new Date(), new Date(), avtoSalonRaqami));
    }
    private static void deleteCarSalon(){
        System.out.println("Avtosalonlarni litsenziyasini bekor qilish bo'limi");
        List<CarSalon> carSalons = carSalonService.get().readAll();
        IntStream.range(0, carSalons.size())
                .forEach(i-> System.out.println((i+1) + " - " + carSalons.get(i)));
        int choose = enterInt("Avto salonni tanlang...") - 1;
        carSalonService.get().delete(carSalons.get(choose).getId());
    }
    private static List<User> listBossAutoSalons(){
        List<User> users = userService.get().readAll();
        return users.stream()
                .filter(user->user.getRole().equals(Role.ADMIN))
                .toList();
    }
    private static void adminMenu(){
        utilAdminMenu();
        switch (enterInt("Tanlang...")){
            case 1 -> myCarSalon();
            case 2 -> createNewCar();
            case 3 -> deleteCar();
            case 4 -> {}
            default -> adminMenu();
        }
        adminMenu();
    }
    private static void myCarSalon(){
        List<CarSalon> carSalons = carSalonService.get().readAll();
        carSalon = carSalons.stream()
                .filter(carSalon -> carSalon.getAdminID().equals(user.getId()))
                .toList()
                .get(0);
        System.out.println(carSalon);
    }
    private static void createNewCar(){

    }
    private static void deleteCar(){

    }

    private static void signUp() {
        String gmailAddress = getGmail();
        String password = getPassword();
        String phoneNumber = getPhoneNumber();
        try {
            gmailService.get().registerMailService(gmailAddress);
            String activitionCode = enterStr("Activition codeni kiriting: ");
            while (! gmailService.get().checkVerificationCode(activitionCode)) {
                activitionCode = enterStr("Xato kiritildi! Activition codeni kiriting: ");
            }
            String firstName = enterStr("First Name: ");
            String lastName = enterStr("Last Name: ");
            String fatherName = enterStr("Father Name: ");
            String passportSeries = enterStr("Passport series: ");
            String passportNumber = enterStr("Passport number: ");
            userService.get().create(new User(0,
                    new Passport(firstName,
                            lastName, fatherName,
                            new Date(), passportSeries,
                            passportNumber),
                    phoneNumber, gmailAddress,
                    password, Role.ADMIN));
        }
        catch (Exception ignore){}
    }

    private static String getPassword() {
        String password = enterStr("Iltimos parol kiriting: ");
        while (! isValidPassword(password)){
            System.out.println("Parol xavfsiz emas. Uzunligi kamida 8, katta-kichik harflar, sonlar va _#@%&* belgilaridan foydalaning");
            password = enterStr("Iltimos parol kiriting: ");
        }
        return password;
    }
    private static String getPhoneNumber(){
        String phoneNumber = enterStr("Raqamingizni kiriting: ");
        while (isValidPhoneNumber(phoneNumber)){
            System.out.println("Telefon raqamni real shaklda kiriting. Misol uchun +998977152600");
            phoneNumber = enterStr("Raqamingizni kiriting: ");
        }
        return phoneNumber;
    }
    private static String getGmail(){
        System.out.println("Attention❗❗❗\nRo'yxatdan o'tish jarayonida real gmail addressdan foydalaning");
        String gmail = enterStr("Iltimos gmail address kiriting: ");
        while (! isValidGmail(gmail))
            gmail = enterStr("Xato gmail kiritildi. Iltimos example@gmail.com ko'rinishida kiriting: ");
        return gmail;
    }
}