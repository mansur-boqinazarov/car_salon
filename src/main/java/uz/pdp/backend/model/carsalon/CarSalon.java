package uz.pdp.backend.model.carsalon;

import lombok.*;
import uz.pdp.backend.model.basemodel.BaseModel;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSalon extends BaseModel{
    private UUID adminID;
    private String name;
    private String location;
    private Date openTime;
    private Date closeTime;
    private String phoneNumber;

    @Override
    public String toString() {
        return name ;
    }
}