import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApp {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public QuizApp(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                displayNextQuestion();
            }
        }, 30000); // 30 seconds per question

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("\nQuestion: " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Your answer (1-" + options.size() + "): ");
            int userAnswerIndex = scanner.nextInt() - 1;

            if (userAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + (currentQuestion.getCorrectAnswerIndex() + 1));
            }

            currentQuestionIndex++;
            displayNextQuestion();
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        timer.cancel();
        System.out.println("\nQuiz ended!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?", List.of("London", "Paris", "Berlin", "Madrid"), 1));
        questions.add(new QuizQuestion("What is the largest planet in our solar system?", List.of("Mars", "Venus", "Jupiter", "Saturn"), 2));
        questions.add(new QuizQuestion("Who wrote 'Romeo and Juliet'?", List.of("William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"), 0));

        QuizApp quizApp = new QuizApp(questions);
        quizApp.startQuiz();
    }
}