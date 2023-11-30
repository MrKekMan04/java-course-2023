package edu.project2.ai.generator;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.HashMap;
import java.util.List;

public class RandomizedPrimGenerator extends BaseGenerator {
    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            return null;
        }

        final Cell[][] grid = initWallGrid(height, width);
        final HashMap<Coordinate, Coordinate> possiblePassage = new HashMap<>();
        final Coordinate firstPoint = new Coordinate(RANDOM.nextInt(height), RANDOM.nextInt(width));

        possiblePassage.put(firstPoint, firstPoint);

        while (!possiblePassage.isEmpty()) {
            Coordinate current = possiblePassage
                .keySet()
                .toArray(Coordinate[]::new)[RANDOM.nextInt(possiblePassage.size())];

            Coordinate from = possiblePassage.get(current);

            for (Coordinate between : List.of(current, getBetweenCoordinate(current, from))) {
                int row = between.getRow();
                int col = between.getCol();

                grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
            }

            possiblePassage.remove(current);

            getFrontierCells(new Coordinate(current.getRow(), current.getCol()), grid)
                .forEach(next -> possiblePassage.put(next, current));
        }

        return new Maze(height, width, grid);
    }
}
