package uz.pdp.telegram.processors.user.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.car.Car;
import uz.pdp.back.model.carname.CarName;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.OrderState;
import uz.pdp.telegram.state.user.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

public class SelectSalonMenuCallbackProcessor implements Processor<SelectSalonMenuState> {
    private static final TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, SelectSalonMenuState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();

        if (state.equals(SelectSalonMenuState.SELECT_CAR_SALON)){

        }
        else if (state.equals(SelectSalonMenuState.SELECT_CAR)) {
            String selectCarModelNameID = callbackQuery.data();
            List<Car> carList = carService
                    .get()
                    .findCarById(telegramUserService.get().getChooseCarSalon(chatID));
            List<CarName> carNameList = new ArrayList<>();
            carList.forEach(car -> {
                if(carNameService.get().read(car.getCarNameID()).getModelID().equals(selectCarModelNameID))
                    carNameList.add(carNameService.get().read(car.getCarNameID()));
            });
            telegramUserService.get().setUserState(chatID, OrderState.GETTING_CONTRACT);
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Mashinani tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carNameList));
            bot.execute(sendMessage);
        }
        else if (state.equals(SelectSalonMenuState.SELECT_CAR_MODEL)) {

        }
        else if (state.equals(SelectSalonMenuState.CAR_ORDER)) {

        }
        else if (state.equals(SelectSalonMenuState.MENU_BUTTON)) {

        }
        else if(state.equals(SelectSalonMenuState.CHOOSE_CAR_SALON_MENU)){
            String carSalonUUID = callbackQuery.data();
            telegramUserService.get().chooseCarSalon(chatID, carSalonUUID);
            telegramUserService.get().setUserState(chatID, SelectSalonMenuState.MENU_BUTTON);
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang", ReplyKeyboardMarkupFactory.autoSalonMenu());
            bot.execute(sendMessage);
        }
    }
}