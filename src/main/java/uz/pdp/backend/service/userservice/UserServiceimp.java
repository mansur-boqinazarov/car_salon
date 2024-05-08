package uz.pdp.backend.service.userservice;

import uz.pdp.backend.databases.DataBase;
import uz.pdp.backend.model.user.User;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/07/49
 */

/**
 * Ma'lumotlar bazasidagi User uchun xizmatni taqdim etuvchi klass.
 */
public class UserServiceimp implements UserService{

    private static DataBase<User> dataBase = new DataBase<>();

    private final String FILE_URL = "src/main/resources/databases/User.json";
    private final String FILE_NAME = "user.txt";

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
}
