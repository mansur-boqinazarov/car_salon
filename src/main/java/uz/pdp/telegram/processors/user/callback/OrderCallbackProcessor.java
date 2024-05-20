package uz.pdp.telegram.processors.user.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.passport.Passport;
import uz.pdp.back.service.car.CarService;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.service.Shartnoma;
import uz.pdp.telegram.state.user.GenerateUserPassportState;
import uz.pdp.telegram.state.user.OrderState;
import uz.pdp.telegram.state.user.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import javax.naming.spi.InitialContextFactory;
import java.io.File;
import java.util.List;
import java.util.Objects;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/37/22
 */
public class OrderCallbackProcessor implements Processor<OrderState> {
    private static final TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, OrderState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();
        if (state.equals(OrderState.GETTING_CONTRACT)){
            String carNameID = callbackQuery.data();
            List<Car> carList = carService
                    .get()
                    .findCarById(telegramUserService.get().getChooseCarSalon(chatID));
            Car car = carList.stream()
                    .filter(car1 -> Objects.equals(car1.getCarNameID(), carNameID))
                    .toList()
                    .get(0);
            String path = pictureService.get().read(car.getPictureID()).getPath();
            telegramUserService.get().setUserState(chatID, OrderState.ORDER_OR_NOT_ORDER);
            SendPhoto sendPhoto = new SendPhoto(chatID, new File(path));
            bot.execute(sendPhoto);
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, car.forCallbackButton(), InlineKeyboardMarkupFactory.backOrOrderButton());
            bot.execute(sendMessage);
        }else if (state.equals(OrderState.CANCELLATION)){

        }
        else if(state.equals(OrderState.ORDER_OR_NOT_ORDER)){
            if (callbackQuery.data().equals("back")) {
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang", ReplyKeyboardMarkupFactory.autoSalonMenu());
                bot.execute(sendMessage);
            }
            else if(callbackQuery.data().equals("shartnoma")){
                try{
                    Passport passport1 = passportMap.get(chatID);
                    passportService.get().create(passport1);
                            telegramUserService.get().addPassport(chatID, passport1.getId());
                }catch (Exception e){}
                finally {
                    if(telegramUserService.get().getPassport(chatID) == null){
                        passportMap.put(chatID, new Passport());
                        telegramUserService.get().setUserState(chatID, GenerateUserPassportState.FIRST_NAME);
                        bot.execute(SendMessageFactory.sendMessage(chatID, "Ismingizni kiriting: "));
                    }
                    else{
                        String carSalonID = telegramUserService.get().getChooseCarSalon(chatID);
                        String carSalonName = carSalonService.get().read(carSalonID).getCarSalonName();
                        String addressID = carSalonService.get().read(carSalonID).getAddressID();
                        String address = addressService.get().read(addressID).toString();
                        String phoneNumberCarSalon = carSalonService.get().read(carSalonID).getPhoneNumberCarSalon();
                        String passportID = telegramUserService.get().getPassport(chatID);
                        Passport passport = passportService.get().read(passportID);
                        String firstName = passport.getFirstName();
                        String lastName = passport.getLastName();
                        String fatherName = passport.getFatherName();
                        String phoneNumber = telegramUserService.get().phoneNumber(chatID);
                        String carNameID = telegramUserService.get().getChooseCar(chatID);
                        String carName = carNameService.get().read(carNameID).getModelName();
                        String modelID1 = carNameService.get().read(carNameID).getModelID();
                        String modelName = carModelService.get().read(modelID1).getModelName();
                        String carID = telegramUserService.get().getChooseCar(chatID);
                        double price = carService.get().read(carID).getPrice();
                        String newShartnome = "Avtomobil Sotib Olish Shartnomasi%nSotuvchi:%nNom: %s%nManzil: %s%nTelefon: %s%nSotib oluvchi:%nIsmi: %s%nFamiliyasi: %s%nOtasining ismi: %s%nTelefon: %s%nShartnoa Predmeti:%nAvtomobil nomi: %s%nModeli: %s%nYili: %s%nRangi: %sShartnoma Shartlari:%nUmumiy Qoidalar%nUshbu shartnoma 20.05.2024 kuni tuzildi va tomonlar o'rtasidagi barcha huquq va majburiyatlarni belgilaydi.%nTo'lov Shartlari%nAvtomobilning umumiy narxi: %s so'm.%nTo'lov tartibi: Naqd (to'lov usuli va muddati ko'rsatiladi).%nTopshirish-Qabul Qilish Shartlari%nAvtomobilni qabul qilish muddati: 06.06.2024.%nSotuvchi avtomobilni sotib oluvchiga texnik jihatdan soz va hujjatlari to'liq holda topshiradi%nTomonlarning Majburiyatlari%nSotuvchi avtomobilning sifati va hujjatlarining haqiqiyligi uchun javob beradi.%nSotib oluvchi avtomobil uchun to'lovni o'z vaqtida amalga oshirishi lozim.%nBoshqa Shartlar%nShartnoma 2 (ikki) nusxada tuzilgan bo'lib, har bir nusxa bir xil huquqiy kuchga ega.%nTomonlar shartnoma bo'yicha kelishmovchiliklarni muzokaralar yo'li bilan hal qilishga harakat qiladi. Agar muzokaralar yo'li bilan hal etilmasa, nizo amaldagi qonunchilikka muvofiq hal qilinadi.%nImzolar:%nSotuvchi:%nImzo: _______________%nSana: _______________%nSotib oluvchi:%nImzo: _______________%nSana: _______________".formatted(carSalonName, address, phoneNumberCarSalon, firstName, lastName, fatherName,
                                phoneNumber, carName, modelName, "2024", "White", price);
                    }

                }
            }
        }
    }
}
