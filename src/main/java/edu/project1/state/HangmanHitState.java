package edu.project1.state;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanHitState extends HangmanState {
    public HangmanHitState(HangmanState state) {
        super(state);
    }

    @Override
    public void printState() {
        System.out.println("Hit!");

        super.printState();
    }
}
