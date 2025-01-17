package edu.project4.render;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.entity.Point;
import edu.project4.entity.Rect;
import edu.project4.entity.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadRenderer extends Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        Random random = new Random(seed);
        final double maxX = world.width() / world.height();
        final double maxY = 1;
        Point center = new Point(world.x() + world.width() / 2, world.y() + world.height() / 2);

        for (int num = 0; num < samples; num++) {
            Point pw = getRandomPoint(random, maxX, maxY);

            for (short step = 0; step < iterPerSample; step++) {
                Transformation variation = variations.get(random.nextInt(variations.size()));
                pw = variation.apply(pw);

                double theta2 = 0.0;
                final int symmetry = 3;

                Point originalPoint = new Point(
                    (int) (world.width() - (((maxX - pw.x()) / (2 * maxX)) * world.width())),
                    (int) (world.height() - (((maxY - pw.y()) / (2 * maxY)) * world.height()))
                );
                double deltaXToCenter = originalPoint.x() - center.x();
                double deltaYToCenter = originalPoint.y() - center.y();
                double distanceToCenter = Math.sqrt(deltaXToCenter * deltaXToCenter + deltaYToCenter * deltaYToCenter);

                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, s++) {
                    Point pwr = new Point(
                        center.x() + distanceToCenter * Math.cos(Math.acos(deltaXToCenter / distanceToCenter) + theta2),
                        center.y() + distanceToCenter * Math.sin(Math.asin(deltaYToCenter / distanceToCenter) + theta2)
                    );

                    if (world.contains(pwr)) {
                        tryAddColor(canvas, variation, pwr);
                    }
                }
            }
        }

        return canvas;
    }

    @Override
    public FractalImage gammaCorrect(FractalImage image) {
        double max = 0.0;

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.getHitCount() != 0) {
                    double normal = Math.log10(pixel.getHitCount());

                    max = Math.max(max, normal);
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                double bright = Math.pow(Math.log10(pixel.getHitCount()) / max, 1 / GAMMA);

                pixel.correctColor(bright);
            }
        }

        return image;
    }
}
