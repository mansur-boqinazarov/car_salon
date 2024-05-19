package uz.pdp.telegram.model.picture;

import uz.pdp.back.model.basemodel.BaseModel;

/**
 * @author To'lqin Ruzimbayev
 * @since 17/May/2024 11/53/35
 */
public class TelegramPictureID extends BaseModel {
    private String modelPictureid; // picture.json fayldagi uuidlar
    private Long TelegramPictureID;

    @Override
    public String forCallbackButton() {
        return "";
    }
}
