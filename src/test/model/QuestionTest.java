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
                "D) Bob\n\n\n");

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
                        "D) D\n\n\n");
    }

    @Test
    void testEquals() {
        assertTrue(question.equals(question));
        assertFalse(question.equals(new Quiz("")));
        assertFalse(question.equals(null));
        Question question2 = new Question(quesStatement,options,answer);
        Question question3 = new Question("hamin",options,answer);
        Question question4 = new Question(quesStatement,new ArrayList<>(),answer);
        Question question5 = new Question(quesStatement,options,"C");
        assertTrue(question.equals(question2));
        assertFalse(question.equals(question3));
        assertFalse(question.equals(question4));
        assertFalse(question.equals(question5));
    }

    @Test
    void testHashcode() {
        Question question2 = new Question(quesStatement,options,answer);
        assertEquals(question.hashCode(),question2.hashCode());
    }
}