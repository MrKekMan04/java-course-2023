package edu.project2.ui;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.List;

public class DefaultRenderer extends AbstractRenderer {
    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder result = new StringBuilder(render(maze));

        path.forEach(coordinate -> {
            int symbolIndex = coordinate.getRow() * (maze.width() + 1) + coordinate.getCol();

            result.replace(symbolIndex, symbolIndex + 1, getPathSymbol(null, null));
        });

        return result.toString();
    }

    @Override
    protected String getPathSymbol(Coordinate to, Coordinate from) {
        return "*";
    }
}
