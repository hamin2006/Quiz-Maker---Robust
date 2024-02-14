package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {
    private Quiz quiz;
    private Question q1;
    private Question q2;
    private Question q3;
    private ArrayList<String> genericOptions;
    @BeforeEach
    void runBefore() {
        quiz = new Quiz("Harsh's Quiz");
        genericOptions = new ArrayList<>();
        genericOptions.add("A");
        genericOptions.add("B");
        genericOptions.add("C");
        genericOptions.add("D");
        q1 = new Question("q1",genericOptions,"A");
        q2 = new Question("q2",genericOptions,"B");
        q3 = new Question("q3",genericOptions,"C");
    }

    @Test
    void testConstructor() {
        assertEquals(quiz.getTitle(), "Harsh's Quiz");
        assertEquals(quiz.getQuestions().size(),0);
    }

    @Test
    void testAddQuestion() {
        quiz.addQuestion(q1);
        assertEquals(quiz.getQuestions().size(),1);
        assertEquals(quiz.getQuestions().get(0).getQuestion(),"q1");

        quiz.addQuestion(q2);
        assertEquals(quiz.getQuestions().size(),2);
        assertEquals(quiz.getQuestions().get(0).getQuestion(),"q1");
        assertEquals(quiz.getQuestions().get(1).getQuestion(),"q2");
    }

    @Test
    void testRemoveQuestion() {
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);

        quiz.removeQuestion(1);
        assertEquals(quiz.getQuestions().size(),2);
        assertEquals(quiz.getQuestions().get(0).getQuestion(),"q1");
        assertEquals(quiz.getQuestions().get(1).getQuestion(),"q3");

        quiz.removeQuestion(0);
        q3.setQuestion("q4");
        assertEquals(quiz.getQuestions().size(),1);
        assertEquals(quiz.getQuestions().get(0).getQuestion(),"q4");

    }

    @Test
    void testGradeQuiz() {
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        ArrayList<String> answers = new ArrayList<>();
        answers.add("D");
        answers.add("D");
        answers.add("D");
        assertEquals(quiz.gradeQuiz(answers),0);

        answers.set(0,"A");
        answers.set(1,"B");
        answers.set(2,"C");
        assertEquals(quiz.gradeQuiz(answers),100);

        q1.setAnswer("B");
        assertEquals(quiz.gradeQuiz(answers),67);
    }

    @Test
    void testPrintQuiz() {
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        String output = quiz.printQuiz();
        assertTrue(output.contains(quiz.getTitle()));
        assertTrue(output.contains("q1\n" +
                "A) A\n" +
                "B) B\n" +
                "C) C\n" +
                "D) D\n"));
        assertTrue(output.contains("q2\n" +
                "A) A\n" +
                "B) B\n" +
                "C) C\n" +
                "D) D\n"));
        assertTrue(output.contains("q3\n" +
                "A) A\n" +
                "B) B\n" +
                "C) C\n" +
                "D) D\n"));
        assertFalse(output.contains("q4\n" +
                "A) A\n" +
                "B) B\n" +
                "C) C\n" +
                "D) D\n"));
    }
}
