package uz.pdp.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.telegram.state.State;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    private Long chatID;
    private String phoneNumber;
    private State state;
    private String passportID;
}