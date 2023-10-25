package edu.project1.state;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanStateTest {
    private static HangmanState defaultState;

    @BeforeAll
    public static void setUpDefaultState() {
        defaultState = new HangmanState("no", 2);
    }

    @Test
    public void assertThatChangedStatesReturnedRightStatesTest() {
        var preLostState = defaultState.tryGuess("f");
        var preWonState = defaultState.tryGuess("n");

        assertTrue(preWonState instanceof HangmanHitState);
        assertTrue(preWonState.tryGuess("o") instanceof HangmanWonState);
        assertTrue(preLostState instanceof HangmanMistakeState);
        assertTrue(preLostState.tryGuess("g") instanceof HangmanLostState);
    }

    @Test
    public void assertThatIncorrectUserInputReturnedSameStateTest() {
        assertSame(defaultState.tryGuess(null), defaultState);
        assertSame(defaultState.tryGuess(""), defaultState);
        assertSame(defaultState.tryGuess("moreThenOneLength"), defaultState);
    }
}
