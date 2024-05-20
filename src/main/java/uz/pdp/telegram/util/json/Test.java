package uz.pdp.telegram.util.json;

import uz.pdp.telegram.model.user.TelegramUser;
import uz.pdp.telegram.state.user.DefaultState;

import java.util.List;

/**
 * @author To'lqin Ruzimbayev
 * @since 19/May/2024 13/31/18
 */
public class Test {
    public static void main(String[] args) {
        TelegramUserSerializer serializer = new TelegramUserSerializer();
        List<TelegramUser> telegramUsers = List.of(new TelegramUser(1L, "1", "1", "1", DefaultState.SEND_PHONE_NUMBER, "1", "1", "1"),
                new TelegramUser(2L, "2", "2", "2", DefaultState.BASE_USER_MENU, "2", "2", "2"));
        serializer.writeToFile(telegramUsers);
    }
}
