package edu.project2.ai.solver;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PathfinderTest {
    private static Maze maze;
    private static List<Coordinate> correctMazePath;
    private static Coordinate correctMazeStartPoint;
    private static Coordinate correctMazeEndPoint;
    private static Coordinate wallMazePoint;
    private static Pathfinder breadthFirstSearch;
    private static Pathfinder aStar;

    @BeforeAll
    public static void sutUp() {
        maze = new Maze(2, 2, new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.WALL)},
            new Cell[] {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE)}
        });
        correctMazePath = List.of(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1));
        correctMazeStartPoint = new Coordinate(0, 0);
        correctMazeEndPoint = new Coordinate(1, 1);
        wallMazePoint = new Coordinate(0, 1);
        breadthFirstSearch = new BreadthFirstSearchPathfinder();
        aStar = new AStarPathfinder();
    }

    @Test
    public void assertThatInCorrectMazePathIsFoundTest() {
        assertNotNull(breadthFirstSearch.solve(maze, correctMazeStartPoint, correctMazeEndPoint));
        assertNotNull(aStar.solve(maze, correctMazeStartPoint, correctMazeEndPoint));
    }

    @Test
    public void assertThatInCorrectMazePathIsRightTest() {
        assertEquals(correctMazePath, breadthFirstSearch.solve(maze, correctMazeStartPoint, correctMazeEndPoint));
        assertEquals(correctMazePath, aStar.solve(maze, correctMazeStartPoint, correctMazeEndPoint));
    }

    @Test
    public void assertThatIncorrectInputReturnedNullTest() {
        for (Pathfinder pathfinder : List.of(breadthFirstSearch, aStar)) {
            assertNull(pathfinder.solve(null, correctMazeStartPoint, correctMazeEndPoint));
            assertNull(pathfinder.solve(maze, null, correctMazeEndPoint));
            assertNull(pathfinder.solve(maze, correctMazeStartPoint, null));
            assertNull(pathfinder.solve(maze, new Coordinate(-1, 0), correctMazeEndPoint));
            assertNull(pathfinder.solve(maze, correctMazeStartPoint, new Coordinate(1, 2)));
            assertNull(pathfinder.solve(maze, wallMazePoint, correctMazeEndPoint));
            assertNull(pathfinder.solve(maze, correctMazeStartPoint, wallMazePoint));
        }
    }

    @Test
    public void assertThatImpossibleReachReturnedNullTest() {
        final Maze impossibleMaze = new Maze(3, 1, new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.PASSAGE)},
            new Cell[] {new Cell(1, 0, Cell.Type.WALL)},
            new Cell[] {new Cell(2, 0, Cell.Type.PASSAGE)}
        });
        final Coordinate start = new Coordinate(0, 0);
        final Coordinate end = new Coordinate(2, 0);

        assertNull(breadthFirstSearch.solve(impossibleMaze, start, end));
        assertNull(aStar.solve(impossibleMaze, start, end));
    }
}
