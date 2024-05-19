package uz.pdp.telegram.handlers.admin;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.NonNull;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.telegram.handlers.Handler;
import uz.pdp.telegram.state.user.State;
import uz.pdp.telegram.state.admin.AdminDefaultState;
import uz.pdp.telegram.state.admin.AdminState;
import uz.pdp.telegram.state.admin.DeleteCarState;
import uz.pdp.telegram.util.keyboards.admin.AdminReplyKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author Mansurbek Boqinazarov
 */
public class AdminMessageHandler implements Handler {
    TelegramBot bot = TelegramBotConfiguration.get();

    @Override
    public void handle(Update update) {
        Message message = update.message();
        Chat chat = message.chat();
        Long chatID = chat.id();
        State state = adminState.get(chatID);

//        bot.execute(new SendPhoto(chatID,"AgACAgIAAxkBAAIEzmZHmjygQxu2x2PizCDjYsmXNtmEAAJX3TEbujRBStV61JeLPWnbAQADAgADbQADNQQ"));
       /* if (message.photo() != null){
            PhotoSize[] photo = message.photo();
            for (PhotoSize photoSize : photo){
                System.out.println(photoSize.fileId());
            }
        }*/

        if (state == null)
            adminMenu(chatID);

          else if (state instanceof AdminDefaultState adminDefaultState)
            adminDefaultMessageProcessor.get().process(update, adminDefaultState);

          else if (state instanceof AdminState adminState)
            adminMessageProcessor.get().process(update, adminState);

        else if (state instanceof DeleteCarState deleteCarState)
            adminDeleteCarProcessor.get().process(update, deleteCarState);

    }

    private void adminMenu(@NonNull Long chatID) {
        adminState.put(chatID, AdminDefaultState.ADMIN_MAIN_MENU);
        SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "Menulardan birini tanlang", AdminReplyKeyboardMarkupFactory.adminMenu());
        bot.execute(sendMessage);
    }
}
