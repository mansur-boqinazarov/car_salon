package uz.pdp.telegram.handlers.admin;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import uz.pdp.telegram.handlers.Handler;
import uz.pdp.telegram.state.State;
import uz.pdp.telegram.state.admin.DeleteCarState;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class AdminCallBackHandler implements Handler {
    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();
        State state = adminState.get(chatID);
        if (state instanceof DeleteCarState deleteCarState)
            deleteCallbackProcessor.get().process(update, deleteCarState);


    }
}
