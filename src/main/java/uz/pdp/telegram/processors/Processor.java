package uz.pdp.telegram.processors;

import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.state.State;

/**
 * @author Mansurbek Boqinazarov
 */
public interface Processor<S extends State> {
    void process(Update update, S state);
}
