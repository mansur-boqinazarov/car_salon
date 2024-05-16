package uz.pdp.telegram.processors.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.message.MaybeInaccessibleMessage;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.SelectSalonMenuState;
import uz.pdp.telegram.util.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.SendMessageFactory;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

public class SelectSalonMenuCallbackProcessor implements Processor<SelectSalonMenuState> {
    private static TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, SelectSalonMenuState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        System.out.println(callbackQuery.data());
        MaybeInaccessibleMessage maybeInaccessibleMessage = callbackQuery.maybeInaccessibleMessage();
        Message message = (Message) maybeInaccessibleMessage;
        Long chatID = message.chat().id();

        if (state.equals(SelectSalonMenuState.SELECT_CAR_SALON)){
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang...", ReplyKeyboardMarkupFactory.autoSalonMenu());

            bot.execute(sendMessage);
            userState.put(chatID, SelectSalonMenuState.CHOOSE_CAR_SALON_MENU);
        }else if (state.equals(SelectSalonMenuState.SELECT_CAR)) {

        }else if (state.equals(SelectSalonMenuState.SELECT_CAR_MODEL)) {

        }else if (state.equals(SelectSalonMenuState.CAR_ORDER)) {

        }else if (state.equals(SelectSalonMenuState.BACK)) {

        }else if(state.equals(SelectSalonMenuState.CHOOSE_CAR_SALON_MENU)){

        }
    }
}