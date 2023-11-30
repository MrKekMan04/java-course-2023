package edu.project2.ui;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RendererTest {
    private static Maze maze;
    private static List<Coordinate> path;
    private static Renderer defaultRenderer;
    private static Renderer pointerRenderer;

    @BeforeAll
    public static void setUp() {
        maze = new Maze(2, 2, new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL)},
            new Cell[] {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE)}
        });
        path = List.of(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1));
        defaultRenderer = new DefaultRenderer();
        pointerRenderer = new PointerRenderer();
    }

    @Test
    public void assertThatMazeRenderReturnedRightAnswer() {
        final String mazeResult = "◆◇\n◆◆\n";

        assertEquals(mazeResult, defaultRenderer.render(maze));
        assertEquals(mazeResult, pointerRenderer.render(maze));
    }

    @Test
    public void assertThatMazeWithPathRenderReturnedRightAnswer() {
        final String defaultResult = "*◇\n**\n";
        final String pointerResult = "↓◇\n→*\n";

        assertEquals(defaultResult, defaultRenderer.render(maze, path));
        assertEquals(pointerResult, pointerRenderer.render(maze, path));
    }
}
