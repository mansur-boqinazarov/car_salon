package uz.pdp.back.config;

import com.pengrad.telegrambot.TelegramBot;

import java.util.ResourceBundle;


public class TelegramBotConfiguration {

    private static final ResourceBundle settings = ResourceBundle.getBundle("settings");
    private static final ThreadLocal<TelegramBot> threadSafeBot = ThreadLocal.withInitial(()-> new TelegramBot(settings.getString("bot.token")));

    public static TelegramBot get(){
        return threadSafeBot.get();
    }
}