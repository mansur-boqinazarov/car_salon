package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.OrderState;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/37/22
 */
public class OrderMessageProcessor implements Processor<OrderState> {
    @Override
    public void process(Update update, OrderState state) {
        Message message = update.message();
        if(state.equals(OrderState.GETTING_CONTRACT)){

        }
        else if(state.equals(OrderState.CANCELLATION)){

        }
    }
}
