package edu.project2.ai.solver;

import edu.project2.entity.Coordinate;
import edu.project2.entity.WeightedCoordinate;

public class AStarPathfinder extends BreadthFirstSearchPathfinder {
    @Override
    protected Coordinate createNextCoordinate(Coordinate next, Coordinate end) {
        return new WeightedCoordinate(next.getRow(), next.getCol(), calculateDistance(next, end));
    }

    private static double calculateDistance(Coordinate from, Coordinate to) {
        int deltaX = to.getCol() - from.getCol();
        int deltaY = to.getRow() - from.getRow();

        return deltaX * deltaX + deltaY * deltaY;
    }
}
