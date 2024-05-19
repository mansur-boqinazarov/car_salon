package uz.pdp.back.config;


import uz.pdp.back.model.passport.Passport;
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
import uz.pdp.telegram.handlers.*;
import uz.pdp.telegram.handlers.admin.AdminCallBackHandler;
import uz.pdp.telegram.handlers.admin.AdminMessageHandler;
import uz.pdp.telegram.handlers.user.CallbackHandler;
import uz.pdp.telegram.handlers.user.MessageHandler;
import uz.pdp.telegram.model.user.TelegramUser;
import uz.pdp.telegram.processors.admin.callback.DeleteCarCallbackProcessor;
import uz.pdp.telegram.processors.admin.message.AdminCarDeleteProcessor;
import uz.pdp.telegram.processors.admin.message.AdminDefaultProcessor;
import uz.pdp.telegram.processors.admin.message.AdminMessageProcessor;
import uz.pdp.telegram.processors.user.callback.*;
import uz.pdp.telegram.processors.user.message.*;
import uz.pdp.telegram.service.user.TelegramUserService;
import uz.pdp.telegram.service.user.TelegramUserServiceimp;
import uz.pdp.telegram.state.user.State;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadSafeBeansContainer {
    public static final ThreadLocal<UpdateHandler> updateHandler = ThreadLocal.withInitial(UpdateHandler::new);
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
    public static final ThreadLocal<Handler> adminMessageHandler = ThreadLocal.withInitial(AdminMessageHandler::new);
    public static final ThreadLocal<Handler> adminCallbackHandler = ThreadLocal.withInitial(AdminCallBackHandler::new);

    public static final ThreadLocal<DefaultCallbackProcessor> defaultCallbackProcessor = ThreadLocal.withInitial(DefaultCallbackProcessor::new);
    public static final ThreadLocal<GenerateUserPassportCallbackProcessor> generateUserPassportCallbackProcessor = ThreadLocal.withInitial(GenerateUserPassportCallbackProcessor::new);
    public static final ThreadLocal<OrderCallbackProcessor> orderCallbackProcessor = ThreadLocal.withInitial(OrderCallbackProcessor::new);
    public static final ThreadLocal<SelectSalonMenuCallbackProcessor> selectSalonMenuCallbackProcessor = ThreadLocal.withInitial(SelectSalonMenuCallbackProcessor::new);

    public static final ThreadLocal<DefaultMessageProcessor> defaultMessageProcessor = ThreadLocal.withInitial(DefaultMessageProcessor::new);
    public static final ThreadLocal<GenerateUserPassportMessageProcessor> generateUserPassportMessageProcessor = ThreadLocal.withInitial(GenerateUserPassportMessageProcessor::new);
    public static final ThreadLocal<OrderMessageProcessor> orderMessageProcessor = ThreadLocal.withInitial(OrderMessageProcessor::new);
    public static final ThreadLocal<SelectSalonMenuMessageProcessor> selectSalonMenuMessageProcessor = ThreadLocal.withInitial(SelectSalonMenuMessageProcessor::new);


    //admin
    public static final ThreadLocal<AdminDefaultProcessor> adminDefaultMessageProcessor = ThreadLocal.withInitial(AdminDefaultProcessor::new);
    public static final ThreadLocal<AdminMessageProcessor> adminMessageProcessor = ThreadLocal.withInitial(AdminMessageProcessor::new);
    public static final ThreadLocal<AdminCarDeleteProcessor> adminDeleteCarProcessor = ThreadLocal.withInitial(AdminCarDeleteProcessor::new);
    public static final ThreadLocal<DeleteCarCallbackProcessor> deleteCallbackProcessor = ThreadLocal.withInitial(DeleteCarCallbackProcessor::new);
    public static final ConcurrentHashMap<Long, State> userState = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Long, State> adminState = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Long, String> salonUUID = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Long, String> modelID = new ConcurrentHashMap<>();

    public static final ThreadLocal<TelegramUser> telegramUser = ThreadLocal.withInitial(TelegramUser::new);

    public static final ThreadLocal<TelegramUserService> telegramUserService = ThreadLocal.withInitial(TelegramUserServiceimp::new);
    public static final ConcurrentHashMap<Long, Passport> passportMap = new ConcurrentHashMap<>();
}