package edu.project2.ai.generator;

import edu.project2.entity.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class MazeGeneratorTest {
    private static final int LOOP_TEST_ITERATIONS = 1_000;
    private static Random random;
    private static RandomizedPrimGenerator generator;

    @BeforeAll
    public static void setUp() {
        random = new Random();
        generator = new RandomizedPrimGenerator();
    }

    @Test
    public void assertThatWithPrimeGeneratorPassagesGeneratesMoreThen30PercentTest() {
        IntStream.range(0, LOOP_TEST_ITERATIONS)
            .mapToObj(i -> generator.generate(random.nextInt(10, 100), random.nextInt(10, 100)))
            .forEach(maze -> {
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

                assertTrue(passages / ((double) passages + walls) > 0.3);
            });
    }

    @Test
    public void assertThatIncorrectInputReturnedNullTest() {
        assertNull(generator.generate(-1, 20));
        assertNull(generator.generate(1, 0));
    }
}
