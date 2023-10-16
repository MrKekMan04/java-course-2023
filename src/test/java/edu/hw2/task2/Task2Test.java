package edu.hw2.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    @Test
    public void rectangleTest() {
        var rectangle = new Rectangle(10, 20);

        assertEquals(rectangle.calculateArea(), 200);

        rectangle = rectangle.setWidth(5);
        assertEquals(rectangle.calculateArea(), 100);

        rectangle = rectangle.setHeight(10);
        assertEquals(rectangle.calculateArea(), 50);
    }

    @Test
    public void squareTest() {
        var square = (Rectangle) new Square(10);

        assertEquals(square.calculateArea(), 100);

        square = square.setWidth(8);
        assertEquals(square.calculateArea(), 64);

        square = square.setHeight(5);
        assertEquals(square.calculateArea(), 25);
    }
}
