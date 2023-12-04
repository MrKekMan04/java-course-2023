package edu.project4.entity.transformation;

import edu.project4.entity.Point;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

public abstract class Transformation implements Function<Point, Point> {
    private static final Random RANDOM = new SecureRandom();
    private static final int MAX_COLOR_NUMBER = 256;
    private final int r;
    private final int g;
    private final int b;

    public Transformation() {
        this(RANDOM.nextInt(MAX_COLOR_NUMBER), RANDOM.nextInt(MAX_COLOR_NUMBER), RANDOM.nextInt(MAX_COLOR_NUMBER));
    }

    public Transformation(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int r() {
        return r;
    }

    public int g() {
        return g;
    }

    public int b() {
        return b;
    }
}
