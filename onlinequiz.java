import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Question {
    String question;
    String optionA, optionB, optionC, optionD;
    char correctAnswer;

    public Question(String question, String optionA, String optionB,
                    String optionC, String optionD, char correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public boolean askQuestion(Scanner sc) {
        System.out.println("\n" + question);
        System.out.println("A. " + optionA);
        System.out.println("B. " + optionB);
        System.out.println("C. " + optionC);
        System.out.println("D. " + optionD);
        System.out.print("Your Answer: ");

        char answer = sc.next().toUpperCase().charAt(0);

        if (answer == correctAnswer) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Wrong! Correct answer: " + correctAnswer);
            return false;
        }
    }
}

public class Main {

    static ArrayList<Question> questions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadQuestions();

        System.out.println("===== Online Quiz System =====");
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        int score = 0;

        for (Question q : questions) {
            if (q.askQuestion(sc)) {
                score++;
            }
        }

        System.out.println("\nQuiz Finished!");
        System.out.println("Your Score: " + score + "/" + questions.size());

        saveResult(name, score);
    }

    static void loadQuestions() {
        questions.add(new Question(
                "Which language is platform independent?",
                "C", "C++", "Java", "Python", 'C'));

        questions.add(new Question(
                "Which company developed Java?",
                "Microsoft", "Sun Microsystems", "Google", "Apple", 'B'));

        questions.add(new Question(
                "Which keyword is used for inheritance in Java?",
                "this", "super", "extends", "implements", 'C'));

        questions.add(new Question(
                "Which collection does not allow duplicates?",
                "List", "ArrayList", "Set", "Vector", 'C'));
    }

    static void saveResult(String name, int score) {
        try (FileWriter fw = new FileWriter("quiz_results.txt", true)) {
            fw.write("Name: " + name + " | Score: " + score + "\n");
            System.out.println("Result saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving result.");
        }
    }
}