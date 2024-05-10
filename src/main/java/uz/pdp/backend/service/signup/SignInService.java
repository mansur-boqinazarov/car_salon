package uz.pdp.backend.service.signup;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 09/17/04
 */
public interface SignInService {
    boolean getPhoneNumber(String phoneNumber);
    boolean getEmail(String email);
    boolean getPassword(String password);
    boolean getFirstName(String firstName);
    boolean getLastName(String lastName);
    boolean getFatherName(String fatherName);
    boolean getBirthday(String birthday);
    boolean getPassportSeries(String passportSeries);
    boolean getPassportNumber(String passportNumber);
}