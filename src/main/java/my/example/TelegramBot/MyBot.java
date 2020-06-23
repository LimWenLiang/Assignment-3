package my.example.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    static SendMessage message = new SendMessage();
    static String[] temp = new String[100];
    static String output;
    static int i = 0;

    @Override
    public void onUpdateReceived(Update update) {
        String input = update.getMessage().getText();
        freqCount(input);
        message.setChatId(update.getMessage().getChatId());

        Thread thread = new Thread(() -> {
            try {
                execute(message.setText("Thread Name: " + Thread.currentThread().getName()));
                execute(message.setText("Length: " + countLength(input)));

                if (i != 0) {
                    for (int j = 0; j < i; j++) {
                        execute(message.setText(temp[j]));

                        if (j == (i - 1)) {
                            i = 0;
                        }
                    }
                } else {
                    execute(message.setText(output));
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static int countLength(String str) {
        str = str.replace(" ", ""); // remove all spaces
        int length = str.length();
        return length;
    }

    public static void freqCount(String str) {
        str = str.toUpperCase();
        str = str.replace(" ", ""); // remove all spaces
        while (str.length() > 0) {
            char letter = str.charAt(0);
            if (countChar(str, letter) > 3) {
                temp[i] = letter + " = " + countChar(str, letter);
                i++;
            } else {
                output = "Sorry, there has no letter occur more than 3 times.";
            }
            str = str.replace("" + letter, "");
        }

    }

    public static int countChar(String str, char ch) {
        int count = 0;
        while (str.indexOf(ch) != -1) {
            count++;
            str = str.substring((str.indexOf(ch) + 1));
        }
        return count;
    }

    @Override
    public String getBotUsername() {
        return "WenLiang_bot";
    }

    @Override
    public String getBotToken() {
        return "1247728053:AAEEJKNSQ2mjFHNddX6Gzn7AzfA67Y7nlsU";
    }
}
