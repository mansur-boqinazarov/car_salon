package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.GenerateUserPassportState;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/36/18
 */
public class GenerateUserPassportMessageProcessor implements Processor<GenerateUserPassportState> {
    @Override
    public void process(Update update, GenerateUserPassportState state) {
        Message message = update.message();
        if (state.equals(GenerateUserPassportState.GENERATE_PASSPORT)){

        }else if (state.equals(GenerateUserPassportState.FIRST_NAME)){

        }else if(state.equals(GenerateUserPassportState.LAST_NAME)){

        }else if(state.equals(GenerateUserPassportState.FATHERS_NAME)){

        }else if(state.equals(GenerateUserPassportState.GENDER)){

        }else if(state.equals(GenerateUserPassportState.BIRTH_DATE)){

        }else if(state.equals(GenerateUserPassportState.PASSPORT_NUMBER)){

        }else if(state.equals(GenerateUserPassportState.PASSPORT_SERIES)){

        }
    }
}
