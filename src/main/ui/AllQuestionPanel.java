package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.*;

public class AllQuestionPanel extends JPanel {
    private static final int WIDTH = 1000 - 150;
    private static final int HEIGHT = 800;
    private JTextArea quesView;
    private JScrollPane scrollPane;

    public AllQuestionPanel(Quiz q) {
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        quesView = new JTextArea(q.printQuiz());
        quesView.setEditable(false);
        quesView.setBackground(Color.lightGray);
        quesView.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        scrollPane = new JScrollPane(quesView);
        scrollPane.setBounds(5,5,WIDTH - 10,HEIGHT - 50);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}
