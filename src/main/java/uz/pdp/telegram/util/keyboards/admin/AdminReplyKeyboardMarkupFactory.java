package uz.pdp.telegram.util.keyboards.admin;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

/**
 * @author Mansurbek Boqinazarov
 */
public class AdminReplyKeyboardMarkupFactory {
    public static Keyboard adminMenu(){
        KeyboardButton b1 = new KeyboardButton("REKLAMA");
        KeyboardButton b2 = new KeyboardButton("DELETE CAR");
        KeyboardButton[][] buttons ={{b1,b2}};
        return new ReplyKeyboardMarkup(buttons).resizeKeyboard(true).oneTimeKeyboard(true);
    }
}
