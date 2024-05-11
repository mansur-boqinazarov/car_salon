package uz.pdp.frontend.util;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 09/10/00
 */
public interface UtilMenu {
    static void utilMainMenu(){
        System.out.println("1. Kirish");
        System.out.println("2. Ro'yxatdan o'tish");
        System.out.println("3. Dasturni tugatish");
    }
    static void utilSuperAdminMenu(){
        System.out.println("1. Avto salonlar ro'yxati");
        System.out.println("2. Avto salon yaratish");
        System.out.println("3. Avto salon o'chirish");
        System.out.println("4. Dasturdan chiqish");
    }
    static void utilAdminMenu(){
        System.out.println("1. Mening avto salonim");
        System.out.println("2. Avtomabil yaratish");
        System.out.println("3. Avtomabilni o'chirish");
        System.out.println("4. Dasturdan chiqish");
    }
}
