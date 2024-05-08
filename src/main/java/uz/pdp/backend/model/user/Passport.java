package uz.pdp.backend.model.user;

import lombok.*;
import uz.pdp.backend.model.basemodel.BaseModel;

import java.util.Date;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passport extends BaseModel {
    private String firstName;
    private String lastName;
    private String fatherName;
    private Date date;
    private String series;
    private String passportNumber;
}
