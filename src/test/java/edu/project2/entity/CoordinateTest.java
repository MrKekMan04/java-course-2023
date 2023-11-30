package edu.project2.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
    private static Coordinate coordinate;
    private static WeightedCoordinate weightedCoordinate;

    @BeforeAll
    public static void setUp() {
        coordinate = new Coordinate(0, 0);
        weightedCoordinate = new WeightedCoordinate(0, 0, 0);
    }

    @Test
    public void assertThatGetRowReturnedRightAnswerTest() {
        assertEquals(0, coordinate.getRow());
        assertEquals(0, weightedCoordinate.getRow());
    }

    @Test
    public void assertThatGetColReturnedRightAnswerTest() {
        assertEquals(0, coordinate.getCol());
        assertEquals(0, weightedCoordinate.getCol());
    }

    @Test
    public void assertThatGetWeightReturnedRightAnswerTest() {
        assertEquals(0, weightedCoordinate.getWeight());
    }

    @Test
    public void assertThatSameCoordinateAndWeightedCoordinateEqualsTest() {
        assertEquals(coordinate, weightedCoordinate);
    }

    @Test
    public void assertThatSameCoordinateAndWeightedCoordinateHashCodesEqualsTest() {
        assertEquals(coordinate.hashCode(), weightedCoordinate.hashCode());
    }
}
