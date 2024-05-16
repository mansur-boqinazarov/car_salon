package uz.pdp.telegram.buttons.keyboard;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;

public class KeyboardButtons {

    public static Keyboard avtoSalonButton(){
        KeyboardButton button1 = new KeyboardButton("Auto Salons");
        KeyboardButton[][] keyboardButtons = {
                {button1}
        };
        return new ReplyKeyboardMarkup(keyboardButtons).resizeKeyboard(true).oneTimeKeyboard(true);
    }

    public static Keyboard shareContact(){
        KeyboardButton contact = new KeyboardButton("Share Contact");
        contact.requestContact(true);
        return new ReplyKeyboardMarkup(contact).resizeKeyboard(true).oneTimeKeyboard(true);
    }

}
