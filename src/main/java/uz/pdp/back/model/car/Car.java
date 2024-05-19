package uz.pdp.back.model.car;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;
import uz.pdp.back.model.carmodel.CarModel;
import uz.pdp.back.model.carname.CarName;

import static uz.pdp.back.config.ThreadSafeBeansContainer.carModelService;
import static uz.pdp.back.config.ThreadSafeBeansContainer.carNameService;

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
        CarName carName = carNameService.get().read(getCarNameID());
        String modelID = carName.getModelID();
        CarModel carModel = carModelService.get().read(modelID);
        return "Modeli: %s%nNomi: %s%nIshlab chiqarilgan yili: %s%nYoqilg'i turi: %s%nRangi: %s%nNarxi: %s".formatted(carModel.getModelName(), carName.getModelName(), getYear(), carName.getFuelRote(), carName.getColor(), getPrice());
    }
}
