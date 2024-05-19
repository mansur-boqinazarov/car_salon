package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendLocation;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.back.model.location.Location;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/37/41
 */
public class SelectSalonMenuMessageProcessor implements Processor<SelectSalonMenuState> {
    TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, SelectSalonMenuState state) {
        Message message = update.message();
        Long chatID = message.chat().id();
        if(state.equals(SelectSalonMenuState.SELECT_CAR_SALON)){
            List<CarSalon> carSalons = carSalonService.get().readAll();
            telegramUserService.get().setUserState(chatID, SelectSalonMenuState.CHOOSE_CAR_SALON_MENU);
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Avto salon tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carSalons));
            bot.execute(sendMessage);
        }
        else if(state.equals(SelectSalonMenuState.SELECT_CAR)){

        }
        else if(state.equals(SelectSalonMenuState.SELECT_CAR_MODEL)){

        }
        else if(state.equals(SelectSalonMenuState.CAR_ORDER)){

        }
        else if(state.equals(SelectSalonMenuState.MENU_BUTTON)){
            List<String> list = carService.get().findModelIDByCarID(telegramUserService.get().getChooseCarSalon(chatID));
            List<CarModel> carModelList = new ArrayList<>();
            list.forEach(carModelID -> carModelList.add(carModelService.get().read(carModelID)));

            if(Objects.equals(message.text(), "Modellar")){
                telegramUserService.get().setUserState(chatID, SelectSalonMenuState.SELECT_CAR);
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Mashina modelini tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carModelList));
                bot.execute(sendMessage);
            }

            else if(Objects.equals(message.text(), "Salon manzili")){
                String addressID = carSalonService.get().read(telegramUserService.get().getChooseCarSalon(chatID)).getAddressID();
                Location location1 = locationService
                        .get()
                        .readAll()
                        .stream()
                        .filter(location -> Objects.equals(location.getSalonID(), telegramUserService.get().getChooseCarSalon(chatID)))
                        .toList()
                        .get(0);
                String addressString = addressService.get().read(addressID).toString();
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, addressString, ReplyKeyboardMarkupFactory.backButton());
                SendLocation sendLocation = new SendLocation(chatID, (float) location1.getLatitude(), (float) location1.getLongitude());
                bot.execute(sendLocation);
                bot.execute(sendMessage);
                telegramUserService.get().setUserState(chatID, SelectSalonMenuState.MENU_BUTTON);
            }

            else if(Objects.equals(message.text(), "Orqaga")){
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang", ReplyKeyboardMarkupFactory.requestMenuButton());
                telegramUserService.get().setUserState(chatID, SelectSalonMenuState.SELECT_CAR_SALON);
                bot.execute(sendMessage);
            }
            else if(Objects.equals(message.text(), "Back")){
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang", ReplyKeyboardMarkupFactory.autoSalonMenu());
                bot.execute(sendMessage);
            }
        }
        else if(state.equals(SelectSalonMenuState.CHOOSE_CAR_SALON_MENU)){

        }
    }
}