package uz.pdp.telegram.processors.user.callback;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.OrderState;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/37/22
 */
public class OrderCallbackProcessor implements Processor<OrderState> {
    @Override
    public void process(Update update, OrderState state) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (state.equals(OrderState.GETTING_CONTRACT)){

        }else if (state.equals(OrderState.CANCELLATION)){

        }
    }
}
