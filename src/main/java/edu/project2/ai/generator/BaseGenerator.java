package edu.project2.ai.generator;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseGenerator implements MazeGenerator {
    protected static final Random RANDOM = new Random();
    private static final int CELL_GENERATION_DISTANCE = 2;
    private static final int MAX_FRONTIER_COUNT = 4;
    private static final int[] DELTA_FRONTIER = new int[] {-CELL_GENERATION_DISTANCE, 0, CELL_GENERATION_DISTANCE};

    protected Cell[][] initWallGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        return grid;
    }

    protected List<Coordinate> getFrontierCells(Coordinate source, Cell[][] grid) {
        ArrayList<Coordinate> frontiers = new ArrayList<>(MAX_FRONTIER_COUNT);

        for (int deltaRow : DELTA_FRONTIER) {
            for (int deltaCol : DELTA_FRONTIER) {
                if ((deltaRow != 0 && deltaCol == 0) || (deltaCol != 0 && deltaRow == 0)) {
                    Coordinate frontier = new Coordinate(source.getRow() + deltaRow, source.getCol() + deltaCol);

                    if (isCoordinateValid(grid, frontier) && isWall(grid, frontier)) {
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

    private boolean isWall(Cell[][] grid, Coordinate frontier) {
        return grid[frontier.getRow()][frontier.getCol()].type() == Cell.Type.WALL;
    }

    protected Coordinate getBetweenCoordinate(Coordinate from, Coordinate to) {
        return new Coordinate(
            from.getRow() + (to.getRow() - from.getRow()) / CELL_GENERATION_DISTANCE,
            from.getCol() + (to.getCol() - from.getCol()) / CELL_GENERATION_DISTANCE
        );
    }
}
