package uz.pdp.backend.service.userservice;

import uz.pdp.backend.databases.DataBase;
import uz.pdp.backend.model.user.User;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;


/**
 * Ma'lumotlar bazasidagi User uchun xizmatni taqdim etuvchi klass.
 */
public class UserServiceimp implements UserService{

    private static DataBase<User> dataBase = new DataBase<>();

    private final String FILE_URL = ResourceBundle.getBundle("files").getString("user.fileurl");
    private final String FILE_NAME = ResourceBundle.getBundle("files").getString("user.filename");

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

    @Override
    public User login(String phoneNumber, String password) {
        List<User> users = readAll();
        return users.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password))
                .toList()
                .get(0);
    }
}