package uz.pdp.back.model.picture;

import lombok.*;
import uz.pdp.back.model.basemodel.BaseModel;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 10/15/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picture extends BaseModel {
    private String name;
    private String path;
}
