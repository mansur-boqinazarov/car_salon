package uz.pdp.frontend.update.handle;

import com.pengrad.telegrambot.model.Update;
import uz.pdp.frontend.update.CallbackQuery.CallbackQueryHandler;
import uz.pdp.frontend.update.TextMessageQuery.TextMessageHandler;

/**
 * Updatelarni handle qilish
 */
public class HandleUpdate {
    public static void update(Update update) {
        if (update.callbackQuery() != null) {
            CallbackQueryHandler.handle(update.callbackQuery());
        } else if (update.message() != null && update.message().text() != null) {
            TextMessageHandler.handle(update.message());
        }
    }
}
