package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.DefaultState;
import uz.pdp.telegram.state.user.SelectSalonMenuState;
import uz.pdp.telegram.util.keyboards.user.ReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import static uz.pdp.back.config.ThreadSafeBeansContainer.telegramUserService;

public class DefaultMessageProcessor implements Processor<DefaultState> {
    private static final TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, DefaultState state) {
        Message message = update.message();
        String text = message.text();
        Long chatID = message.chat().id();
        if(state.equals(DefaultState.SEND_PHONE_NUMBER)){
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menuni tanlang", ReplyKeyboardMarkupFactory.requestMenuButton());
            telegramUserService.get().setUserState(chatID, SelectSalonMenuState.SELECT_CAR_SALON);
            bot.execute(sendMessage);
        }
        else if(state.equals(DefaultState.MAIN_STATE)){

        }
        else if(state.equals(DefaultState.DELETE)){

        }
        else if(state.equals(DefaultState.BASE_USER_MENU)){

        }
    }
}