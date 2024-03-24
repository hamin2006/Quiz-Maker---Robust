package ui;

import model.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizMakerGUI extends JFrame {
    JPanel contentPane;
    JList nameList;
    JScrollPane scrollPane;
    JSplitPane split;
    JSplitPane split2;
    ArrayList<String> indexs;
    JButton addQ;
    Quiz quiz;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;


    public QuizMakerGUI() {
        initFrame();
        runProject();
    }

    public void initFrame() {
        setTitle("Quiz Maker");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        indexs = new ArrayList<>();
        indexs.add("No Questions");
        nameList = new JList<String>(indexs.toArray(new String[indexs.size()]));
        nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nameList.setSelectedIndex(0);

        contentPane = new JPanel(null);
        contentPane.setSize(WIDTH - 150,HEIGHT);

        addQ = new JButton("+");

        scrollPane = new JScrollPane(nameList);

        split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,addQ,scrollPane);
        split2.setBounds(0,0,150,HEIGHT);
        split2.setEnabled(false);
        split2.setDividerSize(0);
        split2.setDividerLocation(25);


        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split2,contentPane);
        split.setBounds(0,0,WIDTH,HEIGHT);
        split.setEnabled(false);
        split.setDividerSize(0);
        split.setDividerLocation(150);
        add(split);
    }

    public void runProject() {

    }

    public void updateQuestionList() {
        for (int i = 1; i <= quiz.getQuestions().size(); i++) {
            indexs.add("Question " + i);
        }
        nameList = new JList(indexs.toArray(new String[indexs.size()]));
        scrollPane = new JScrollPane(nameList);
        split2.setBottomComponent(scrollPane);
    }
}
