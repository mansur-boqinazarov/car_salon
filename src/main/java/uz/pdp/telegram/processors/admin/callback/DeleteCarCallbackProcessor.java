package uz.pdp.telegram.processors.admin.callback;

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
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.List;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class DeleteCarCallbackProcessor implements Processor<DeleteCarState> {
    TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, DeleteCarState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();
        if (adminState.equals(DeleteCarState.SELECT_SALON)) {
            List<CarSalon> carSalons = carSalonService.get().readAll();
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Salonni tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carSalons));
            bot.execute(sendMessage);
            adminState.put(chatID, DeleteCarState.SELECT_MODEL);
        } else if (adminState.equals(DeleteCarState.SELECT_MODEL)) {
            long selectedSalonId = Long.parseLong(data);
            List<CarModel> carModels = carModelService.get().listBySalonID(Long.toString(selectedSalonId));
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Modelni tanlang", InlineKeyboardMarkupFactory.listInlineButtons(carModels));
            bot.execute(sendMessage);
            adminState.put(chatID, DeleteCarState.SELECT_DELETE_CAR);
        }


    }
}
