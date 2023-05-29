import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    private static int ties = 0;
    private static int userWins = 0;
    private static int computerWins = 0;

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain = true;

        while (playAgain) {
            playGame();
            printSummary();
            playAgain = askToPlayAgain();
        }

        System.out.println("Thanks for playing!");
    }

    private static void playGame() {
        int rounds = askForRounds();

        for (int i = 0; i < rounds; i++) {
            System.out.println("Round " + (i + 1));
            int userChoice = getUserChoice();
            int computerChoice = generateComputerChoice();

            System.out.println("User: " + choiceToString(userChoice));
            System.out.println("Computer: " + choiceToString(computerChoice));

            int result = calculateResult(userChoice, computerChoice);
            if (result == 0) {
                System.out.println("It's a tie!");
                ties++;
            } else if (result == 1) {
                System.out.println("User wins!");
                userWins++;
            } else {
                System.out.println("Computer wins!");
                computerWins++;
            }

            System.out.println();
        }
    }

    private static int askForRounds() {
        int rounds;
        do {
            System.out.print("Enter the number of rounds to play (1-10): ");
            rounds = scanner.nextInt();
            if (rounds < 1 || rounds > 10) {
                System.out.println("Invalid number of rounds. Please try again.");
            }
        } while (rounds < 1 || rounds > 10);

        return rounds;
    }

    private static int getUserChoice() {
        int choice;
        do {
            System.out.print("Enter your choice (1 = Rock, 2 = Paper, 3 = Scissors): ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice < 1 || choice > 3);

        return choice;
    }

    private static int generateComputerChoice() {
        return random.nextInt(3) + 1;
    }

    private static int calculateResult(int userChoice, int computerChoice) {
        if (userChoice == computerChoice) {
            return 0; // Tie
        } else if ((userChoice == ROCK && computerChoice == SCISSORS)
                || (userChoice == PAPER && computerChoice == ROCK)
                || (userChoice == SCISSORS && computerChoice == PAPER)) {
            return 1; // User wins
        } else {
            return -1; // Computer wins
        }
    }

    private static String choiceToString(int choice) {
        if (choice == ROCK) {
            return "Rock";
        } else if (choice == PAPER) {
            return "Paper";
        } else {
            return "Scissors";
        }
    }

    private static void printSummary() {
        System.out.println("Game summary:");
        System.out.println("Ties: " + ties);
        System.out.println("User wins: " + userWins);
    }
    private static boolean askToPlayAgain() {
        System.out.print("Do you want to play again? (Yes/No): ");
        String answer = scanner.next();
        return answer.equalsIgnoreCase("Yes");
    }
    }
