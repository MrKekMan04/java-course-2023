package edu.project2.entity;

import java.util.Objects;

public class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate other) {
            return row == other.row && col == other.col;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
