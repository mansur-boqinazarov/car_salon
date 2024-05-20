package uz.pdp.telegram.processors.user.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.back.config.TelegramBotConfiguration;
import uz.pdp.back.model.passport.Passport;
import uz.pdp.telegram.processors.Processor;
import uz.pdp.telegram.state.user.GenerateUserPassportState;
import uz.pdp.telegram.state.user.OrderState;
import uz.pdp.telegram.util.keyboards.user.InlineKeyboardMarkupFactory;
import uz.pdp.telegram.util.keyboards.user.SendMessageFactory;

import java.util.Date;

import static uz.pdp.back.config.ThreadSafeBeansContainer.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 16/May/2024 12/36/18
 */
public class GenerateUserPassportMessageProcessor implements Processor<GenerateUserPassportState> {
    private static final TelegramBot bot = TelegramBotConfiguration.get();
    @Override
    public void process(Update update, GenerateUserPassportState state) {
        Message message = update.message();
        Long chatID = message.chat().id();
        if (state.equals(GenerateUserPassportState.GENERATE_PASSPORT)){
            Passport passport = passportMap.get(chatID);
            passportService.get().create(passport);
            for (Passport passport1 : passportService.get().readAll()) {
                if(passport1.equals(passport)){
                    telegramUserService.get().addPassport(chatID, passport.getId());
                }
            }
            telegramUserService.get().setUserState(chatID, OrderState.ORDER_OR_NOT_ORDER);
            SendMessage sendMessage = SendMessageFactory.sendMessage(chatID, "", InlineKeyboardMarkupFactory.orderButton());
            bot.execute(sendMessage);
        }else if (state.equals(GenerateUserPassportState.FIRST_NAME)){
            passportMap.get(chatID).setFirstName(message.text());
            telegramUserService.get().setUserState(chatID, GenerateUserPassportState.LAST_NAME);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Familiyangizni kiriting: "));
        }else if(state.equals(GenerateUserPassportState.LAST_NAME)){
            passportMap.get(chatID).setLastName(message.text());
            telegramUserService.get().setUserState(chatID, GenerateUserPassportState.FATHERS_NAME);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Otangizni ismini kiriting: "));
        }else if(state.equals(GenerateUserPassportState.FATHERS_NAME)){
            passportMap.get(chatID).setFatherName(message.text());
            telegramUserService.get().setUserState(chatID, GenerateUserPassportState.GENDER);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Jinsingizni kiriting: "));
        }else if(state.equals(GenerateUserPassportState.GENDER)){
            passportMap.get(chatID).setGender(message.text());
            telegramUserService.get().setUserState(chatID, GenerateUserPassportState.BIRTH_DATE);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Tug'ilgan sanangizni kiriting: "));
        }else if(state.equals(GenerateUserPassportState.BIRTH_DATE)){
            passportMap.get(chatID).setBirthDate(new Date());
            telegramUserService.get().setUserState(chatID, GenerateUserPassportState.PASSPORT_NUMBER);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Passport raqamini kiriting: "));
        }else if(state.equals(GenerateUserPassportState.PASSPORT_NUMBER)){
            passportMap.get(chatID).setPassportNumber(message.text());
            telegramUserService.get().setUserState(chatID, GenerateUserPassportState.PASSPORT_SERIES);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Passport seriyasini kiriting: "));
        }else if(state.equals(GenerateUserPassportState.PASSPORT_SERIES)){
            passportMap.get(chatID).setPassportSeries(message.text());
            telegramUserService.get().setUserState(chatID, OrderState.ORDER_OR_NOT_ORDER);
            bot.execute(SendMessageFactory.sendMessage(chatID, "Passport ma`lumotlarini qo'shish", InlineKeyboardMarkupFactory.orderButton()));
        }
    }
}
