package uz.pdp.frontend.util;

import java.util.Scanner;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 09/03/19
 */
public interface Util {
    static String enterStr(String hint){
        System.out.print(hint);
        return new Scanner(System.in).nextLine();
    }
    static int enterInt(String hint){
        System.out.print(hint);
        return new Scanner(System.in).nextInt();
    }
    static double enterDouble(String hint){
        System.out.print(hint);
        return new Scanner(System.in).nextDouble();
    }
}
