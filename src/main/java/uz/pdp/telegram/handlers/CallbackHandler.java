package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;

/**
 * @author Mansurbek Boqinazarov
 */
public class CallbackHandler implements Handler{
    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatID = update.message().chat().id();
        String data = callbackQuery.data();
    }
}
