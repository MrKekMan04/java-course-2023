package edu.project1;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        var game = new HangmanGame();

        game.run();
    }
}
