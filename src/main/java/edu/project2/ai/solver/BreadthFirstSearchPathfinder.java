package edu.project2.ai.solver;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.CoordinateNode;
import edu.project2.entity.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreadthFirstSearchPathfinder implements Pathfinder {
    private static final int MAX_NEIGHBOUR_COUNT = 4;
    private static final int[] DELTA_NEIGHBOUR = new int[] {-1, 0, 1};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (maze == null || !areCoordinatesValid(maze, start, end) || isWall(maze, start) || isWall(maze, end)) {
            return null;
        }

        Queue<CoordinateNode> queue = Stream.of(new CoordinateNode(createNextCoordinate(start, end)))
            .collect(Collectors.toCollection(queueFactory()));
        Set<Coordinate> visitedCoordinates = new HashSet<>();
        CoordinateNode current = new CoordinateNode(null);

        while (!end.equals(current.coordinate()) && !queue.isEmpty()) {
            current = queue.poll();
            visitedCoordinates.add(current.coordinate());

            CoordinateNode finalCurrent = current;
            queue.addAll(getNeighbour(current.coordinate(), visitedCoordinates, maze)
                .stream()
                .map(coordinate -> new CoordinateNode(finalCurrent, createNextCoordinate(coordinate, end)))
                .toList());
        }

        return end.equals(current.coordinate()) ? buildPath(current) : null;
    }

    private static boolean areCoordinatesValid(Maze maze, Coordinate... coordinates) {
        for (Coordinate coordinate : coordinates) {
            if (coordinate == null
                || coordinate.getRow() < 0 || coordinate.getRow() >= maze.height()
                || coordinate.getCol() < 0 || coordinate.getCol() >= maze.width()) {
                return false;
            }
        }

        return true;
    }

    private boolean isWall(Maze maze, Coordinate coordinate) {
        return maze.grid()[coordinate.getRow()][coordinate.getCol()].type() == Cell.Type.WALL;
    }

    protected Supplier<? extends Queue<CoordinateNode>> queueFactory() {
        return ArrayDeque::new;
    }

    private List<Coordinate> getNeighbour(Coordinate source, Set<Coordinate> visited, Maze maze) {
        ArrayList<Coordinate> neighbours = new ArrayList<>(MAX_NEIGHBOUR_COUNT);

        for (int deltaRow : DELTA_NEIGHBOUR) {
            for (int deltaCol : DELTA_NEIGHBOUR) {
                if ((deltaRow != 0 && deltaCol == 0) || (deltaCol != 0 && deltaRow == 0)) {
                    Coordinate neighbour = new Coordinate(source.getRow() + deltaRow, source.getCol() + deltaCol);

                    if (areCoordinatesValid(maze, neighbour)
                        && !isWall(maze, neighbour)
                        && !visited.contains(neighbour)) {
                        neighbours.add(neighbour);
                    }
                }
            }
        }

        return neighbours;
    }

    protected Coordinate createNextCoordinate(Coordinate next, Coordinate end) {
        return next;
    }

    private static List<Coordinate> buildPath(CoordinateNode pathNode) {
        List<Coordinate> path = new ArrayList<>();
        CoordinateNode copyPathNode = pathNode;

        while (copyPathNode != null) {
            path.add(copyPathNode.coordinate());
            copyPathNode = copyPathNode.child();
        }

        return path.reversed();
    }
}
