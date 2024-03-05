package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// represents a question with a question statement, answer, and options A through D
public class Question implements Writable {
    private ArrayList<String> options; // options A - D
    private String question;    // question statement
    private String answer;  // answer letter
    private static final char[] LETTERS = new char[] {'A','B','C','D'}; // represents constant letter options

    // EFFECTS: creates new question
    public Question(String question, ArrayList<String> options, String answer) {
        this.options = options;
        this.question = question;
        this.answer = answer;
    }

    // EFFECTS: transforms question into a outputable string
    public String printQuestion() {
        String list = question + "\n";
        int index = 0;
        for (String s : options) {
            list += LETTERS[index] + ") " + s + "\n";
            index++;
        }
        list += "\n";
        return list;
    }

    //@Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("options",optionsToJson());
        json.put("question", question);
        json.put("answer", answer);
        return json;
    }

    private JSONArray optionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : options) {
            jsonArray.put(s);
        }
        return jsonArray;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
