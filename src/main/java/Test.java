import static uz.pdp.back.config.ThreadSafeBeansContainer.pictureService;

public class Test {
    public static void main(String[] args) {
        pictureService.get().readAll().forEach(picture -> System.out.println(picture.getId()));
    }
}
