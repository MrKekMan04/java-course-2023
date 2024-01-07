package edu.project4.entity.transformation;

import edu.project4.entity.Point;

public class SphereTransformation extends Transformation {
    @Override
    public Point apply(Point point) {
        double delimiter = (point.x() * point.x() + point.y() * point.y());

        return new Point(point.x() / delimiter, point.y() / delimiter);
    }
}
