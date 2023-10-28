package edu.project2.entity;

import org.jetbrains.annotations.NotNull;

public class WeightedCoordinate extends Coordinate {
    private final double weight;

    public WeightedCoordinate(int row, int col, double weight) {
        super(row, col);

        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(@NotNull Coordinate o) {
        if (o instanceof WeightedCoordinate wo) {
            return Double.compare(this.weight, wo.weight);
        }

        return 1;
    }
}
