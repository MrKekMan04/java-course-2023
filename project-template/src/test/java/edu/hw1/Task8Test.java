package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    @Test
    public void taskDataTest() {
        assertTrue(Task8.knightBoardCapture(
            new int[][] {
                new int[] {0, 0, 0, 1, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 1, 0, 0, 0, 1, 0, 0},
                new int[] {0, 0, 0, 0, 1, 0, 1, 0},
                new int[] {0, 1, 0, 0, 0, 1, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 1, 0, 0, 0, 0, 0, 1},
                new int[] {0, 0, 0, 0, 1, 0, 0, 0}
            }
        ));

        assertFalse(Task8.knightBoardCapture(
            new int[][] {
                new int[] {1, 0, 1, 0, 1, 0, 1, 0},
                new int[] {0, 1, 0, 1, 0, 1, 0, 1},
                new int[] {0, 0, 0, 0, 1, 0, 1, 0},
                new int[] {0, 0, 1, 0, 0, 1, 0, 1},
                new int[] {1, 0, 0, 0, 1, 0, 1, 0},
                new int[] {0, 0, 0, 0, 0, 1, 0, 1},
                new int[] {1, 0, 0, 0, 1, 0, 1, 0},
                new int[] {0, 0, 0, 1, 0, 1, 0, 1}
            }
        ));

        assertFalse(Task8.knightBoardCapture(
            new int[][] {
                new int[] {0, 0, 0, 0, 1, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 1, 0, 0},
                new int[] {0, 0, 0, 1, 0, 0, 0, 0},
                new int[] {1, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 1, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 1, 0, 0},
                new int[] {0, 0, 0, 0, 0, 1, 0, 0},
                new int[] {1, 0, 0, 0, 0, 0, 0, 0}
            }
        ));
    }
}
