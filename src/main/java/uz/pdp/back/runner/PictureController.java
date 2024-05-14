package uz.pdp.back.runner;

import uz.pdp.back.model.picture.Picture;

import static uz.pdp.back.config.Configuration.pictureService;
import static uz.pdp.back.utils.Util.enterInt;
import static uz.pdp.back.utils.Util.enterStr;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 13/45/57
 */
public class PictureController {
    public static void start(){
        System.out.println("1. Rasm qo'shish");
        System.out.println("2. Dasturni tugatish");
        if(enterInt("Tanlang...") == 1){
            String name = enterStr("Rasm nomini kiriting(Mashina modeli):");
            String path = enterStr("Rasm path ini kiriting: ");
            pictureService.get().create(new Picture(name, path));
        }
    }
}
