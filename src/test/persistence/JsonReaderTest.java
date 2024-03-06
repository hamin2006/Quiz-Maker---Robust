package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import model.Quiz;
import model.Question;
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Quiz q = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyQuiz.json");
        try {
            Quiz q = reader.read();
            assertEquals("My quiz", q.getTitle());
            assertEquals(0, q.getQuestions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralQuiz.json");
        try {
            Quiz q = reader.read();
            assertEquals("My quiz", q.getTitle());
            List<Question> questions = q.getQuestions();
            assertEquals(2, questions.size());
            ArrayList<String> options = new ArrayList<>();
            options.add("A");
            options.add("B");
            options.add("C");
            options.add("D");
            checkQuestion("My age?", "B",options,questions.get(0));
            checkQuestion("My name?", "A",options,questions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
