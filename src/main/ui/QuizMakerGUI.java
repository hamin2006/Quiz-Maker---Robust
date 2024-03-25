package ui;

import model.Question;
import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizMakerGUI extends JFrame implements ActionListener, ListSelectionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final String LOCATION = "./data/saveQuiz.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel contentPane;
    private JPanel namePanel;
    private JPanel buttons;
    private JList nameList;
    private JScrollPane scrollPane;
    private JSplitPane split;
    private JSplitPane split2;
    private JLabel startLabel;
    private JLabel titleLabel;
    private JTextArea writeTitle;
    private ArrayList<String> indexs;
    private JButton addQ;
    private JButton makeQuiz;
    private JButton take;
    private JButton view;
    private JButton save;
    private Quiz quiz;
    private String title;


    public QuizMakerGUI() {
        super();
        jsonWriter = new JsonWriter(LOCATION);
        jsonReader = new JsonReader(LOCATION);
        nameQuiz();
        setContentPane(namePanel);
    }

    public void nameQuiz() {
        setTitle("Quiz Maker");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        titleLabel = new JLabel("Quiz Title:");
        titleLabel.setBounds((WIDTH / 2) - 200,HEIGHT - 500,400,20);
        writeTitle = new JTextArea();
        writeTitle.setBounds((WIDTH / 2) - 125,HEIGHT - 500,400,20);
        makeQuiz = new JButton("Enter");
        makeQuiz.setBounds((WIDTH / 2) - 125,HEIGHT - 470,400,25);
        makeQuiz.addActionListener(this);
        namePanel = new JPanel(null);
        namePanel.add(titleLabel);
        namePanel.add(writeTitle);
        namePanel.add(makeQuiz);
    }

    public void initFrame() {
        setTitle(title);
        indexs = new ArrayList<>();
        indexs.add("No Questions");
        nameList = new JList(indexs.toArray());
        nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nameList.setSelectedIndex(0);
        nameList.addListSelectionListener(this);

        buttons = new JPanel(null);
        buttons.setSize(150,25);

        initStartPanel();


        addQ = new JButton("+");
        addQ.setBounds(0,0,70,25);
        addQ.addActionListener(this);
        save = new JButton("Save");
        save.setOpaque(true);
        save.setBounds(70,0,70,25);
        save.addActionListener(this);
        take = new JButton("Take");
        take.setBounds(0,25,70,25);
        take.addActionListener(this);
        view = new JButton("View");
        view.setBounds(70,25,70,25);
        view.addActionListener(this);
        buttons.add(addQ);
        buttons.add(save);
        buttons.add(take);
        buttons.add(view);
        scrollPane = new JScrollPane(nameList);


        split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,buttons,scrollPane);
        split2.setBounds(0,0,150,HEIGHT);
        split2.setEnabled(false);
        split2.setDividerSize(0);
        split2.setDividerLocation(50);

        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split2,contentPane);
        split.setBounds(0,0,WIDTH,HEIGHT - 25);
        split.setEnabled(false);
        split.setDividerSize(0);
        split.setDividerLocation(150);
        add(split);
    }

    public void initStartPanel() {
        contentPane = new JPanel(null);
        contentPane.setSize(WIDTH - 150,HEIGHT);
        startLabel = new JLabel("Click the + button in the top left to add a question");
        startLabel.setBounds((WIDTH / 2) - 200,HEIGHT - 600,400,20);
        contentPane.add(startLabel);
    }

    public void updateQuestionList() {
        if (indexs.contains("No Questions")) {
            indexs.remove("No Questions");
        }
        indexs.clear();
        for (int i = 1; i <= quiz.getQuestions().size(); i++) {
            String s = "Question " + i;
            if (!indexs.contains(s)) {
                indexs.add("Question " + i);
            }
        }
        nameList.setListData(indexs.toArray());
    }

    public void openAddQuestion() {
        startLabel.setVisible(false);
        split.setBottomComponent(new AddQuestionPanel(this));
        split.setDividerLocation(150);
    }

    public void openEditPanel() {
        if (nameList.getSelectedIndex() >= 0) {
            split.setBottomComponent(new EditQuestionPanel(quiz, this,nameList.getSelectedIndex()));
            split.setDividerLocation(150);
        }
    }

    public void openViewQuesPanel(int index) {
        split.setBottomComponent(new ViewQuestionPanel(quiz, this, index));
        split.setDividerLocation(150);
    }

    public void openViewAllPanel() {
        updateQuestionList();
        nameList.clearSelection();
        split.setBottomComponent(new AllQuestionPanel(quiz));
        split.setDividerLocation(150);
    }

    public void setQuestionIndex(int i) {
        nameList.setSelectedIndex(i);
    }

    public void removeQuestion(int index) {
        quiz.removeQuestion(index);
        updateQuestionList();
        int size = quiz.getQuestions().size();
        if (size == 0) {
            updateQuestionList();
            initStartPanel();
            split.setBottomComponent(contentPane);
            split.setDividerLocation(150);
        } else if (index >= size) {
            setQuestionIndex(size - 1);
        } else {
            setQuestionIndex(index);
        }
    }

    public void addQuestion(Question q) {
        if (!quiz.getQuestions().contains(q)) {
            quiz.addQuestion(q);
            updateQuestionList();
            setQuestionIndex(quiz.getQuestions().size() - 1);
        }
    }

    public void editQuestion(String ques, ArrayList<String> options, String ans, int index) {
        Question q = quiz.getQuestions().get(index);
        q.setQuestion(ques);
        q.setOptions(options);
        q.setAnswer(ans);
        setQuestionIndex(index);
        openViewQuesPanel(index);
    }

    public void saveQuiz() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            setBackground(Color.GREEN);
        } catch (FileNotFoundException e) {
            setBackground(Color.RED);
        }
    }

    public void takeQuiz() {
        setContentPane(new TakeQuizPanel(quiz,this));
    }

    public void enableSplit() {
        setContentPane(split);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == makeQuiz) {
            title = writeTitle.getText();
            quiz = new Quiz(title);
            initFrame();
            setContentPane(split);
        } else {
            setBackground(Color.white);
            if (e.getSource() == addQ) {
                openAddQuestion();
            } else if (e.getSource() == view) {
                openViewAllPanel();
            } else if (e.getSource() == save) {
                saveQuiz();
            } else if (e.getSource() == take) {
                if (quiz.getQuestions().size() > 0) {
                    takeQuiz();
                } else {
                    setBackground(Color.RED);
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList ls = (JList) e.getSource();
        int i = ls.getSelectedIndex();
        if (i >= 0) {
            openViewQuesPanel(i);
        }
    }
}
