import java.util.*;

class Question {
    String question;
    String[] options;
    char correctOption;

    public Question(String question, String[] options, char correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public void display() {
        System.out.println("\n" + question);
        char opt = 'A';
        for (String option : options) {
            System.out.println(opt + ". " + option);
            opt++;
        }
    }

    public boolean isCorrect(char answer) {
        return Character.toUpperCase(answer) == Character.toUpperCase(correctOption);
    }
}

public class Task4_QuizApplication {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        
        // Sample Questions
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 'C'));

        questions.add(new Question("Which language is used for Android development?",
                new String[]{"Python", "Java", "Swift", "Kotlin"}, 'D'));

        questions.add(new Question("Who invented Java?",
                new String[]{"Dennis Ritchie", "James Gosling", "Guido van Rossum", "Bjarne Stroustrup"}, 'B'));

        System.out.println("🧠 Welcome to the Quiz!");
        System.out.println("⏱️ You have 10 seconds for each question.\n");

        for (Question q : questions) {
            askWithTimer(q);
        }

        System.out.println("\n✅ Quiz Finished!");
        System.out.println("🎯 Your Final Score: " + score + "/" + questions.size());
    }

    public static void askWithTimer(Question q) {
        Timer timer = new Timer();
        q.display();

        // Timer task to auto-submit
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\n⏰ Time's up! Moving to the next question.");
                scanner = new Scanner(System.in); // Reset scanner input
                timer.cancel();
            }
        };

        timer.schedule(task, 10000); // 10 seconds

        System.out.print("👉 Enter your answer (A/B/C/D): ");
        String input = "";

        if (scanner.hasNext()) {
            input = scanner.next();
            timer.cancel();
        }

        if (!input.isEmpty() && q.isCorrect(input.charAt(0))) {
            System.out.println("✅ Correct!");
            score++;
        } else if (!input.isEmpty()) {
            System.out.println("❌ Incorrect! Correct Answer: " + q.correctOption);
        }
    }
}