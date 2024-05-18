package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.model.Update;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;
import static uz.pdp.back.config.ThreadSafeBeansContainer.callbackHandler;
import static uz.pdp.back.config.ThreadSafeBeansContainer.messageHandler;

/**
 * @author Mansurbek Boqinazarov
 */
public class UpdateHandler {
    public void handle(Update update) {
        CompletableFuture.runAsync(() -> {
            executor.submit(() -> {
                Long chatID = update.message().chat().id();
                if (Objects.equals(chatID,5127045086L) && Objects.nonNull(update.message()))
                    adminMessageHandler.get().handle(update);

                else if (Objects.equals(chatID, 5127045086L) && Objects.nonNull(update.callbackQuery()))
                    adminCallbackHandler.get().handle(update);

                else if (Objects.nonNull(update.message()))
                    messageHandler.get().handle(update);

                else if (Objects.nonNull(update.callbackQuery()))
                    callbackHandler.get().handle(update);

            });
        });
    }
}
