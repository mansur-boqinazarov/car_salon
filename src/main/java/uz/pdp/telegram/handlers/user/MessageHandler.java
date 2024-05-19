package uz.pdp.telegram.handlers.user;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.NonNull;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.handlers.Handler;
import uz.pdp.telegram.state.user.*;
import uz.pdp.telegram.util.keyboards.user.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.concurrent.ConcurrentHashMap;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class MessageHandler implements Handler {

    private final TelegramBot bot = TelegramBotConfiguration.get();
    private static ConcurrentHashMap<Long, Boolean> light = new ConcurrentHashMap<>();

    @Override
    public void handle(Update update) {
        Message message = update.message();
        Chat chat = message.chat();
        Long chatID = chat.id();
        if(!light.containsKey(chatID)) {
            light.put(chatID, false);
        }
        else if(light.get(chatID)) {
            telegramUserService.get().addNumber(chatID, message.contact());
        }
        if(update.message().text().equals("/start")){
            if(! telegramUserService.get().hasUser(chatID) && !light.get(chatID)){
                requestPhoneNumber(chatID);
            }
            else{
                telegramUserService.get().setUserState(chatID, DefaultState.SEND_PHONE_NUMBER);
                defaultMessageProcessor.get().process(update, (DefaultState) telegramUserService.get().getUserState(chatID));
            }
        }
        else{
            State state = telegramUserService.get().getUserState(chatID);
            if(state instanceof DefaultState defaultState){
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
    }

    private void requestPhoneNumber(@NonNull Long chatID) {
        userState.put(chatID, DefaultState.SEND_PHONE_NUMBER);
        SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Iltimos telefon raqamingizni jo'nating", ReplyKeyboardMarkupFactory.requestPhoneNumber());
        bot.execute(sendMessage);
        light.put(chatID, true);
    }
}