package uz.pdp.backend.service.userservice;

import uz.pdp.backend.model.user.User;

import java.util.List;
import java.util.UUID;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 17/07/49
 */
public class UserServiceimp implements UserService{

    private final String urlFile = "src/main/resources/databases/User.json";
    private final String nameFile = "User.json";

    @Override
    public void create(User user) {

    }

    @Override
    public User read(UUID id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<User> readAll() {
        return List.of();
    }
}
