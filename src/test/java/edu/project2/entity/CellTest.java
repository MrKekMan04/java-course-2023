package edu.project2.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private static Cell wall;
    private static Cell passage;

    @BeforeAll
    public static void setUp() {
        wall = new Cell(0, 1, Cell.Type.WALL);
        passage = new Cell(2, 3, Cell.Type.PASSAGE);
    }

    @Test
    public void assertThatGetSymbolReturnedRightStringTest() {
        assertEquals(wall.type().getSymbol(), Cell.Type.WALL.getSymbol());
        assertEquals(passage.type().getSymbol(), Cell.Type.PASSAGE.getSymbol());
    }

    @Test
    public void assertThatRowReturnedRightAnswerTest() {
        assertEquals(0, wall.row());
        assertEquals(2, passage.row());
    }

    @Test
    public void assertThatColReturnedRightAnswerTest() {
        assertEquals(1, wall.col());
        assertEquals(3, passage.col());
    }
}
