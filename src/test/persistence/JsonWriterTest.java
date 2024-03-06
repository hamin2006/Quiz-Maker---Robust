package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import model.Quiz;
import model.Question;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Quiz q = new Quiz("My quiz");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Quiz q = new Quiz("My quiz");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyQuiz.json");
            q = reader.read();
            assertEquals("My quiz", q.getTitle());
            assertEquals(0, q.getQuestions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Quiz q = new Quiz("My quiz");
            ArrayList<String> options = new ArrayList<>();
            options.add("A");
            options.add("B");
            options.add("C");
            options.add("D");
            q.addQuestion(new Question("My name?", options, "A"));
            q.addQuestion(new Question("My age?", options, "B"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralQuiz.json");
            q = reader.read();
            assertEquals("My quiz", q.getTitle());
            List<Question> questions = q.getQuestions();
            assertEquals(2, questions.size());
            checkQuestion("My name?", "A",options,questions.get(0));
            checkQuestion("My age?", "B",options,questions.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
