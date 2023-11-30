package edu.project2.ai.solver;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.List;

public interface Pathfinder {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
