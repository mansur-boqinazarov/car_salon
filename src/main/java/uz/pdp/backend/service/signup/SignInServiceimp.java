package uz.pdp.backend.service.signup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 09/17/15
 */
public class SignInServiceimp implements SignInService{

    @Override
    public boolean getPhoneNumber(String phoneNumber) {
        return false;
    }

    @Override
    public boolean getEmail(String email) {
        return false;
    }

    @Override
    public boolean getPassword(String password) {
        return false;
    }

    @Override
    public boolean getFirstName(String firstName) {
        return false;
    }

    @Override
    public boolean getLastName(String lastName) {
        return false;
    }

    @Override
    public boolean getFatherName(String fatherName) {
        return false;
    }

    @Override
    public boolean getBirthday(String birthday) {
        return false;
    }

    @Override
    public boolean getPassportSeries(String passportSeries) {
        return false;
    }

    @Override
    public boolean getPassportNumber(String passportNumber) {
        return false;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regexPattern = "^(\\+998)((50|55|77|88|9[0134789])(\\d{7}))$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }
    private boolean isValidPassword(String password) {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[_#@%&*]).{8,}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}