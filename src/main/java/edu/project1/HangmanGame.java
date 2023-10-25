package edu.project1;

import edu.project1.state.HangmanLostState;
import edu.project1.state.HangmanState;
import edu.project1.state.HangmanWonState;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanGame {
    private static final String[] ANSWERS = {"hello", "print", "random", "answer", "template", "project", "attribute"};
    private static final int DEFAULT_MAX_ATTEMPTS = 5;
    private HangmanState currentState;

    public HangmanGame() {
        this(ANSWERS[new Random().nextInt(ANSWERS.length)], DEFAULT_MAX_ATTEMPTS);
    }

    public HangmanGame(String answer, int maxAttempts) {
        if (!(answer == null || answer.isEmpty() || maxAttempts < 1)) {
            currentState = new HangmanState(answer, maxAttempts);
        }
    }

    public void run() {
        while (!(currentState instanceof HangmanWonState || currentState instanceof HangmanLostState)) {
            try {
                currentState = tryGuess();

                printState();
            } catch (NoSuchElementException ignored) {
                break;
            }
        }
    }

    private HangmanState tryGuess() {
        var scanner = new Scanner(System.in);

        System.out.println("Guess a letter:");

        return currentState.tryGuess(scanner.nextLine());
    }

    private void printState() {
        currentState.printState();
    }

    public HangmanState getCurrentState() {
        return currentState;
    }
}
