package uz.pdp.back.model.address;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 10/19/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseModel {
    private String city;
    private String district;
    private String street;
    private String apartmentNumber;

    @Override
    public String forCallbackButton() {
        return "";
    }
}