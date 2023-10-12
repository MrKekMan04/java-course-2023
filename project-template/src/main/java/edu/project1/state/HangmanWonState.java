package edu.project1.state;

@SuppressWarnings("RegexpSinglelineJava")
public class HangmanWonState extends HangmanHitState {
    public HangmanWonState(HangmanState state) {
        super(state);
    }

    @Override
    public void printState() {
        super.printState();

        System.out.println("You won!");
    }
}
