package uz.pdp.telegrambot.update.handle;

import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegrambot.update.callbackQuery.CallbackQueryHandler;
import uz.pdp.telegrambot.update.textMessageQuery.MessageHandler;
import uz.pdp.telegrambot.update.basehandle.BaseHandle;

/**
 * Updatelarni handle qilish
 */
public class UpdateHandle {
    private BaseHandle messageHandle;
    private BaseHandle callbackQueryHandle;

    public UpdateHandle() {
        this.messageHandle = new MessageHandler();
        this.callbackQueryHandle = new CallbackQueryHandler();
    }

    public void manage(Update update) {
        if (update.message() != null) {
            this.messageHandle.handle(update);
        } else if (update.callbackQuery() != null) {
            this.callbackQueryHandle.handle(update);
        }
    }
}
