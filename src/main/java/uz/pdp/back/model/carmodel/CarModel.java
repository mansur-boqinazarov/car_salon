package uz.pdp.back.model.carmodel;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModel extends BaseModel{
    private String modelName;
}