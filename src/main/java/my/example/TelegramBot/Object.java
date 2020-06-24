package my.example.TelegramBot;

import java.io.Serializable;

public class Object implements Serializable {

    private char letter;
    private int count;

    public Object(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public Object() {

    }

    @Override
    public String toString() {
        return letter + " = " + count;
    }
}
