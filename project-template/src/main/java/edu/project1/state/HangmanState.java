package edu.project1.state;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanState {
    private final HashSet<Character> unGuessedLetters;
    private final String answer;
    protected final int maxAttempts;
    protected int attempts;

    public HangmanState(String answer, int maxAttempts) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;

        this.unGuessedLetters = IntStream.range(0, answer.length())
            .mapToObj(answer::charAt)
            .collect(Collectors.toCollection(HashSet<Character>::new));
    }

    public HangmanState(HangmanState state) {
        this.unGuessedLetters = state.getUnGuessedLetters();
        this.answer = state.getAnswer();
        this.maxAttempts = state.getMaxAttempts();
        this.attempts = state.getAttempts();
    }

    public HangmanState tryGuess(String userInput) {
        if (userInput == null || userInput.length() != 1) {
            return this;
        }

        var letter = userInput.charAt(0);

        if (unGuessedLetters.contains(letter)) {
            unGuessedLetters.remove(letter);

            return unGuessedLetters.isEmpty() ? new HangmanWonState(this) : new HangmanHitState(this);
        }

        attempts++;

        return attempts == maxAttempts ? new HangmanLostState(this) : new HangmanMistakeState(this);
    }

    public void printState() {
        System.out.println("\n" + buildWordToPrint() + "\n");
    }

    private String buildWordToPrint() {
        return IntStream.range(0, answer.length())
            .mapToObj(i -> unGuessedLetters.contains(answer.charAt(i)) ? "*" : answer.substring(i, i + 1))
            .collect(Collectors.joining());
    }

    public HashSet<Character> getUnGuessedLetters() {
        return unGuessedLetters;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }
}
