package my.example.TelegramBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainClass {

    public static void main(String[] args) {
        disableWarning();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MyBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for disable the occurring of warning message in the output.
     */

    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
