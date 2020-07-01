package my.example.TelegramBot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class is for manipulating string.
 *
 * @author Lim Wen Liang
 */

public class Count {

    static Object object = new Object();
    static ArrayList<Object> objectList = new ArrayList<>();

    /**
     * This method is for counting the length of your string.
     *
     * @param string Your string.
     * @return The total length of your string.
     */

    public int length(String string) {
        string = string.replace(" ", ""); // remove all spaces
        int length = string.length();
        return length;
    }

    /**
     * This method is for counting the letter from a string which occur more than 3 times.
     *
     * @param string Your string.
     */

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

    /**
     * This method is for counting the number of letter occur in the string.
     *
     * @param string Your string.
     * @param ch     The letter which occur in your string.
     * @return The number of letter occur in your string.
     */

    public static int countChar(String string, char ch) {
        int count = 0;
        while (string.indexOf(ch) != -1) {
            count++;
            string = string.substring((string.indexOf(ch) + 1));
        }
        return count;
    }

    /**
     * This method is for writing data into a object.
     *
     * @throws IOException
     */

    public void writeObj() throws IOException {
        FileOutputStream fos = new FileOutputStream("Object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
        fos.close();
    }

}
