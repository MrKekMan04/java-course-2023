package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int width) {
        super(width, width);
    }

    @Override
    public Rectangle setWidth(int width) {
        return new Square(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        return new Square(height);
    }
}
