package edu.project2.ui;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder result = new StringBuilder();

        IntStream.range(0, maze.height()).forEach(i -> result
            .append(Arrays.stream(maze.grid()[i])
                .map(cell -> cell.type().getSymbol())
                .collect(Collectors.joining()))
            .append("\n"));

        return result.toString();
    }

    protected int getSymbolIndex(Coordinate coordinate, Maze maze) {
        return coordinate.getRow() * (maze.width() + 1) + coordinate.getCol();
    }

    @Override
    public abstract String render(Maze maze, List<Coordinate> path);

    protected abstract String getPathSymbol(Coordinate to, Coordinate from);
}
