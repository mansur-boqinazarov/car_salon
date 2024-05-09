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
    private Passport passportID;
    private String phoneNumber;
    private Role role;
}
