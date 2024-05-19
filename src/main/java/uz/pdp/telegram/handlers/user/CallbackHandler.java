package uz.pdp.telegram.handlers.user;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import uz.pdp.telegram.handlers.Handler;
import uz.pdp.telegram.state.user.*;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class CallbackHandler implements Handler {
    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();
        State state = telegramUserService.get().getUserState(chatID);
        if(state instanceof DefaultState defaultState){
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
