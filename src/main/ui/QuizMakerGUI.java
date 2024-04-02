package ui;

import model.Event;
import model.EventLog;
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
import java.io.IOException;
import java.util.ArrayList;

// Main GUI class which altogether represents all aspects of an entire quiz maker application
public class QuizMakerGUI extends JFrame implements ActionListener, ListSelectionListener, WindowListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final String LOCATION = "./data/saveQuiz.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel contentPane;
    private JPanel buttons;
    private JList nameList;
    private JScrollPane scrollPane;
    private JSplitPane split;
    private JSplitPane split2;
    private JLabel startLabel;
    private ArrayList<String> indexs;
    private JButton addQ;
    private JButton take;
    private JButton view;
    private JButton save;
    private Quiz quiz;
    private String title;

    // EFFECTS: creates new JFrame and calls for the user to create a new quiz or load a saved one
    public QuizMakerGUI() {
        super();
        jsonWriter = new JsonWriter(LOCATION);
        jsonReader = new JsonReader(LOCATION);
        nameQuiz();
        this.addWindowListener(this);
    }

    // MODIFIES: this
    // EFFECTS: opens the panel which will call for the user to create a new quiz or load a saved one
    public void nameQuiz() {
        setSize(WIDTH, HEIGHT);
        setTitle("Quiz Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setContentPane(new NamePanel(this));
    }

    // MODIFIES: this
    // EFFECTS: Initializes the main frame of the quizmaker
    public void initFrame() {
        setTitle(title);
        indexs = new ArrayList<>();
        indexs.add("No Questions");
        nameList = new JList(indexs.toArray());
        nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nameList.setSelectedIndex(0);
        nameList.addListSelectionListener(this);
        nameList.setDragEnabled(true);
        initStartPanel();
        initButtons();
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

    // MODIFIES: this
    // EFFECTS: initializes the first content pane, prompting the user to create a new question
    public void initStartPanel() {
        contentPane = new JPanel(null);
        contentPane.setSize(WIDTH - 150,HEIGHT);
        startLabel = new JLabel("Click the + button in the top left to add a question");
        startLabel.setBounds((WIDTH / 2) - 200,HEIGHT - 600,400,20);
        contentPane.add(startLabel);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons
    public void initButtons() {
        buttons = new JPanel(null);
        buttons.setSize(150,25);
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
    }

    // MODIFIES: this
    // EFFECTS: Updates nameList to contain names for all questions in the quiz
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

    // MODIFIES: this
    // EFFECTS: opens new panel to add a question to the quiz and clears nameList selection
    public void openAddQuestion() {
        nameList.clearSelection();
        split.setBottomComponent(new AddQuestionPanel(this));
        split.setDividerLocation(150);
    }

    // MODIFIES: this
    // EFFECTS: opens new panel to edit a previously added question in the list
    public void openEditPanel() {
        if (nameList.getSelectedIndex() >= 0) {
            split.setBottomComponent(new EditQuestionPanel(quiz, this,nameList.getSelectedIndex()));
            split.setDividerLocation(150);
        }
    }

    // MODIFIES: this
    // EFFECTS: opens new panel which views a single question at index i in the quiz's question list
    public void openViewQuesPanel(int i) {
        split.setBottomComponent(new ViewQuestionPanel(quiz, this, i));
        split.setDividerLocation(150);
    }

    // MODIFIES: this
    // EFFECTS: opens new panel which views all the questions in the quiz
    public void openViewAllPanel() {
        updateQuestionList();
        nameList.clearSelection();
        split.setBottomComponent(new AllQuestionPanel(quiz));
        split.setDividerLocation(150);
    }

    // MODIFIES: this
    // EFFECTS: opens panel which will prompt the user to take the quiz
    public void openTakeQuiz() {
        setContentPane(new TakeQuizPanel(quiz,this));
    }

    // MODIFIES: this
    // EFFECTS: Method to start the quiz maker after NamePanel execution has completed
    public void beginQuizMaker(String title) {
        this.title = title;
        quiz = new Quiz(title);
        initFrame();
        setContentPane(split);
    }

    // MODIFIES: this
    // EFFECTS: sets which index should be selected in the JList
    public void setQuestionIndex(int i) {
        nameList.setSelectedIndex(i);
    }

    // MODIFIES: this
    // EFFECTS: handles the removal of a question from quiz
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

    // MODIFIES: this
    // EFFECTS: handles the addition of a question to the quiz
    public void addQuestion(Question q) {
        if (!quiz.getQuestions().contains(q)) {
            quiz.addQuestion(q);
            updateQuestionList();
            setQuestionIndex(quiz.getQuestions().size() - 1);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the editing of a question at index i in the quiz's question list
    public void editQuestion(String ques, ArrayList<String> options, String ans, int i) {
        Question q = quiz.getQuestions().get(i);
        q.setQuestion(ques);
        q.setOptions(options);
        q.setAnswer(ans);
        setQuestionIndex(i);
        openViewQuesPanel(i);
    }

    // MODIFIES: this
    // EFFECTS: sets the JFrame panel to the original split pane
    public void enableSplit() {
        setContentPane(split);
    }

    // EFFECTS: saves quiz to file
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

    // MODIFIES: this
    // EFFECTS: reads quiz from file
    public void loadQuiz() {
        try {
            quiz = jsonReader.read();
            title = quiz.getTitle();
            initFrame();
            setContentPane(split);
            updateQuestionList();
        } catch (IOException e) {
            setBackground(Color.RED);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles all button pushes for the application
    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.white);
        if (e.getSource() == addQ) {
            openAddQuestion();
        } else if (e.getSource() == view) {
            openViewAllPanel();
        } else if (e.getSource() == save) {
            saveQuiz();
        } else if (e.getSource() == take) {
            if (quiz.getQuestions().size() > 0) {
                openTakeQuiz();
            } else {
                setBackground(Color.RED);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the changing of the panel to match the question selected when nameList selection is changed
    @Override
    public void valueChanged(ListSelectionEvent e) {
        setBackground(Color.white);
        JList ls = (JList) e.getSource();
        int i = ls.getSelectedIndex();
        if (i >= 0) {
            openViewQuesPanel(i);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event log : EventLog.getInstance()) {
            System.out.println(log.toString() + "\n\n");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
