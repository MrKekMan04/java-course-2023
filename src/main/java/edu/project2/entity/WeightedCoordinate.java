package edu.project2.entity;

public class WeightedCoordinate extends Coordinate {
    private final double weight;

    public WeightedCoordinate(int row, int col, double weight) {
        super(row, col);

        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
