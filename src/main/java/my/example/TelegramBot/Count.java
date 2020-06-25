package my.example.TelegramBot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Count {

    static Object object = new Object();
    static ArrayList<Object> objectList = new ArrayList<>();

    public int length(String string) {
        string = string.replace(" ", ""); // remove all spaces
        int length = string.length();
        return length;
    }

    public void letter(String string) {
        string = string.toUpperCase();
        string = string.replace(" ", ""); // remove all spaces
        while (string.length() > 0) {
            char letter = string.charAt(0);
            if (countChar(string, letter) > 3) {
                object = new Object(letter, countChar(string, letter));
                objectList.add(object);
            }
            string = string.replace("" + letter, "");
        }

    }

    public static int countChar(String string, char ch) {
        int count = 0;
        while (string.indexOf(ch) != -1) {
            count++;
            string = string.substring((string.indexOf(ch) + 1));
        }
        return count;
    }

    public void writeObj() throws IOException {
        FileOutputStream fos = new FileOutputStream("Object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
        fos.close();
    }

}
