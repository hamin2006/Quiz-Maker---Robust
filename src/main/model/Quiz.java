package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// represents a quiz with multiple questions
// this class should be able to manage the quiz's questions in a ordered manner
// a quiz should be able to be graded and viewed in a on-paper like ordered manner
public class Quiz implements Writable {
    private String title; // name of the quiz
    private ArrayList<Question> questions; // questions list in the quiz

    // EFFECTS: creates new empty quiz instance with title and no questions
    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new question q to the end of the list questions
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // REQUIRES: q is in the list of questions and questions.size() > 0
    // MODIFIES: this
    // EFFECTS: removes question at index q from question list
    public void removeQuestion(int q) {
        questions.remove(q);
    }

    // REQUIRES: answers.size() == questions.size()
    // EFFECTS: returns the graded percentage score rounded to the nearest whole number
    public double gradeQuiz(ArrayList<String> answers) {
        int correctAnswers = 0;
        int index = 0;
        for (String s : answers) {
            if (s.equals(questions.get(index).getAnswer())) {
                correctAnswers++;
            }
            index++;
        }
        return Math.round(correctAnswers / Double.valueOf(answers.size()) * 100);
    }

    // EFFECTS: creates a outputable string format of the whole quiz
    public String printQuiz() {
        String output = "\n";
        output += title + "\n\n";
        int questionNumber = 1;
        for (Question q : questions) {
            output += questionNumber + ". " + q.printQuestion();
            questionNumber++;
        }
        output += "\n";
        return output;
    }

    // EFFECTS: returns a json object which serializes the quiz instance
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("questions", questionsToJson());
        json.put("title", title);
        return json;
    }

    // EFFECTS: returns questions in this quiz as JSON objects in a JSON array
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Question q : questions) {
            jsonArray.put(q.toJson());
        }
        return jsonArray;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
