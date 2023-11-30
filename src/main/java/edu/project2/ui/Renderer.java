package edu.project2.ui;

import edu.project2.entity.Coordinate;
import edu.project2.entity.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
