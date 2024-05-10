package uz.pdp.telegrambot.runner;

import  com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static uz.pdp.config.ThreadSafeBeansContainer.executor;
import static uz.pdp.config.ThreadSafeBeansContainer.handleUpdate;

public class TelegramBotRunner {

    public static final TelegramBot bot = new TelegramBot(ResourceBundle.getBundle("settings").getString("bot.token"));

    public static void main(String[] args) {
        bot.setUpdatesListener(updates -> {

                    for (Update update : updates) {
                        CompletableFuture.runAsync(() -> {
                            handleUpdate.get().manage(update);
                        }, executor);
                    }

                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                }
        );
    }
}