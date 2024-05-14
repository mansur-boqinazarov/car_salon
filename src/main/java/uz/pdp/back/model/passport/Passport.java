package uz.pdp.back.model.passport;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

import java.time.LocalDate;
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
    private Date birthDate;
    private String gender;
    private String passportNumber;
    private String passportSeries;
}
