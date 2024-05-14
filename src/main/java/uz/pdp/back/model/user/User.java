package uz.pdp.back.model.user;

import lombok.*;
import uz.pdp.back.enums.Role;
import uz.pdp.back.model.basemodel.BaseModel;


/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 10/12/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseModel {
    private String gmail;
    private String password;
    private String passportID;
    private Role role;
}