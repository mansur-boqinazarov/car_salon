package uz.pdp.back.model.order;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseModel {
    private String userID;
    private String carID;
}