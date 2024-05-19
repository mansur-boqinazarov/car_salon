package uz.pdp.telegram.service.user;

import com.pengrad.telegrambot.model.Contact;
import uz.pdp.telegram.state.user.State;

public interface TelegramUserService {
    void addNumber(Long chatID, Contact contact);
    boolean hasUser(Long chatID);
    void setUserState(Long chatID, State state);
    State getUserState(Long chatID);
    void addPassport(Long chatID, String passportID);
    String getPassport(Long chatID);
    void chooseCarSalon(Long chatID, String id);
    void chooseCar(Long chatID, String id);
    String getChooseCarSalon(Long chatID);
    String getChooseCar(Long chatID);
}