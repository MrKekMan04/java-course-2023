package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanGameTest {
    @Test
    public void incorrectAnswerTest() {
        assertNull(new HangmanGame("", 1).getCurrentState());
        assertNull(new HangmanGame(null, 1).getCurrentState());
        assertNull(new HangmanGame("right", 0).getCurrentState());
    }
}
