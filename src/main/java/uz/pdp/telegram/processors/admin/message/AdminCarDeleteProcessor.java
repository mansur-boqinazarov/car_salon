package uz.pdp.telegram.processors.admin.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.model.carsalon.CarSalon;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.admin.DeleteCarState;
import uz.pdp.telegram.state.user.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.ArrayList;
import java.util.List;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;
import static uz.pdp.back.config.ThreadSafeBeansContainer.userState;

/**
 * @author Mansurbek Boqinazarov
 */
public class AdminCarDeleteProcessor implements Processor<DeleteCarState> {
    TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, DeleteCarState state) {
        Message message = update.message();
        String text = message.text();
        Long chatID = message.chat().id();
    }
}
