package model;

import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private Question question;
    private String quesStatement;
    private ArrayList<String> options;
    private String answer;

    @BeforeEach
    void runBefore() {
        options = new ArrayList<>();
        options.add("Harsh");
        options.add("Amin");
        options.add("Alex");
        options.add("Bob");
        quesStatement = "What's my name?";
        answer = "A";
        question = new Question(quesStatement, options, answer);
    }

    @Test
    void testConstructor() {
        assertEquals(question.getQuestion(),quesStatement);
        assertEquals(question.getAnswer(), answer);
        assertEquals(question.getOptions().size(),4);
        assertEquals(question.getOptions().get(0),"Harsh");
        assertEquals(question.getOptions().get(1),"Amin");
        assertEquals(question.getOptions().get(2),"Alex");
        assertEquals(question.getOptions().get(3),"Bob");
    }

    @Test
    void testPrintQuestion() {
        assertEquals(question.printQuestion(),
                question.getQuestion()+"\n" +
                "A) Harsh\n" +
                "B) Amin\n" +
                "C) Alex\n" +
                "D) Bob\n\n");

        ArrayList<String> newOpts = new ArrayList<>();
        newOpts.add("A");
        newOpts.add("B");
        newOpts.add("C");
        newOpts.add("D");
        question.setOptions(newOpts);
        question.setQuestion("changed question");

        assertEquals(question.printQuestion(),
                question.getQuestion()+"\n" +
                        "A) A\n" +
                        "B) B\n" +
                        "C) C\n" +
                        "D) D\n\n");
    }
}