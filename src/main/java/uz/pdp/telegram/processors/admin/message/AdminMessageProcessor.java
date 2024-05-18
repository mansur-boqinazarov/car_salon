package uz.pdp.telegram.processors.admin.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.admin.AdminDefaultState;
import uz.pdp.telegram.state.admin.AdminState;

import static uz.pdp.back.config.ThreadSafeBeansContainer.adminState;

/**
 * @author Mansurbek Boqinazarov
 */
public class AdminMessageProcessor implements Processor<AdminState> {
    TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, AdminState state) {
        Message message = update.message();
        String text = message.text();
        Long chatID = message.chat().id();
        if(state.equals(AdminState.REK_SEND_MESSAGE)){
            SendMessage sendMessage = new SendMessage(6773663685L,text);
            bot.execute(sendMessage);
            SendMessage sm = new SendMessage(chatID,"Xabar jo'natildi ✔️");
            bot.execute(sm);
            adminState.put(chatID, AdminDefaultState.ADMIN_MAIN_MENU);
        }
    }
}
