package uz.pdp;

import lombok.SneakyThrows;
import uz.pdp.backend.enums.roles.Role;
import uz.pdp.backend.model.user.Passport;
import uz.pdp.backend.model.user.User;
import uz.pdp.backend.service.gmailservice.GmailService;
import uz.pdp.backend.service.gmailservice.GmailServiceimp;
import uz.pdp.backend.service.userservice.UserService;
import uz.pdp.backend.service.userservice.UserServiceimp;

import java.util.Date;
import java.util.List;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        GmailService gmailService = new GmailServiceimp();
        gmailService.registerMailService("dizzymansur@gmail.com");
    }
}
