package uz.pdp.back.model.carsalon;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSalon extends BaseModel {

    private String ownerID;
    private String addressID;
    private String carSalonName;
    private String phoneNumberCarSalon;

}