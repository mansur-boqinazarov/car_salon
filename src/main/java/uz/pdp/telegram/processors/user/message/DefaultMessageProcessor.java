package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.DefaultState;
import uz.pdp.telegram.state.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.SendMessageFactory;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

public class DefaultMessageProcessor implements Processor<DefaultState> {
    private static TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, DefaultState state) {
        Message message = update.message();
        String text = message.text();
        Long chatID = message.chat().id();
        if(state.equals(DefaultState.SEND_PHONE_NUMBER)){
            String phoneNumber = message.contact().phoneNumber();
            telegramUser.get().setPhoneNumber(phoneNumber);
            userState.put(chatID, DefaultState.BASE_USER_MENU);
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menulardan birini tanlang", ReplyKeyboardMarkupFactory.requestMenuButton());
            bot.execute(sendMessage);
        }
        else if(state.equals(DefaultState.MAIN_STATE)){
        }
        else if(state.equals(DefaultState.DELETE)){

        }
        else if(state.equals(DefaultState.BASE_USER_MENU)){
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Avto salonlardan birini tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carSalonService.get().readAll()));
            bot.execute(sendMessage);
            userState.put(chatID, SelectSalonMenuState.SELECT_CAR_SALON);
        }
    }
}
