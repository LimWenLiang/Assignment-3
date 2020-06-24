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
    static String[] temp = new String[100];
    static String output = "Sorry, there has no letter occur more than 3 times.";
    static int i = 0;
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

                execute(message.setText("Thread Name: " + Thread.currentThread().getName()));
                execute(message.setText("Length: " + count.length(input)));

                if (objectList.size() > temp) {
                    for (int i = temp; i < objectList.size(); i++) {
                        execute(message.setText(String.valueOf(objectList.get(i))));
                        temp++;
                    }

                } else if (objectList.size() == temp) {
                    execute(message.setText(output));
                }
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
        return "wenliang_bot";
    }

    @Override
    public String getBotToken() {
        return "1160848603:AAFX06cH4Udc7xyRR7PCoHj8Ljpm3AqRsU0";
    }
}