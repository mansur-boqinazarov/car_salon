package uz.pdp.back.utils;

import java.util.Scanner;

/**
 * @author To'lqin Ruzimbayev
 * @since 14/May/2024 12/33/05
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
