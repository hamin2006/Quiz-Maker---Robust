package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.*;

// This class creates a panel UI which displays all of the questions in the quiz
public class AllQuestionPanel extends JPanel {
    private static final int WIDTH = 1000 - 150;
    private static final int HEIGHT = 800;
    private JTextArea quesView;
    private JScrollPane scrollPane;

    // EFFECTS: set up new panel to view all questions
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
