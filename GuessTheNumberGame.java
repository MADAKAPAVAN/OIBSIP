import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int numberOfRounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("You need to guess a number between " + lowerBound + " and " + upperBound + ".");

        for (int round = 1; round <= numberOfRounds; round++) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            int maxAttempts = 5;
            int roundScore = 0;

            System.out.println("\nRound " + round + ":");
            System.out.println("Guess the number!");

            while (true) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    roundScore = maxAttempts - attempts + 1;
                    totalScore += roundScore;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                if (attempts >= maxAttempts) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber);
                    break;
                }
            }

            System.out.println("Round " + round + " Score: " + roundScore);
        }

        System.out.println("\nGame Over!");
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
}
