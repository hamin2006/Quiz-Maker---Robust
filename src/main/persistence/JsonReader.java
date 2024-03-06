package persistence;

import model.Question;
import model.Quiz;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads a quiz from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads quiz from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Quiz read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuiz(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses quiz from JSON object and returns it
    private Quiz parseQuiz(JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Quiz qu = new Quiz(title);
        addQuestions(qu, jsonObject);
        return qu;
    }

    // MODIFIES: qu
    // EFFECTS: parses questions from JSON object and adds them to workroom
    private void addQuestions(Quiz qu, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(qu, nextQuestion);
        }
    }

    // MODIFIES: qu
    // EFFECTS: parses a question from JSON object and adds it to workroom
    private void addQuestion(Quiz qu, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        ArrayList<String> options = getOptions(jsonObject.getJSONArray("options"));
        String answer = jsonObject.getString("answer");
        Question newQ = new Question(question, options, answer);
        qu.addQuestion(newQ);
    }

    // EFFECTS: returns a string arrayList of the options for a question, parsed from json array
    private ArrayList<String> getOptions(JSONArray jsonArray) {
        ArrayList<String> options = new ArrayList<>();
        for (Object json : jsonArray) {
            String nextOption = (String) json;
            options.add(nextOption);
        }
        return options;
    }
}
