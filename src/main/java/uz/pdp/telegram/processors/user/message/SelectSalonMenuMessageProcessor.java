package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.SendMessageFactory;

import java.util.ArrayList;
import java.util.List;

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

        }
        else if(state.equals(SelectSalonMenuState.SELECT_CAR)){

        }
        else if(state.equals(SelectSalonMenuState.SELECT_CAR_MODEL)){
/*
            List<String> list = carService.get().findModelIDByCarID(salonUUID.get(chatID));
            List<CarModel> carModelList = new ArrayList<>();
            list.forEach(carModelID -> carModelList.add(carModelService.get().read(carModelID)));
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Modelni tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carModelList));
            bot.execute(sendMessage);
            userState.put(chatID, SelectSalonMenuState.SELECT_CAR);
*/
        }
        else if(state.equals(SelectSalonMenuState.CAR_ORDER)){

        }
        else if(state.equals(SelectSalonMenuState.BACK)){

        }
        else if(state.equals(SelectSalonMenuState.CHOOSE_CAR_SALON_MENU)){
            if(message.text().equals("Modellar")){
                List<String> list = carService.get().findModelIDByCarID(salonUUID.get(chatID));
                List<CarModel> carModelList = new ArrayList<>();
                list.forEach(carModelID -> carModelList.add(carModelService.get().read(carModelID)));
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Modelni tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carModelList));
                bot.execute(sendMessage);
                userState.put(chatID, SelectSalonMenuState.SELECT_CAR);
            }
            else if(message.text().equals("Salon manzili")){
                userState.put(chatID, SelectSalonMenuState.CAR_SALON_LOCATION);
            }
            else{
                userState.put(chatID, SelectSalonMenuState.BACK);
            }
            DeleteMessage deleteMessage = new DeleteMessage(chatID, message.messageId());
            bot.execute(deleteMessage);
        }
    }
}
