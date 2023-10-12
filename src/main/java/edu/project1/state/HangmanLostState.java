package edu.project1.state;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanLostState extends HangmanMistakeState {
    public HangmanLostState(HangmanState state) {
        super(state);
    }

    @Override
    public void printState() {
        super.printState();

        System.out.println("You lost!");
    }
}
