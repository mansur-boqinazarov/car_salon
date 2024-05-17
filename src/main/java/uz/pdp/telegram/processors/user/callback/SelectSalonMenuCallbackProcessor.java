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
import uz.pdp.telegram.state.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.SendMessageFactory;

import java.util.ArrayList;
import java.util.List;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

public class SelectSalonMenuCallbackProcessor implements Processor<SelectSalonMenuState> {
    private static TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, SelectSalonMenuState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        System.out.println(callbackQuery.data());
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();

        if (state.equals(SelectSalonMenuState.SELECT_CAR_SALON)){
            salonUUID.put(chatID, callbackQuery.data());
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang...", ReplyKeyboardMarkupFactory.autoSalonMenu());
            bot.execute(sendMessage);
            userState.put(chatID, SelectSalonMenuState.CHOOSE_CAR_SALON_MENU);
        }else if (state.equals(SelectSalonMenuState.SELECT_CAR)) {
            modelID.put(chatID, callbackQuery.data());
            List<Car> carById = carService.get().findCarById(salonUUID.get(chatID));
            List<CarName> carNameList = new ArrayList<>();
            carById.forEach(carID -> carNameList.add(carNameService.get().read(carID.getCarNameID())));
            List<CarName> list = carNameList.stream()
                    .filter(carModelid -> carModelid.getModelID().equals(modelID.get(chatID)))
                    .toList();
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Mashinani tanlang", InlineKeyboardMarkupFactory.listInlineButtons(list));
            bot.execute(sendMessage);
        }else if (state.equals(SelectSalonMenuState.SELECT_CAR_MODEL)) {

        }else if (state.equals(SelectSalonMenuState.CAR_ORDER)) {

        }else if (state.equals(SelectSalonMenuState.BACK)) {

        }else if(state.equals(SelectSalonMenuState.CHOOSE_CAR_SALON_MENU)){

        }
    }
}