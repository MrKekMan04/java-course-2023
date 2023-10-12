package edu.project1.state;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanMistakeState extends HangmanState {
    public HangmanMistakeState(HangmanState state) {
        super(state);
    }

    @Override
    public void printState() {
        System.out.printf("Missed, mistake %d out of %d.%n", attempts, maxAttempts);

        super.printState();
    }
}
