package edu.project2.ai.generator;

import edu.project2.entity.Maze;

public interface MazeGenerator {
    Maze generate(int height, int width);
}
