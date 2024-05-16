package uz.pdp.back.model.location;


import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location extends BaseModel {
    private String salonID;
    private double latitude;
    private double longitude;

    @Override
    public String forCallbackButton() {
        return "";
    }
}
