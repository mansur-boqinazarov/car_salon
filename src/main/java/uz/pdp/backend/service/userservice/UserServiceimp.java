package uz.pdp.backend.service.userservice;

import uz.pdp.backend.databases.DataBase;
import uz.pdp.backend.model.user.User;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/07/49
 */

/**
 * Ma'lumotlar bazasidagi User uchun xizmatni taqdim etuvchi klass.
 */
public class UserServiceimp implements UserService{

    private static DataBase<User> dataBase = new DataBase<>();

    private final String FILE_URL = ResourceBundle.getBundle("settings").getString("user.fileurl");
    private final String FILE_NAME = ResourceBundle.getBundle("Settings").getString("user.filename");

    /**
     * User yaratish.
     */
    @Override
    public void create(User user) {
        dataBase.SAVE(user, FILE_URL);
    }

    /**
     * Userni tekshirish.
     */
    @Override
    public User read(UUID id) {
        return dataBase.GET(id, FILE_URL, User.class);
    }
    /**
     * Userni o'zgartirish.
     */
    @Override
    public void update(User user) {

    }
    /**
     * Userni o'chirish.
     */
    @Override
    public void delete(UUID id) {
        dataBase.DELETE(id, FILE_URL, FILE_NAME, User.class);
    }
    /**
     * Barcha Userlarni tekshirish.
     */
    @Override
    public List<User> readAll() {
        return dataBase.GET_ALL(FILE_URL, User.class);
    }
    /**
     * phoneNumberni check qilib qaytaradi
     */

    public boolean isValidPhoneNumber(String phoneNumber) {
        String regexPattern = "^(\\+998)((50|55|77|88|9[0134789])(\\d{7}))$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }

    /**
     * passwordni check qilib qaytaradi
     */
    public boolean isValidPassword(String password) {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[_#@%&*]).{8,}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}