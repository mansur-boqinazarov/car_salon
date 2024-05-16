package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

/**
 * @author Mansurbek Boqinazarov
 */
public class MessageHandler implements Handler{

    @Override
    public void handle(Update update) {
        Message message = update.message();
        Chat chat = message.chat();
        Long chatID = chat.id();

    }
}
