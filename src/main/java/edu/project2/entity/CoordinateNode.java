package edu.project2.entity;

public record CoordinateNode<T extends Coordinate>(CoordinateNode<T> child, T coordinate) {
    public CoordinateNode(T coordinate) {
        this(null, coordinate);
    }
}
