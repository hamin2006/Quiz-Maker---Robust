package model;

import java.util.*;

// represents a quiz with multiple questions
public class Quiz {
    private String title;
    private ArrayList<Question> questions;

    // EFFECTS: creates new empty quiz insane with title and no questions
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
        String output = "----------------------------------------------------------------------------------\n";
        output += title + "\n\n";
        int questionNumber = 1;
        for (Question q : questions) {
            output += questionNumber + ". " + q.toString();
            questionNumber++;
        }
        output += "----------------------------------------------------------------------------------\n";
        return output;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
