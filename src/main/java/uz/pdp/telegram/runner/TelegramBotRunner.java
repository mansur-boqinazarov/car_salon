package uz.pdp.telegram.runner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.back.config.TelegramBotConfiguration;


import java.util.concurrent.CompletableFuture;

import static uz.pdp.back.config.ThreadSafeBeansContainer.executor;
import static uz.pdp.back.config.ThreadSafeBeansContainer.updateHandler;
public class TelegramBotRunner {
    public static void main(String[] args) {
        TelegramBot bot = TelegramBotConfiguration.get();
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                CompletableFuture.runAsync(() -> {
                    updateHandler.get().handle(update);
                }, executor);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, Throwable::printStackTrace);
    }
}