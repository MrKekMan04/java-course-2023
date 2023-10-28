package edu.project2.ai.generator;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomizedPrimGenerator implements MazeGenerator {
    private static final int CELL_GENERATION_DISTANCE = 2;
    private static final int MAX_FRONTIER_COUNT = 4;

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            return null;
        }

        final Random random = new Random();
        final Cell[][] grid = initWallGrid(height, width);
        final HashMap<Coordinate, Coordinate> possiblePassage = new HashMap<>();
        final Coordinate firstPoint = new Coordinate(random.nextInt(height), random.nextInt(width));

        possiblePassage.put(firstPoint, firstPoint);

        while (!possiblePassage.isEmpty()) {
            Coordinate current = possiblePassage
                .keySet()
                .toArray(Coordinate[]::new)[random.nextInt(possiblePassage.size())];

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

    private Cell[][] initWallGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        return grid;
    }

    private List<Coordinate> getFrontierCells(Coordinate source, Cell[][] grid) {
        int[] delta = new int[] {-CELL_GENERATION_DISTANCE, 0, CELL_GENERATION_DISTANCE};
        ArrayList<Coordinate> frontiers = new ArrayList<>(MAX_FRONTIER_COUNT);

        for (int deltaRow : delta) {
            for (int deltaCol : delta) {
                if ((deltaRow != 0 && deltaCol == 0) || (deltaCol != 0 && deltaRow == 0)) {
                    Coordinate frontier = new Coordinate(source.getRow() + deltaRow, source.getCol() + deltaCol);

                    if (isCoordinateValid(grid, frontier)
                        && grid[frontier.getRow()][frontier.getCol()].type() == Cell.Type.WALL) {
                        frontiers.add(frontier);
                    }
                }
            }
        }

        return frontiers;
    }

    private boolean isCoordinateValid(Cell[][] grid, Coordinate coordinate) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    private Coordinate getBetweenCoordinate(Coordinate from, Coordinate to) {
        return new Coordinate(
            from.getRow() + (to.getRow() - from.getRow()) / CELL_GENERATION_DISTANCE,
            from.getCol() + (to.getCol() - from.getCol()) / CELL_GENERATION_DISTANCE
        );
    }
}
