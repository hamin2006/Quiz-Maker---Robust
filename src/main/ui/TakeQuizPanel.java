package ui;

import model.Question;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TakeQuizPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private JTextArea quesView;
    private JTextField gradeView;
    private JScrollPane scrollPane;
    private JButton submit;
    private JButton back;
    private Quiz quiz;
    private ArrayList<JComboBox> boxs;
    private QuizMakerGUI gui;

    public TakeQuizPanel(Quiz q,QuizMakerGUI gui) {
        this.quiz = q;
        this.gui = gui;
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        boxs = new ArrayList<>();
        quesView = new JTextArea();
        quesView.setEditable(false);
        quesView.setBackground(Color.lightGray);
        quesView.setText("");
        quesView.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        scrollPane = new JScrollPane(quesView);
        scrollPane.setBounds(5,5,WIDTH - 10,HEIGHT - 150);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        submit = new JButton("Submit");
        submit.setBounds(WIDTH / 2 - 100, HEIGHT - 125, 200, 75);
        submit.addActionListener(this);
        back = new JButton("Back");
        back.setBounds(WIDTH / 2 - 400, HEIGHT - 125, 200, 75);
        back.addActionListener(this);
        gradeView = new JTextField();
        gradeView.setBounds(WIDTH / 2 + 200, HEIGHT - 125, 200, 75);
        gradeView.setBackground(new Color(240,240,240));
        gradeView.setBorder(null);
        gradeView.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        gradeView.setEditable(false);
        gradeView.setHorizontalAlignment(SwingConstants.CENTER);
        add(submit);
        add(gradeView);
        add(back);
        initQuesView();
    }

    public void initQuesView() {
        int index = 0;
        quesView.setText(quiz.printQuiz());
        for (Question q : quiz.getQuestions()) {
            JComboBox box = new JComboBox<>(new String[]{"A","B","C","D"});
            box.setBounds(20, 175 * index + 200, 100, 30);
            boxs.add(box);
            quesView.add(box);
            index++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            ArrayList<String> answers = new ArrayList<>();
            for (JComboBox b : boxs) {
                answers.add((String) b.getSelectedItem());
            }
            int grade = Integer.valueOf((int) quiz.gradeQuiz(answers));
            gradeView.setText(grade + "%");
        } else if (e.getSource() == back) {
            gui.enableSplit();
        }

    }
}
