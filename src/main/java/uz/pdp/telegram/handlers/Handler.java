package uz.pdp.telegram.handlers;

import com.pengrad.telegrambot.model.Update;

/**
 * @author Mansurbek Boqinazarov
 */
public interface Handler {
    void handle(Update update);
}
