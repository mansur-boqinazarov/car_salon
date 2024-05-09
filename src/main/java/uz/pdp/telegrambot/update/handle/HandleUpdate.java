package uz.pdp.telegrambot.update.handle;

import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegrambot.update.CallbackQuery.CallbackQueryHandler;
import uz.pdp.telegrambot.update.TextMessageQuery.MessageHandler;
import uz.pdp.telegrambot.update.basehandle.BaseHandle;

/**
 * Updatelarni handle qilish
 */
public class HandleUpdate {
    private BaseHandle messageHandle;
    private BaseHandle callbackQueryHandle;

    public HandleUpdate() {
        this.messageHandle = new MessageHandler();
        this.callbackQueryHandle = new CallbackQueryHandler();
    }

    public void manage(Update update) {
        if(update.message()!=null){
            this.messageHandle.handle(update);
        }
        else if(update.callbackQuery()!=null){
            this.callbackQueryHandle.handle(update);
        }
    }
}
