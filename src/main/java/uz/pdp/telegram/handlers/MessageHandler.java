package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.NonNull;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.state.*;
import uz.pdp.telegram.util.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.SendMessageFactory;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class MessageHandler implements Handler{

    private final TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void handle(Update update) {
        Message message = update.message();
        Chat chat = message.chat();
        Long chatID = chat.id();
        State state = userState.get(chatID);
        System.out.println(state);
        if(state == null){
            requestPhoneNumber(chatID);
        }
        else if(state instanceof DefaultState defaultState){
            defaultMessageProcessor.get().process(update, defaultState);
        }
        else if(state instanceof GenerateUserPassportState generateUserPassportState){
            generateUserPassportMessageProcessor.get().process(update, generateUserPassportState);
        }
        else if(state instanceof OrderState orderState){
            orderMessageProcessor.get().process(update, orderState);
        }
        else if(state instanceof SelectSalonMenuState selectSalonMenuState){
            selectSalonMenuMessageProcessor.get().process(update, selectSalonMenuState);
        }
    }

    private void requestPhoneNumber(@NonNull Long chatID) {
        userState.put(chatID, DefaultState.SEND_PHONE_NUMBER);
        SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Iltimos telefon raqamingizni jo'nating", ReplyKeyboardMarkupFactory.requestPhoneNumber());
        bot.execute(sendMessage);
    }
}
