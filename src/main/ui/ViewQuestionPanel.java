package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewQuestionPanel extends JPanel implements ActionListener {
    private static final int WIDTH = 1000 - 150;
    private static final int HEIGHT = 800;
    private int index;
    private QuizMakerGUI gui;
    private JTextArea quesView;
    private JScrollPane scrollPane;
    private JButton edit;
    private JButton delete;

    public ViewQuestionPanel(Quiz q, QuizMakerGUI gui, int i) {
        this.gui = gui;
        this.index = i;
        setSize(WIDTH,HEIGHT);
        setLayout(null);
        quesView = new JTextArea();
        quesView.setEditable(false);
        quesView.setBackground(Color.lightGray);
        quesView.setText(q.getQuestions().get(i).printQuestion());
        quesView.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        scrollPane = new JScrollPane(quesView);
        scrollPane.setBounds(5,5,WIDTH - 10,HEIGHT - 150);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        edit = new JButton("Edit");
        edit.setBounds(5, HEIGHT - 140, WIDTH / 2 - 2, 100);
        edit.addActionListener(this);
        delete = new JButton("Delete");
        delete.setBounds(WIDTH / 2, HEIGHT - 140, WIDTH / 2 - 4, 100);
        delete.addActionListener(this);
        add(edit);
        add(delete);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            gui.removeQuestion(index);
        } else if (e.getSource() == edit) {
            gui.openEditPanel();
        }
    }
}
