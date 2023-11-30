package edu.project2.ai.solver;

import edu.project2.entity.Coordinate;
import edu.project2.entity.CoordinateNode;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Supplier;

public class BreadthFirstSearchPathfinder extends BasePathfinder<Coordinate> {

    @Override
    protected Supplier<Queue<CoordinateNode<Coordinate>>> queueFactory() {
        return ArrayDeque::new;
    }

    @Override
    protected Coordinate createNextCoordinate(Coordinate next, Coordinate end) {
        return next;
    }
}
