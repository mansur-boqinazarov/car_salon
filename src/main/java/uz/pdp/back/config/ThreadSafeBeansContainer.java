package uz.pdp.back.config;


import uz.pdp.back.service.address.AddressService;
import uz.pdp.back.service.address.AddressServiceimp;
import uz.pdp.back.service.car.CarService;
import uz.pdp.back.service.car.CarServiceImp;
import uz.pdp.back.service.carModel.CarModelService;
import uz.pdp.back.service.carModel.CarModelServiceimp;
import uz.pdp.back.service.carName.CarNameService;
import uz.pdp.back.service.carName.CarNameServiceimp;
import uz.pdp.back.service.carSalon.CarSalonService;
import uz.pdp.back.service.carSalon.CarSalonServiceimp;
import uz.pdp.back.service.location.LocationService;
import uz.pdp.back.service.location.LocationServiceimp;
import uz.pdp.back.service.passport.PassportService;
import uz.pdp.back.service.passport.PassportServiceimp;
import uz.pdp.back.service.picture.PictureService;
import uz.pdp.back.service.picture.PictureServiceimp;
import uz.pdp.back.service.user.UserService;
import uz.pdp.back.service.user.UserServiceimp;
import uz.pdp.telegram.handlers.CallbackHandler;
import uz.pdp.telegram.handlers.Handler;
import uz.pdp.telegram.handlers.MessageHandler;
import uz.pdp.telegram.processors.callback.OrderProcessor;
import uz.pdp.telegram.processors.callback.SelectSalonMenuProcessor;
import uz.pdp.telegram.processors.message.CarSalonMenuProcessor;
import uz.pdp.telegram.processors.message.DefaultProcessor;
import uz.pdp.telegram.processors.message.GenerateUserPassportProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadSafeBeansContainer {
    public static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static final ThreadLocal<UserService> userService = ThreadLocal.withInitial(UserServiceimp::new);
    public static final ThreadLocal<PassportService> passportService = ThreadLocal.withInitial(PassportServiceimp::new);
    public static final ThreadLocal<AddressService> addressService = ThreadLocal.withInitial(AddressServiceimp::new);
    public static final ThreadLocal<CarService> carService = ThreadLocal.withInitial(CarServiceImp::new);
    public static final ThreadLocal<CarModelService> carModelService = ThreadLocal.withInitial(CarModelServiceimp::new);
    public static final ThreadLocal<CarNameService> carNameService = ThreadLocal.withInitial(CarNameServiceimp::new);
    public static final ThreadLocal<CarSalonService> carSalonService = ThreadLocal.withInitial(CarSalonServiceimp::new);
    public static final ThreadLocal<LocationService> locationService = ThreadLocal.withInitial(LocationServiceimp::new);
    public static final ThreadLocal<PictureService> pictureService = ThreadLocal.withInitial(PictureServiceimp::new);

    // handler
    public static final ThreadLocal<Handler> messageHandler = ThreadLocal.withInitial(MessageHandler::new);
    public static final ThreadLocal<Handler> callbackHandler = ThreadLocal.withInitial(CallbackHandler::new);

    public static final ThreadLocal<OrderProcessor> threadOrderProcessor = ThreadLocal.withInitial(OrderProcessor::new);
    public static final ThreadLocal<SelectSalonMenuProcessor> threadSelectSalonMenuProcessor = ThreadLocal.withInitial(SelectSalonMenuProcessor::new);
    public static final ThreadLocal<CarSalonMenuProcessor> threadCarSalonMenuProcessor = ThreadLocal.withInitial(CarSalonMenuProcessor::new);
    public static final ThreadLocal<DefaultProcessor> threadDefaultProcessor = ThreadLocal.withInitial(DefaultProcessor::new);
    public static final ThreadLocal<GenerateUserPassportProcessor> threadUserPassportGenerateProcessor = ThreadLocal.withInitial(GenerateUserPassportProcessor::new);


}
