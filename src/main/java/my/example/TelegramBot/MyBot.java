package my.example.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * This class is for manipulating your Telegram Bot.
 *
 * @author Lim Wen Liang
 */

public class MyBot extends TelegramLongPollingBot {

    static SendMessage message = new SendMessage();
    static String output = "Sorry, there have no letter occur more than 3 times.";
    static ArrayList<Object> objectList = new ArrayList<>();

    /**
     * This method is for manipulating the message received and displayed from Telegram Bot.
     *
     * @param update The message received from Telegram Bot.
     */

    @Override
    public void onUpdateReceived(Update update) {
        Count count = new Count();
        String input = update.getMessage().getText();
        message.setChatId(update.getMessage().getChatId());

        Thread thread = new Thread(() -> {
            try {
                int temp = objectList.size();
                count.letter(input);
                count.writeObj();
                readObj();

                execute(message.setText("Message: " + input + "\n"));
                execute(message.setText("Length of the message: " + count.length(input)));
                execute(message.setText("Occurance of letter:"));

                if (objectList.size() > temp) {
                    for (int i = temp; i < objectList.size(); i++) {
                        execute(message.setText(String.valueOf(objectList.get(i))));
                        temp++;
                    }

                } else if (objectList.size() == temp) {
                    execute(message.setText(output));
                }

                execute(message.setText("Execute by Thread: " + Thread.currentThread().getName()));
                System.out.println(input);

            } catch (TelegramApiException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * This method is for reading data from a object.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static void readObj() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("Object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        objectList = (ArrayList<Object>) ois.readObject();

        ois.close();
        fis.close();
    }

    /**
     * This method is for getting the username of your Telegram Bot.
     *
     * @return Your username of your Telegram Bot.
     */

    @Override
    public String getBotUsername() {
        return "261938_bot";
    }

    /**
     * This method is for getting the token of your Telegram Bot.
     *
     * @return Your token of your Telegram Bot.
     */

    @Override
    public String getBotToken() {
        return "1255888791:AAGJvWOH2kUTWA8IT2GSnAN-rAHQS6VA9Vk";
    }
}
