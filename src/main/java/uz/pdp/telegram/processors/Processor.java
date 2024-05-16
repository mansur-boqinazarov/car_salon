package uz.pdp.telegram.processors;

import com.pengrad.telegrambot.model.Update;

/**
 * @author Mansurbek Boqinazarov
 */
public interface Processor<S> {
    void process(Update update, S state);
}
