import uz.pdp.back.model.basemodel.BaseModel;

import static uz.pdp.back.config.ThreadSafeBeansContainer.pictureService;

/**
 * @author To'lqin Ruzimbayev
 * @since 17/May/2024 11/50/44
 */
public class test {
    public static void main(String[] args) {
        pictureService.get().readAll().forEach(picture -> System.out.println(picture.getId()));
    }
}
