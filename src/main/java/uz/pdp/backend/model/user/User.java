package uz.pdp.backend.model.user;

import lombok.*;
import uz.pdp.backend.enums.roles.Role;
import uz.pdp.backend.model.basemodel.BaseModel;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseModel {
    private long chatID;
    private Passport passport;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}