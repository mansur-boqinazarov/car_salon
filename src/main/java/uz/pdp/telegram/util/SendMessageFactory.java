package uz.pdp.telegram.util;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;

public class SendMessageFactory {

    public static SendMessage sendMessage(Long chatId, String text) {
        return new SendMessage(chatId, text);
    }
    public static SendMessage sendMessage(Long chatId, String text, Keyboard keyboard) {
        SendMessage sendMessage = sendMessage(chatId, text);
        sendMessage.replyMarkup(keyboard);
        return sendMessage;
    }
    public static SendMessage sendMessage(Long chatID,String text , InlineKeyboardMarkup inlineKeyboardMarkup){
        SendMessage sendMessage = sendMessage(chatID, text);
        sendMessage.replyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}