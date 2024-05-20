package uz.pdp.telegram.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author To'lqin Ruzimbayev
 * @since 20/May/2024 07/23/22
 */
public class Shartnoma {
    private static final String shartnoma = "src/main/resources/shartnoma.txt";
    public static String readFileToString(){
        try {
            return new String(Files.readAllBytes(Paths.get(shartnoma)));
        } catch (IOException ignore) {}
        return null;
    }
    public static void writeStringToFIle(String text, String path){
        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ignore) {}
    }
}
