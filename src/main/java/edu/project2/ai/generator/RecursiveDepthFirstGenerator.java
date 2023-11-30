package edu.project2.ai.generator;

import edu.project2.entity.Cell;
import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.List;
import java.util.Stack;

public class RecursiveDepthFirstGenerator extends BaseGenerator {
    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            return null;
        }

        Cell[][] grid = initWallGrid(height, width);
        Stack<Coordinate> stack = new Stack<>();

        Coordinate startPoint = new Coordinate(RANDOM.nextInt(height), RANDOM.nextInt(width));
        stack.push(startPoint);

        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();
            List<Coordinate> frontierCells = getFrontierCells(current, grid);

            if (!frontierCells.isEmpty()) {
                Coordinate randomNeighbor = frontierCells.get(RANDOM.nextInt(frontierCells.size()));

                for (Coordinate between : List.of(
                    current,
                    getBetweenCoordinate(current, randomNeighbor),
                    randomNeighbor
                )) {
                    int row = between.getRow();
                    int col = between.getCol();

                    grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                }

                stack.push(randomNeighbor);
            } else {
                stack.pop();
            }
        }

        return new Maze(height, width, grid);
    }
}
