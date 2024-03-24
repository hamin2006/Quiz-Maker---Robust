package ui;

import java.awt.*;

// Main class which calls on the quiz maker to start
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QuizMakerGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
