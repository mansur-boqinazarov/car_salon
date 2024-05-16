package uz.pdp.back.model.car;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends BaseModel {
    private String salonID;
    private String carNameID;
    private String pictureID;
    private double price;
    private int year;

    @Override
    public String forCallbackButton() {
        return "";
    }
}
