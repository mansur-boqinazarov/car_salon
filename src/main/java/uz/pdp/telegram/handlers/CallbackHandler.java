package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.state.*;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class CallbackHandler implements Handler{
    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatID = update.message().chat().id();
        State state = userState.get(chatID);
        if(state instanceof CarSalonMenuState carSalonMenuState){
            carSalonMenuCallbackProcessor.get().process(update, carSalonMenuState);
        }
        else if(state instanceof DefaultState defaultState){
            defaultCallbackProcessor.get().process(update, defaultState);
        }
        else if(state instanceof GenerateUserPassportState generateUserPassportState){
            generateUserPassportCallbackProcessor.get().process(update, generateUserPassportState);
        }
        else if(state instanceof OrderState orderState){
            orderCallbackProcessor.get().process(update, orderState);
        }
        else if(state instanceof SelectSalonMenuState selectSalonMenuState) {
            selectSalonMenuCallbackProcessor.get().process(update, selectSalonMenuState);
        }
    }
}
