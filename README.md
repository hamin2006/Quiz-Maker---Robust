# My Personal Project
### What is it?
For my personal project I have decided to create an application which will serve as a **multiple-choice question quiz 
maker**. The application can be used as a survey creator, a testing tool, or a studying tool. I consistently find myself
always trying to find ways to test my understanding prior to going into large assessments such as midterms or finals in 
order to get a glimpse into how well my understanding on topics covers the lecture course content. Understandably a 
great way to fix this issue is to create an application specifically for making multiple-choice question quizzes. The 
application would **mainly serve to help students** who are trying to prepare for tests or other activities, however,
there are no true bounds to its uses.

### How will it work? 
The application will allow a user to create and save a quiz. Furthermore, there will be no bounds as to how
many questions must be in one quiz and the application will allow you to save and load all quizzes previously worked on.
The user will also be able to edit and delete all the questions inside of them. They will have options to set the 
correct answer (answers if there are more than one) for individual questions and upon 
completion of making the quiz the user can then prompt a new panel in which they can take the quiz they just created.
After they've taken the quiz they can check their quiz statistics to see how well they did on the quiz and whether they
should retake it to improve their understanding. Furthermore, every time they reopen the application the quiz statistics
section of the quiz will reset for the user, but the quizzes and questions will remain in the same state as before.
The application should serve as a great tool for users, whether they are trying to study, survey, or test their 
fellow peers.
## User Story

- **As a user, I want to be able to add question(s) to a selected quiz**
- **As a user, I want to be able to edit the questions in a quiz**      
- **As a user, I want to be able to remove questions in a quiz**
- **As a user, I want to be able to view/list all the questions in a selected quiz**          
- **As a user, I want to be able to complete the quiz and view performance statistics** 
- **As a user, I want to have the option to load the state of previously the saved quiz from file when the program 
opens**
- **As a user, I want to have the option to save the state of the quiz that has been worked on to file**

# Instructions for Grader

- You can "add multiple questions to a quiz" by pressing the + button at the top left of the project. This will prompt you to enter the new details of a question.
- You can generate the first required action related to the user story "adding multiple questions to a quiz" by clicking
the "view" button in the top left button group of the main application screen which will allow you to view the entire
question list in plain english.
- You can generate the second required action related to the user story "adding multiple questions to a quiz" by clicking
on any of the questions from the left side JList, and then clicking the "delete" button which will allow you to remove
the question from the quiz.
- You can also edit questions by selecting the "edit" button after choosing a question from the left side JList.
- You can also take the quiz and get graded by selecting the "take" button.
- You can locate my visual component when you start the program, there is an image/icon which will pop up.
- You can save the state of my application by clicking the "save" button on the main quiz panel.
- You can reload the state of my application by clicking the load saved quiz button when you start the project

Visual component retrieved from: https://elearningindustry.com/tags/quiz-activity

JSON Serializaion inspired from UBC CPSC 210 example serialization

# Phase 4: Task 2
### Actions:
1. Added new question named "What is my name?"
2. Edited question named "What is my name?"
3. Take's quiz for grades (Only logs grade if submitted, otherwise logs only viewed)
4. Viewed all questions
5. Removed question "What is my name?"

### Logs:

Sat Apr 06 00:33:47 PDT 2024 \
Added Question: What is my name?


Sat Apr 06 00:34:04 PDT 2024\
Edited Question: What is my name?


Sat Apr 06 00:34:10 PDT 2024\
Viewed All Questions


Sat Apr 06 00:34:13 PDT 2024\
Took Quiz and Scored: 100.0


Sat Apr 06 00:34:17 PDT 2024\
Viewed All Questions


Sat Apr 06 00:34:19 PDT 2024\
Removed Question: What is my name?

# Phase 4: Task 3

The first refactoring change I'd make to my project involves most of the UI classes. Each UI class named with the suffix
"Panel", except for class AllQuestionPanel has a lot of common behaviour. Each of these named classes extends JPanel and
implements ActionListener for button clicks. Furthermore, most of these classes has a QuizMakerGUI field, which is
passed through to the panel through its constructor. Therefore, to decrease duplicate code, increase cohesion and
improve readability I can use an abstract class to handle the similar behaviour of the different panel classes. The 
abstract class would extend JPanel and implement ActionListener. This abstract class's constructor would have a parameter of type
QuizMakerGUI because most of the panel classes have a field of type QuizMakerGUI. The constructor of this abstract
class can also call the setSize(WIDTH,HEIGHT) and setLayout(null) methods to set up the basic frame of the panel. As for
methods, the class will have an abstract method buildPane() which will format all JComponents and add them to the panel
and another abstract method actionPerformed(ActionEvent e) because each class has different buttons that serve
unique purposes. I would then just have the applicable Panel classes extend this new abstract class. This refactoring should retain all functionality while increasing code readability.

I could also make a similar refactoring change with the classes which control the console app vs GUI app. Both of these
classes have a JsonWriter, JsonReader, and Quiz fields. They also have similar loadQuiz and saveQuiz functions. Similar
to my previous change I can make an abstract class to handle this duplicate code. First, I would have to remove the
JFrame extension from the QuizMakerGUI class and create an instance of the built-in JFrame class as a field. This means
I would also have to refactor any calls to JFrame methods to make the calls to the new instance field/object. I can then
create a new abstract class which has a constructor with parameter type Quiz and protected fields for a Quiz, JsonWriter and JsonReader. The 
abstract class would also have two protected void methods: saveQuiz() and loadQuiz. These methods will save and load the
data to/from the Quiz instance field which can be accessed by children of the class. I will then just have QuizMakerGUI
and QuizMakerConsole extend this abstract class. This refactoring will retain all functionality, decrease duplicate code
and simplify the project's  UML diagram.