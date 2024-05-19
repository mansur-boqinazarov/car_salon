package uz.pdp.telegram.processors.user.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.passport.Passport;
import uz.pdp.back.service.car.CarService;
import uz.pdp.telegram.processors.Processor;
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
                if(telegramUserService.get().getPassport(chatID) == null){
                    passportMap.put(chatID, new Passport());
                    telegramUserService.get().setUserState(chatID, GenerateUserPassportState.FIRST_NAME);
                    bot.execute(SendMessageFactory.sendMessage(chatID, "Ismingizni kiriting: "));
                }
                else{

                }
            }
        }
    }
}
