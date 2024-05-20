package uz.pdp.telegram.service.user;

import com.pengrad.telegrambot.model.Contact;
import uz.pdp.telegram.model.user.TelegramUser;
import uz.pdp.telegram.state.user.DefaultState;
import uz.pdp.telegram.state.user.State;
import uz.pdp.telegram.util.json.TelegramUserSerializer;

import java.util.ArrayList;
import java.util.List;
public class TelegramUserServiceimp implements TelegramUserService{
    private TelegramUserSerializer serializer = new TelegramUserSerializer();
    @Override
    public void addNumber(Long chatID, Contact contact) {
        System.out.println(contact);
        TelegramUser telegramUser = TelegramUser
                .builder()
                .chatID(chatID)
                .phoneNumber(contact.phoneNumber())
                .firstName(contact.firstName())
                .passportID(null)
                .state(DefaultState.SEND_PHONE_NUMBER)
                .build();
        try {
            TelegramUserSerializer serializer1 = new TelegramUserSerializer();
            List<TelegramUser> telegramUsers = serializer1.readFromFile();
            telegramUsers.add(telegramUser);
            serializer1.writeToFile(telegramUsers);
        }
        catch (NullPointerException e){
            TelegramUserSerializer serializer1 = new TelegramUserSerializer();
            List<TelegramUser> telegramUsers = new ArrayList<>();
            telegramUsers.add(telegramUser);
            serializer1.writeToFile(telegramUsers);
        }
    }

    @Override
    public boolean hasUser(Long chatID) {
        try{
            List<TelegramUser> telegramUsers = serializer.readFromFile();
                return telegramUsers.stream()
                        .filter(user -> user.getChatID().equals(chatID))
                        .toList().size() == 1;
        }
        catch (NullPointerException e){
            return false;
        }
    }


    @Override
    public void setUserState(Long chatID, State state) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        telegramUser.setState(state);
        serializer.writeToFile(telegramUsers);
    }

    @Override
    public State getUserState(Long chatID) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        return telegramUser.getState();
    }

    @Override
    public void addPassport(Long chatID, String passportID) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        telegramUser.setPassportID(passportID);
        serializer.writeToFile(telegramUsers);
    }

    @Override
    public String getPassport(Long chatID) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        return telegramUser.getPassportID();
    }

    @Override
    public void chooseCarSalon(Long chatID, String id) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        telegramUser.setChooseCarSalonID(id);
        serializer.writeToFile(telegramUsers);
    }

    @Override
    public void chooseCar(Long chatID, String id) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        telegramUser.setChooseCarID(id);
        serializer.writeToFile(telegramUsers);
    }

    @Override
    public String getChooseCarSalon(Long chatID) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        return telegramUser.getChooseCarSalonID();
    }

    @Override
    public String getChooseCar(Long chatID) {
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        return telegramUser.getChooseCarSalonID();
    }
    @Override
    public String phoneNumber(Long chatID){
        List<TelegramUser> telegramUsers = serializer.readFromFile();
        TelegramUser telegramUser = telegramUsers.stream().filter(user -> user.getChatID().equals(chatID)).toList().get(0);
        return telegramUser.getPhoneNumber();
    }
}