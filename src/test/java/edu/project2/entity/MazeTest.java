package edu.project2.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private static Cell[][] grid;
    private static Maze maze;

    @BeforeAll
    public static void setUp() {
        grid = new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL)},
            new Cell[] {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE)},
        };
        maze = new Maze(2, 2, grid);
    }

    @Test
    public void assertThatHeightReturnedRightAnswerTest() {
        assertEquals(2, maze.height());
    }

    @Test
    public void assertThatWidthReturnedRightAnswerTest() {
        assertEquals(2, maze.width());
    }

    @Test
    public void assertThatGridReturnedRightAnswerTest() {
        assertTrue(Arrays.deepEquals(grid.clone(), maze.grid()));
    }
}
