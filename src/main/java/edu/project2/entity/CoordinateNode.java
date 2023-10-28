package edu.project2.entity;

import org.jetbrains.annotations.NotNull;

public record CoordinateNode(CoordinateNode child, Coordinate coordinate) implements Comparable<CoordinateNode> {
    public CoordinateNode(Coordinate coordinate) {
        this(null, coordinate);
    }

    @Override
    public int compareTo(@NotNull CoordinateNode o) {
        return coordinate.compareTo(o.coordinate);
    }
}
