package uz.pdp.telegram.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.telegram.state.user.State;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramUser {
    private Long chatID;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private State state;
    private String passportID;
    private String chooseCarSalonID;
    private String chooseCarID;
}