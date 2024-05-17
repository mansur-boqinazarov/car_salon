package uz.pdp.telegram.util.keyboards;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 14/56/20
 */
public class ReplyKeyboardMarkupFactory extends Keyboard{
    public static Keyboard requestPhoneNumber(){
        KeyboardButton button = new KeyboardButton("☎️Kontaktni jo'natish");
        button.requestContact(true);
        return new ReplyKeyboardMarkup(button).resizeKeyboard(true).oneTimeKeyboard(true);
    }
    public static Keyboard requestMenuButton(){
        KeyboardButton button = new KeyboardButton("️Avto salonlar ro'yxati");
        return new ReplyKeyboardMarkup(button).resizeKeyboard(true).oneTimeKeyboard(true);
    }
    public static Keyboard autoSalonMenu(){
        KeyboardButton b1 = new KeyboardButton("Modellar");
        KeyboardButton b2 = new KeyboardButton("Salon manzili");
        KeyboardButton b3 = new KeyboardButton("Orqaga");
        KeyboardButton[][] buttons ={{b1},{b2},{b3}};
        return new ReplyKeyboardMarkup(buttons).resizeKeyboard(true).oneTimeKeyboard(true);
    }
}
