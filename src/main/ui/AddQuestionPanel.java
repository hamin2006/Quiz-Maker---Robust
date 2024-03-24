package ui;

import model.Question;
import model.Quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddQuestionPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private Quiz quiz;
    private JLabel question;
    private JLabel optionA;
    private JLabel optionB;
    private JLabel optionC;
    private JLabel optionD;
    private JLabel ans;
    private JTextArea questionWrite;
    private JTextArea optionAWrite;
    private JTextArea optionBWrite;
    private JTextArea optionCWrite;
    private JTextArea optionDWrite;
    private JTextArea ansWrite;
    private JButton addQ;
    private QuizMakerGUI gui;

    public AddQuestionPanel(QuizMakerGUI gui) {
        this.gui = gui;
        setSize(WIDTH - 150,HEIGHT);
        setLayout(null);
        question = new JLabel("Question");
        question.setBounds((WIDTH / 2) - 300,HEIGHT - 700,75,20);

        optionA = new JLabel("Option A");
        optionA.setBounds((WIDTH / 2) - 300,HEIGHT - 650,75,20);
        optionB = new JLabel("Option B");
        optionB.setBounds((WIDTH / 2) - 300,HEIGHT - 600,75,20);
        optionC = new JLabel("Option C");
        optionC.setBounds((WIDTH / 2) - 300,HEIGHT - 550,75,20);
        optionD = new JLabel("Option D");
        optionD.setBounds((WIDTH / 2) - 300,HEIGHT - 500,75,20);
        ans = new JLabel("Answer");
        ans.setBounds((WIDTH / 2) - 300,HEIGHT - 450,75,20);
        questionWrite = new JTextArea();
        questionWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 700,400,20);
        optionAWrite = new JTextArea();
        optionAWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 650,400,20);
        optionBWrite = new JTextArea();
        optionBWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 600,400,20);
        optionCWrite = new JTextArea();
        optionCWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 550,400,20);
        optionDWrite = new JTextArea();
        optionDWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 500,400,20);
        ansWrite = new JTextArea();
        ansWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 450,400,20);
        addQ = new JButton("Create Question");
        addQ.setBounds((WIDTH / 2) - 225,HEIGHT - 400,400,50);
        addQ.setBackground(Color.LIGHT_GRAY);
        addQ.addActionListener(this);
        add(question);
        add(optionA);
        add(optionB);
        add(optionC);
        add(optionD);
        add(ans);
        add(questionWrite);
        add(optionAWrite);
        add(optionBWrite);
        add(optionCWrite);
        add(optionDWrite);
        add(ansWrite);
        add(addQ);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addQ) {
            ArrayList<String> options = new ArrayList<>();
            options.add(optionAWrite.getText());
            options.add(optionBWrite.getText());
            options.add(optionCWrite.getText());
            options.add(optionDWrite.getText());
            Question newQ = new Question(questionWrite.getText(),options,ansWrite.getText());
            gui.addQuestion(newQ);
        }
    }
}
