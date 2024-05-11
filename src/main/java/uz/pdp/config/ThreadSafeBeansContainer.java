package uz.pdp.config;

import uz.pdp.backend.model.carsalon.CarSalon;
import uz.pdp.backend.model.user.User;
import uz.pdp.backend.service.carsalonservice.CarSalonService;
import uz.pdp.backend.service.carsalonservice.CarSalonServiceimp;
import uz.pdp.backend.service.carservice.CarService;
import uz.pdp.backend.service.carservice.CarServiceimp;
import uz.pdp.backend.service.gmailservice.GmailService;
import uz.pdp.backend.service.gmailservice.GmailServiceimp;
import uz.pdp.backend.service.userservice.UserService;
import uz.pdp.backend.service.userservice.UserServiceimp;
import uz.pdp.telegrambot.update.handle.UpdateHandle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author To'lqin Ruzimbayev
 * @since 09/May/2024 20/03/36
 */
public class ThreadSafeBeansContainer {

    public static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static final ThreadLocal<UpdateHandle> handleUpdate = ThreadLocal.withInitial(UpdateHandle::new);
    public static ThreadLocal<GmailService> gmailService = ThreadLocal.withInitial(GmailServiceimp::new);
    public static ThreadLocal<UserService> userService = ThreadLocal.withInitial(UserServiceimp::new);
    public static ThreadLocal<CarSalonService> carSalonService = ThreadLocal.withInitial(CarSalonServiceimp::new);
    public static ThreadLocal<CarService> carService = ThreadLocal.withInitial(CarServiceimp::new);
}