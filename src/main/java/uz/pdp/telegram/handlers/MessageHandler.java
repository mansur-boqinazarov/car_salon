package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.state.*;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class MessageHandler implements Handler{

    @Override
    public void handle(Update update) {
        Message message = update.message();
        Chat chat = message.chat();
        Long chatID = chat.id();
        State state = userState.get(chatID);
        if(state == null){

        }
        else if(state instanceof CarSalonMenuState carSalonMenuState) {
            carSalonMenuMessageProcessor.get().process(update, carSalonMenuState);
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
}
