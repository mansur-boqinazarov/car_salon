package uz.pdp.frontend.myBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.frontend.update.handle.HandleUpdate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyBot {
    public static final TelegramBot bot = new TelegramBot("7018575739:AAHRxtQvBDXp-iXJN_ye-mObg-tb2gNHr8s");
    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        bot.setUpdatesListener(updates -> {
                    for (Update update : updates) {
                        CompletableFuture.runAsync(() -> {
                            HandleUpdate.update(update);
                        }, executorService);
                    }
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                }
        );
    }
}