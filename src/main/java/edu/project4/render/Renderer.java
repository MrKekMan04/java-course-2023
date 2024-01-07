package edu.project4.render;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.entity.Point;
import edu.project4.entity.Rect;
import edu.project4.entity.transformation.Transformation;
import java.util.List;
import java.util.Random;

public abstract class Renderer {
    protected static final double GAMMA = 2.5;

    public abstract FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    );

    public abstract FractalImage gammaCorrect(FractalImage image);

    protected Point getRandomPoint(Random random, double maxX, double maxY) {
        return new Point(random.nextDouble(-maxX, maxX), random.nextDouble(-maxY, maxY));
    }

    protected void tryAddColor(FractalImage canvas, Transformation variation, Point pwr) {
        Pixel pixel = canvas.data()[((int) pwr.y()) * canvas.width() + (int) pwr.x()];

        if (pixel == null) {
            return;
        }

        pixel.addColor(variation.r(), variation.g(), variation.b());
    }
}
