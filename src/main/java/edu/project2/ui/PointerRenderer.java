package edu.project2.ui;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.List;
import java.util.Map;

public class PointerRenderer extends DefaultRenderer {
    private static final Map<Coordinate, String> DELTA_TO_SYMBOL = Map.of(
        new Coordinate(1, 0), "↓",
        new Coordinate(-1, 0), "↑",
        new Coordinate(0, 1), "→",
        new Coordinate(0, -1), "←"
    );

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder result = new StringBuilder(render(maze));
        Coordinate from = path.get(0);

        for (int i = 1; i < path.size(); i++) {
            Coordinate current = path.get(i);
            int symbolIndex = getSymbolIndex(from, maze);

            result.replace(symbolIndex, symbolIndex + 1, getPathSymbol(current, from));

            from = current;
        }

        int endPointIndex = getSymbolIndex(from, maze);
        result.replace(endPointIndex, endPointIndex + 1, getPathSymbol(from, from));

        return result.toString();
    }

    @Override
    protected String getPathSymbol(Coordinate to, Coordinate from) {
        Coordinate delta = new Coordinate(to.getRow() - from.getRow(), to.getCol() - from.getCol());

        return DELTA_TO_SYMBOL.containsKey(delta) ? DELTA_TO_SYMBOL.get(delta) : super.getPathSymbol(to, from);
    }
}
