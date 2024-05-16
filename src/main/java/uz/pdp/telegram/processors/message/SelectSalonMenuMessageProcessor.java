package uz.pdp.telegram.processors.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.SelectSalonMenuState;
import uz.pdp.telegram.util.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.SendMessageFactory;

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
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Modelni tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carModelService.get().listBySalonID("96984b03-077c-4a7e-94da-52cd8a0a3785")));
            bot.execute(sendMessage);
        }
        else if(state.equals(SelectSalonMenuState.CAR_ORDER)){

        }
        else if(state.equals(SelectSalonMenuState.BACK)){

        }
        else if(state.equals(SelectSalonMenuState.CHOOSE_CAR_SALON_MENU)){
            if(message.text().equals("Modellar")){
                userState.put(chatID, SelectSalonMenuState.SELECT_CAR_MODEL);
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
