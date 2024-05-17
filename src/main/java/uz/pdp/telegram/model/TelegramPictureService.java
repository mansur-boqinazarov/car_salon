package uz.pdp.telegram.model;

import uz.pdp.back.service.base.BaseService;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author To'lqin Ruzimbayev
 * @since 17/May/2024 12/01/06
 */
public class TelegramPictureService implements BaseService<TelegramPictureID> {
    private static final String fileurl = ResourceBundle.getBundle("files").getString("files.urlTelegramPicture");
    @Override
    public void create(TelegramPictureID telegramPictureID) {

    }

    @Override
    public TelegramPictureID read(String id) {
        return null;
    }

    @Override
    public void update(TelegramPictureID telegramPictureID) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<TelegramPictureID> readAll() {
        return List.of();
    }
}
