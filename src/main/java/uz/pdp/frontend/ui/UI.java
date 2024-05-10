package uz.pdp.frontend.ui;

import static uz.pdp.frontend.util.UtilMenu.*;
import static uz.pdp.frontend.util.Util.*;

/**
 * @author To'lqin Ruzimbayev
 * @since 10/May/2024 09/03/04
 */
public class UI {
    public static void main(String[] args) {

    }
    private static void mainMenu(){
        utilMainMenu();
        switch (enterInt("Tanlang...")){
            case 1 : {}
            case 2 : {}
            case 3 : {return;}
            default: {}
        }
    }
}