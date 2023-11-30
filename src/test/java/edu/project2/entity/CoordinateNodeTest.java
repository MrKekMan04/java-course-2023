package edu.project2.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateNodeTest {
    private static Coordinate someCoordinate;
    private static CoordinateNode<Coordinate> withoutChild;
    private static CoordinateNode<Coordinate> withChild;

    @BeforeAll
    public static void setUp() {
        someCoordinate = new Coordinate(0, 0);
        withoutChild = new CoordinateNode<>(someCoordinate);
        withChild = new CoordinateNode<>(withoutChild, someCoordinate);
    }

    @Test
    public void assertThatCoordinateNodeWithoutChildReturnedNullableChildTest() {
        assertNull(withoutChild.child());
    }

    @Test
    public void assertThatCoordinateNodeWithChildReturnedRightAnswerTest() {
        assertEquals(withoutChild, withChild.child());
    }

    @Test
    public void assertThatCoordinateReturnedRightAnswerTest() {
        assertEquals(someCoordinate, withoutChild.coordinate());
        assertEquals(someCoordinate, withChild.coordinate());
    }
}
