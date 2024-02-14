package ui;

import model.Question;
import model.Quiz;

import java.util.*;

// represents a quiz maker application
public class QuizMaker {
    private Scanner input = new Scanner(System.in);
    private Quiz myQuiz;
    private boolean running;
    private static final char[] LETTERS = new char[] {'A','B','C','D'};

    // EFFECTS: run the application when a new instance is made
    public QuizMaker() {
        runProject();
    }

    // MODIFIES: this
    // EFFECTS: intitialize the quiz maker application and begins the run loop
    public void runProject() {
        System.out.print("Name your Quiz: ");
        String name = input.nextLine();
        myQuiz = new Quiz(name);
        running = true;
        displayMenu();
    }

    // MODIFIES: this
    // EFFECTS: main application menu which will carry out the user input for quiz modification
    //          or exiting the application
    public void displayMenu() {
        while (running) {
            System.out.println("What would you like to do with " + myQuiz.getTitle() + ":");
            System.out.println("1. Add a question");
            System.out.println("2. Remove a question");
            System.out.println("3. Edit a question");
            System.out.println("4. View entire quiz");
            System.out.println("5. Take quiz");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");
            String choice = input.nextLine();
            handleInput(choice);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles user input and calls corresponding helper function for quiz modification
    //          or exiting from the application
    public void handleInput(String i) {
        if (i.equals("1")) {
            addQuestion();
        } else if (i.equals("2")) {
            removeQuestion();
        } else if (i.equals("3")) {
            editQuestion();
        } else if (i.equals("4")) {
            System.out.println(myQuiz.printQuiz());
        } else if (i.equals("5")) {
            takeQuiz();
        } else if (i.equals("6")) {
            input.close();
            running = false;
        } else {
            System.out.println("\nInvalid input\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: calls for the creation of a new question and adds it to the quiz
    public void addQuestion() {
        Question newQuestion = createQuestion();
        myQuiz.addQuestion(newQuestion);
    }

    // MODIFIES: this
    // EFFECTS: prompts the creation of a new question and returns the created question
    public Question createQuestion() {
        System.out.print("\nQuestion Title: ");
        String name = input.nextLine();
        ArrayList<String> options = createOptions();
        System.out.print("Correct Answer (A-D): ");
        String answer = input.nextLine();
        System.out.println();
        Question newQuestion = new Question(name, options, answer);

        return newQuestion;
    }

    // MODIFIES: this
    // EFFECTS: prompts for the creation of question options and returns ordered list of options from A-D
    public ArrayList<String> createOptions() {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.print("Option " + LETTERS[i] + ": ");
            String option = input.nextLine();
            options.add(option);
        }
        return options;
    }

    // MODIFIES: this
    // EFFECTS: handles the removal of a question from the quiz based off user selection
    public void removeQuestion() {
        int numQuestions = myQuiz.getQuestions().size();
        if (numQuestions > 0) {
            System.out.println(myQuiz.printQuiz());
            System.out.print("Which numbered question would you like to remove (1-" + numQuestions + "): ");
            int index = input.nextInt();
            input.nextLine();
            if (index <= numQuestions) {
                myQuiz.removeQuestion(index - 1);
                System.out.println("\nQuestion Removed \n");
            } else {
                System.out.println("\nInvalid Input\n");
            }
        } else {
            System.out.println("\nNo Questions\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the editing of a question from the quiz based off user selection
    public void editQuestion() {
        int numQuestions = myQuiz.getQuestions().size();
        if (numQuestions > 0) {
            System.out.println(myQuiz.printQuiz());
            System.out.print("Which numbered question would you like to edit (1-" + numQuestions + "): ");
            int index = input.nextInt();
            input.nextLine();
            if (index <= numQuestions) {
                System.out.println("What would you like to change?");
                System.out.println("1. Title");
                System.out.println("2. Options");
                System.out.println("3. Answer");
                System.out.print("Changing (1-3): ");
                String editOption = input.nextLine();
                System.out.println("");
                makeEdit(editOption, index, numQuestions);
                System.out.println("\nQuestion Edited \n");
            } else {
                System.out.println("\nInvalid Input\n");
            }
        } else {
            System.out.println("\nNo Questions\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: based off of user input, prompts and edits the part of the question of which the user wants to change
    public void makeEdit(String option, int index, int numQuestions) {
        Question editQuestion = myQuiz.getQuestions().get(index - 1);
        boolean valid = index <= numQuestions;
        if (option.equals("1")) {
            System.out.print("New Title: ");
            String newTitle = input.nextLine();
            editQuestion.setQuestion(newTitle);

        } else if (option.equals("2")) {
            ArrayList<String> options = createOptions();
            editQuestion.setOptions(options);

        } else if (option.equals("3")) {
            System.out.print(editQuestion.printQuestion());
            System.out.print("New Answer: ");
            String newAnswer = input.nextLine();
            editQuestion.setAnswer(newAnswer);
        } else {
            System.out.println("\nInvalid input\n");
        }
    }

    // EFFECTS: prompts the user to take the created quiz and prints a graded score of their attempt
    public void takeQuiz() {
        if (myQuiz.getQuestions().size() > 0) {
            ArrayList<String> userAnswers = new ArrayList<>();
            for (Question q : myQuiz.getQuestions()) {
                System.out.print("\n" + q.printQuestion());
                System.out.print("Your Answer: ");
                String answer = input.nextLine();
                userAnswers.add(answer);
            }
            System.out.println("\nYour score: " + Integer.valueOf((int) myQuiz.gradeQuiz(userAnswers)) + "%\n");
        } else {
            System.out.println("\nNo Questions\n");
        }
    }
}
