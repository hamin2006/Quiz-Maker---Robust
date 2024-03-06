package persistence;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import model.Question;
public class JsonTest {
    void checkQuestion(String ques, String ans, ArrayList<String> options, Question q) {
        assertEquals(ques,q.getQuestion());
        assertEquals(ans,q.getAnswer());
        int index = 0;
        for (String s : options) {
            assertEquals(s,q.getOptions().get(index));
            index++;
        }
    }
}
