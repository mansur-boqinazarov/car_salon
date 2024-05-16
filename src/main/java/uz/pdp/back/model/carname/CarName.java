package uz.pdp.back.model.carname;

import lombok.*;
import uz.pdp.back.enums.FuelRoute;
import uz.pdp.back.model.basemodel.BaseModel;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarName extends BaseModel {
    private String modelID;
    private String modelName;
    private String color;
    private FuelRoute fuelRote;

    @Override
    public String forCallbackButton() {
        return modelName;
    }
}