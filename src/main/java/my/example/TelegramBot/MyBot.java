package my.example.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MyBot extends TelegramLongPollingBot {

    static SendMessage message = new SendMessage();
    static String output = "Sorry, there have no letter occur more than 3 times.";
    static ArrayList<Object> objectList = new ArrayList<>();


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

            } catch (TelegramApiException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static void readObj() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("Object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        objectList = (ArrayList<Object>) ois.readObject();

        ois.close();
        fis.close();
    }

    @Override
    public String getBotUsername() {
        return "261938_bot";
    }

    @Override
    public String getBotToken() {
        return "1255888791:AAGJvWOH2kUTWA8IT2GSnAN-rAHQS6VA9Vk";
    }
}
