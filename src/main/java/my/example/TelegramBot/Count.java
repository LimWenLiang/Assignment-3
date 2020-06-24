package my.example.TelegramBot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Count {

    static Object object = new Object();
    static ArrayList<Object> objectList = new ArrayList<>();

    public int length(String str) {
        str = str.replace(" ", ""); // remove all spaces
        int length = str.length();
        return length;
    }

    public void letter(String str) {
        str = str.toUpperCase();
        str = str.replace(" ", ""); // remove all spaces
        while (str.length() > 0) {
            char letter = str.charAt(0);
            if (countChar(str, letter) > 3) {
                object = new Object(letter, countChar(str, letter));
                objectList.add(object);
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

    public void writeObj() throws IOException {
        FileOutputStream fos = new FileOutputStream("Object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
        fos.close();
    }

}
