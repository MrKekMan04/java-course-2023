package edu.project2.ai.solver;

import edu.project2.entity.Coordinate;
import edu.project2.entity.CoordinateNode;
import edu.project2.entity.WeightedCoordinate;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Supplier;

public class AStarPathfinder extends BreadthFirstSearchPathfinder {
    @Override
    protected Coordinate createNextCoordinate(Coordinate next, Coordinate end) {
        return new WeightedCoordinate(next.getRow(), next.getCol(), calculateDistance(next, end));
    }

    @Override
    protected Supplier<? extends Queue<CoordinateNode>> queueFactory() {
        return PriorityQueue::new;
    }

    private static double calculateDistance(Coordinate from, Coordinate to) {
        int deltaX = to.getCol() - from.getCol();
        int deltaY = to.getRow() - from.getRow();

        return deltaX * deltaX + deltaY * deltaY;
    }
}
