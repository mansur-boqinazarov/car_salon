package uz.pdp.telegram.processors.user.callback;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.DefaultState;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/35/15
 */
public class DefaultCallbackProcessor implements Processor<DefaultState> {
    @Override
    public void process(Update update, DefaultState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if(state.equals(DefaultState.MAIN_STATE)){

        }
        else if(state.equals(DefaultState.DELETE)){

        }
        else if(state.equals(DefaultState.SEND_PHONE_NUMBER)){

        }
    }
}
