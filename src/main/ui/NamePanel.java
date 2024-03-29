package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

// This class represents the initial panel which the user will interact with to load or create a new quiz
public class NamePanel extends JPanel implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    BufferedImage img;
    QuizMakerGUI gui;
    JLabel titleLabel;
    JTextArea writeTitle;
    JButton makeQuiz;
    JButton load;

    // EFFECTS: constructs a new Name Panel, loading the visual component
    public NamePanel(QuizMakerGUI gui) {
        this.gui = gui;
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        titleLabel = new JLabel("Quiz Title:");
        titleLabel.setBounds((WIDTH / 2) - 250,HEIGHT - 300 - 50,400,20);
        writeTitle = new JTextArea();
        writeTitle.setBounds((WIDTH / 2) - 175,HEIGHT - 300 - 50,400,20);
        makeQuiz = new JButton("Enter");
        makeQuiz.setBounds((WIDTH / 2) - 175,HEIGHT - 270 - 50,400,60);
        makeQuiz.addActionListener(this);
        load = new JButton("Load Saved Quiz");
        load.setBounds((WIDTH / 2) - 175,HEIGHT - 200 - 50,400,60);
        load.addActionListener(this);
        add(titleLabel);
        add(writeTitle);
        add(makeQuiz);
        add(load);
        try {
            img = ImageIO.read(new File("./data/quiz.png"));
        } catch (Exception e) {
            System.out.println("cant read");
        }
        setBackground(new Color(255,225,70));
    }

    // MODIFIES: this
    // EFFECTS: Overrides the JPanel method and draws visual component to the panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 90, 0, null);

    }

    // MODIFIES: this
    // EFFECTS: Handles whether the user wants to make a new quiz or load a saved quiz and calls on that method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == makeQuiz) {
            gui.beginQuizMaker(writeTitle.getText());
        } else if (e.getSource() == load) {
            gui.loadQuiz();
        }
    }
}
