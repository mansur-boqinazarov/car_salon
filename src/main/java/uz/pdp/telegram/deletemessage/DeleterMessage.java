package uz.pdp.telegram.deletemessage;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import uz.pdp.back.config.TelegramBotConfiguration;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 18/47/07
 */
public class DeleterMessage {
    private static TelegramBot bot = TelegramBotConfiguration.get();
    public static void deleteMessage(Update update) {
        if (update.message() != null)
            bot.execute(new DeleteMessage(update.message().chat().id(), update.message().messageId()));

    }
}
