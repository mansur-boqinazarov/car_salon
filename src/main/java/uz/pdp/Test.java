package uz.pdp;

import uz.pdp.backend.enums.roles.Role;
import uz.pdp.backend.model.user.Passport;
import uz.pdp.backend.model.user.User;
import uz.pdp.backend.service.userservice.UserService;
import uz.pdp.backend.service.userservice.UserServiceimp;

import java.util.Date;

/**
 * @author To'lqin Ruzimbayev
 * @since 08/May/2024 20/25/30
 */
public class Test {
    public static void main(String[] args) {
        UserService userService = new UserServiceimp();
        userService.create(
                new User(new Passport("To'lqin", "Ruzimbayev", "O'tkir o'g'li", new Date(), "KA", "0997251"), "+998977152600", Role.USER)
        );
    }
}
