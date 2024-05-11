package uz.pdp.backend.service.isvalid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 19/06/58
 */
public interface IsValid {
    static boolean isValidPhoneNumber(String phoneNumber) {
        String regexPattern = "^(\\+998)((50|55|77|88|9[0134789])(\\d{7}))$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }
    static boolean isValidPassword(String password) {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[_#@%&*]).{8,}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    static boolean isValidGmail(String gmail) {
        String regexPattern = "^[A-Za-z][A-Za-z0-9._%+-]*@gmail\\.com$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(gmail);
        return matcher.matches();
    }
}
