package ui;

import model.Question;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Panel which prompts a user to take the quiz and answer the questions the way they think would be correct
public class TakeQuizPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private JTextArea quesView;
    private JTextField gradeView;
    private JScrollPane scrollPane;
    private JButton submit;
    private JButton back;
    private ArrayList<JComboBox> boxs;
    private QuizMakerGUI gui;

    // EFFECTS: constructs new take quiz panel
    public TakeQuizPanel(Quiz q,QuizMakerGUI gui) {
        this.gui = gui;
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        boxs = new ArrayList<>();
        quesView = new JTextArea();
        quesView.setEditable(false);
        quesView.setBackground(Color.lightGray);
        quesView.setText("");
        quesView.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        setPanel();
        add(submit);
        add(gradeView);
        add(back);
        initQuesView(q);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons, scrollbars, and text fields
    public void setPanel() {
        scrollPane = new JScrollPane(quesView);
        scrollPane.setBounds(5,5,WIDTH - 10,HEIGHT - 150);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        submit = new JButton("Submit");
        submit.setBounds(WIDTH / 2 - 100, HEIGHT - 125, 300, 75);
        submit.addActionListener(this);
        back = new JButton("Back");
        back.setBounds(WIDTH / 2 - 450, HEIGHT - 125, 300, 75);
        back.addActionListener(this);
        gradeView = new JTextField();
        gradeView.setBounds(WIDTH / 2 + 250, HEIGHT - 125, 200, 75);
        gradeView.setBackground(new Color(240,240,240));
        gradeView.setBorder(null);
        gradeView.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        gradeView.setEditable(false);
        gradeView.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets the main text field to contain all the questions and a combo box for each question
    public void initQuesView(Quiz quiz) {
        int index = 0;
        quesView.setText(quiz.printQuiz());
        for (Question q : quiz.getQuestions()) {
            JComboBox box = new JComboBox<>(new String[]{"A","B","C","D"});
            box.addActionListener(this);
            box.setBounds(20, 175 * index + 200, 100, 30);
            boxs.add(box);
            quesView.add(box);
            index++;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles grading and going "back" to the main JPanel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            ArrayList<String> answers = new ArrayList<>();
            for (JComboBox b : boxs) {
                answers.add((String) b.getSelectedItem());
            }
            int grade = Integer.valueOf((int) gui.gradeQuiz(answers));
            gradeView.setText(grade + "%");
        } else if (e.getSource() == back) {
            gui.enableSplit();
        } else if (e.getSource() instanceof JComboBox) {
            gradeView.setText("");
        }

    }
}
