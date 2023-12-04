package edu.project4.entity.transformation;

import edu.project4.entity.Point;

public class TrigonometricTransformation extends Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.cos(point.x()), Math.sin(point.y()));
    }
}
