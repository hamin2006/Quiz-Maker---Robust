package ui;

import model.Question;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// This class represents a Panel UI which handles the editing of a question
public class EditQuestionPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private QuizMakerGUI gui;
    private int index;
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
    private JComboBox ansWrite;
    private JButton addQ;
    private Question currQues;

    // EFFECTS: construct a new panel ready to add a new question to the quiz
    public EditQuestionPanel(Quiz q, QuizMakerGUI gui, int index) {
        this.gui = gui;
        this.index = index;
        setSize(WIDTH - 150,HEIGHT);
        setLayout(null);
        currQues = q.getQuestions().get(index);
        question = new JLabel("Question");
        question.setBounds((WIDTH / 2) - 300,HEIGHT - 700,75,20);
        setLabels();
        setTextFields();
        addQ = new JButton("Edit Question");
        addQ.setBounds((WIDTH / 2) - 225,HEIGHT - 400,400,50);
        addQ.setBackground(Color.LIGHT_GRAY);
        addQ.addActionListener(this);
        addToPanel();

    }

    // MODIFIES: this
    // EFFECTS: initializes all labels
    public void setLabels() {
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
    }

    // MODIFIES: this
    // EFFECTS: initializes all textfields required to write a new question
    public void setTextFields() {
        questionWrite = new JTextArea(currQues.getQuestion());
        questionWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 700,400,20);
        optionAWrite = new JTextArea(currQues.getOptions().get(0));
        optionAWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 650,400,20);
        optionBWrite = new JTextArea(currQues.getOptions().get(1));
        optionBWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 600,400,20);
        optionCWrite = new JTextArea(currQues.getOptions().get(2));
        optionCWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 550,400,20);
        optionDWrite = new JTextArea(currQues.getOptions().get(3));
        optionDWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 500,400,20);
        ansWrite = new JComboBox(new String[]{"A","B","C","D"});
        ansWrite.setSelectedItem(currQues.getAnswer());
        ansWrite.setBounds((WIDTH / 2) - 225,HEIGHT - 450,400,20);
    }

    // MODIFIES: this
    // EFFECTS: adds all components to JPanel
    public void addToPanel() {
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

    // MODIFIES: this
    // EFFECTS: Makes the edit to the question in the quiz, updates JList, and prompts opening of a ViewQuestionPanel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addQ) {
            ArrayList<String> options = new ArrayList<>();
            options.add(optionAWrite.getText());
            options.add(optionBWrite.getText());
            options.add(optionCWrite.getText());
            options.add(optionDWrite.getText());
            gui.editQuestion(questionWrite.getText(),options,(String) ansWrite.getSelectedItem(),index);
        }
    }
}
