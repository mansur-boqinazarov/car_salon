package uz.pdp.back.config;


import uz.pdp.back.model.carmodel.CarModel;
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


public class Configuration {
    public static final ThreadLocal<UserService> userService = ThreadLocal.withInitial(UserServiceimp::new);
    public static final ThreadLocal<PassportService> passportService = ThreadLocal.withInitial(PassportServiceimp::new);
    public static final ThreadLocal<AddressService> addressService = ThreadLocal.withInitial(AddressServiceimp::new);
    public static final ThreadLocal<CarService> carService = ThreadLocal.withInitial(CarServiceImp::new);
    public static final ThreadLocal<CarModelService> carModelService = ThreadLocal.withInitial(CarModelServiceimp::new);
    public static final ThreadLocal<CarNameService> carNameService = ThreadLocal.withInitial(CarNameServiceimp::new);
    public static final ThreadLocal<CarSalonService> carSalonService = ThreadLocal.withInitial(CarSalonServiceimp::new);
    public static final ThreadLocal<LocationService> locationService = ThreadLocal.withInitial(LocationServiceimp::new);
    public static final ThreadLocal<PictureService> pictureService = ThreadLocal.withInitial(PictureServiceimp::new);
}
