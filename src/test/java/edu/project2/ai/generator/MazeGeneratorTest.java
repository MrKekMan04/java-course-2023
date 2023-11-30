package edu.project2.ai.generator;

import edu.project2.entity.Cell;
import edu.project2.entity.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class MazeGeneratorTest {
    private static final int LOOP_TEST_ITERATIONS = 1_000;
    private static Random random;
    private static MazeGenerator randomizedPrimGenerator;
    private static MazeGenerator recursiveDepthFirstGenerator;

    @BeforeAll
    public static void setUp() {
        random = new Random();
        randomizedPrimGenerator = new RandomizedPrimGenerator();
        recursiveDepthFirstGenerator = new RecursiveDepthFirstGenerator();
    }

    @Test
    public void assertThatWithPrimeGeneratorPassagesGeneratesMoreThen30PercentTest() {
        IntStream.range(0, LOOP_TEST_ITERATIONS)
            .mapToObj(i -> randomizedPrimGenerator.generate(random.nextInt(10, 100), random.nextInt(10, 100)))
            .forEach(maze -> assertTrue(getMazePassagesRate(maze) > 0.3));
    }

    @Test
    public void assertThatWithRecursiveDepthFirstPassagesGeneratesMoreThen30PercentTest() {
        IntStream.range(0, LOOP_TEST_ITERATIONS)
            .mapToObj(i -> recursiveDepthFirstGenerator.generate(random.nextInt(10, 100), random.nextInt(10, 100)))
            .forEach(maze -> assertTrue(getMazePassagesRate(maze) > 0.3));
    }

    @Test
    public void assertThatIncorrectInputReturnedNullTest() {
        assertNull(randomizedPrimGenerator.generate(-1, 20));
        assertNull(randomizedPrimGenerator.generate(1, 0));
        assertNull(recursiveDepthFirstGenerator.generate(-1, 20));
        assertNull(recursiveDepthFirstGenerator.generate(1, 0));
    }

    private double getMazePassagesRate(Maze maze) {
        int walls = 0;
        int passages = 0;
        Cell[][] grid = maze.grid();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                switch (cell.type()) {
                    case WALL -> walls++;
                    case PASSAGE -> passages++;
                }
            }
        }

        return passages / ((double) passages + walls);
    }
}
