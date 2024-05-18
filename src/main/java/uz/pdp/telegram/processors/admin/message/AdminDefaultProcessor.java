package uz.pdp.telegram.processors.admin.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.service.carModel.CarModelService;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.admin.AdminDefaultState;
import uz.pdp.telegram.state.admin.AdminState;
import uz.pdp.telegram.state.admin.DeleteCarState;
import uz.pdp.telegram.state.user.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.List;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;
import static uz.pdp.telegram.util.keyboards.user.SendMessageFactory.sendMessage;

/**
 * @author Mansurbek Boqinazarov
 */
public class AdminDefaultProcessor implements Processor<AdminDefaultState> {
    TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void process(Update update, AdminDefaultState state) {
        Message message = update.message();
        String text = message.text();
        Long chatID = message.chat().id();
        if (state.equals(AdminDefaultState.ADMIN_MAIN_MENU)) {
            if (text.equals("REKLAMA")) {
                adminState.put(chatID, AdminState.REK_SEND_MESSAGE);
                bot.execute(sendMessage(chatID, "Jo'natiladigan reklamani kiriting..."));
            } else if (text.equals("DELETE CAR")) {
                SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Avto salonlardan birini tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carSalonService.get().readAll()));
                adminState.put(chatID, DeleteCarState.SELECT_SALON);
                bot.execute(sendMessage);
            }
        }
    }
}

