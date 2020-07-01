package my.example.TelegramBot;

import java.io.Serializable;

/**
 * This class is for manipulating Object.
 *
 * @author Lim Wen Liang
 */

public class Object implements Serializable {

    private char letter;
    private int count;

    /**
     * This method is a constructor to initialize Object with parameter.
     *
     * @param letter The letter of your string.
     * @param count  The number of letter occur in your string.
     */

    public Object(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    /**
     * This method is a constructor to initialize Object without parameter.
     */
    public Object() {

    }

    /**
     * This method is for displaying a string.
     *
     * @return The letter and number of letter of your string.
     */

    @Override
    public String toString() {
        return letter + " = " + count;
    }
}
