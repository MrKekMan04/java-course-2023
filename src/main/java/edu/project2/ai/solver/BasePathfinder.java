package edu.project2.ai.solver;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.CoordinateNode;
import edu.project2.entity.Maze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BasePathfinder<T extends Coordinate> implements Pathfinder {
    private static final int MAX_NEIGHBOUR_COUNT = 4;
    private static final int THREADS_COUNT = 4;
    private static final int[] DELTA_NEIGHBOUR = new int[] {-1, 0, 1};
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (maze == null || !areCoordinatesValid(maze, start, end) || isWall(maze, start) || isWall(maze, end)) {
            return null;
        }

        Queue<CoordinateNode<T>> queue = Stream.of(new CoordinateNode<>(createNextCoordinate(start, end)))
            .collect(Collectors.toCollection(queueFactory()));
        Set<Coordinate> visitedCoordinates = new HashSet<>();
        AtomicReference<CoordinateNode<T>> solveCord = new AtomicReference<>();

        try (ExecutorService pool = Executors.newFixedThreadPool(THREADS_COUNT)) {
            while (!queue.isEmpty() && solveCord.get() == null) {
                CompletableFuture.allOf(Stream.generate(() -> CompletableFuture.runAsync(() -> {
                            CoordinateNode<T> current;
                            lock.writeLock().lock();

                            try {
                                current = queue.poll();
                            } finally {
                                lock.writeLock().unlock();
                            }

                            if (current != null) {
                                if (end.equals(current.coordinate())) {
                                    solveCord.set(current);
                                } else {
                                    visitedCoordinates.add(current.coordinate());

                                    List<CoordinateNode<T>> neighbours =
                                        getNeighbour(current.coordinate(), visitedCoordinates, maze)
                                            .stream()
                                            .map(coordinate -> new CoordinateNode<>(
                                                current,
                                                createNextCoordinate(coordinate, end)
                                            ))
                                            .toList();

                                    lock.writeLock().lock();

                                    try {
                                        queue.addAll(neighbours);
                                    } finally {
                                        lock.writeLock().unlock();
                                    }
                                }
                            }
                        }, pool))
                        .limit(THREADS_COUNT)
                        .toArray(CompletableFuture[]::new))
                    .join();
            }
        }

        return solveCord.get() != null && end.equals(solveCord.get().coordinate()) ? buildPath(solveCord.get()) : null;
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

    protected abstract Supplier<Queue<CoordinateNode<T>>> queueFactory();

    protected abstract T createNextCoordinate(Coordinate next, Coordinate end);

    private boolean isWall(Maze maze, Coordinate coordinate) {
        return maze.grid()[coordinate.getRow()][coordinate.getCol()].type() == Cell.Type.WALL;
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

    private static List<Coordinate> buildPath(CoordinateNode<? extends Coordinate> pathNode) {
        List<Coordinate> path = new ArrayList<>();
        CoordinateNode<? extends Coordinate> copyPathNode = pathNode;

        while (copyPathNode != null) {
            path.add(copyPathNode.coordinate());
            copyPathNode = copyPathNode.child();
        }

        return path.reversed();
    }
}
