package edu.project1.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanStateTest {
    @Test
    public void correctStateChangingTest() {
        var state = new HangmanState("no", 2);
        var preLostState = state.tryGuess("f");
        var preWonState = state.tryGuess("n");

        assertTrue(preWonState instanceof HangmanHitState);
        assertTrue(preWonState.tryGuess("o") instanceof HangmanWonState);
        assertTrue(preLostState instanceof HangmanMistakeState);
        assertTrue(preLostState.tryGuess("g") instanceof HangmanLostState);

        assertSame(state.tryGuess(null), state);
        assertSame(state.tryGuess(""), state);
        assertSame(state.tryGuess("moreThenOneLength"), state);
    }
}
