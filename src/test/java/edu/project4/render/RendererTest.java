package edu.project4.render;

import edu.project4.entity.FractalImage;
import edu.project4.entity.ImageFormat;
import edu.project4.entity.ImageUtils;
import edu.project4.entity.Rect;
import edu.project4.entity.transformation.PolarTransformation;
import edu.project4.entity.transformation.Transformation;
import edu.project4.entity.transformation.TrigonometricTransformation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RendererTest {
    private static final Path FRACTAL_PATH = Path.of("fractal.png");
    private static final List<Transformation> VARIATIONS =
        List.of(new TrigonometricTransformation(), new PolarTransformation(), new PolarTransformation());
    private static final Rect WORLD = new Rect(0, 0, 1920, 1080);
    private static final int SAMPLES = 10_000;
    private static final short ITER_PER_SAMPLE = (short) 128;
    private static final long SEED = System.currentTimeMillis();

    @AfterAll
    public static void afterAll() {
        FRACTAL_PATH.toFile().delete();
    }

    @Test
    public void assertThatMultiThreadWorksFasterThanSingleThread() {
        Renderer singleThreadRenderer = new SingleThreadRenderer();
        Renderer multiThreadRenderer = new MultiThreadRenderer(4);

        long singleThreadStart = System.nanoTime();
        singleThreadRenderer.gammaCorrect(singleThreadRenderer.render(
            FractalImage.create((int) WORLD.width(), (int) WORLD.height()),
            WORLD, VARIATIONS, SAMPLES, ITER_PER_SAMPLE, SEED
        ));
        long singleThreadWorkTime = System.nanoTime() - singleThreadStart;
        long multiThreadStart = System.nanoTime();
        multiThreadRenderer.gammaCorrect(multiThreadRenderer.render(
            FractalImage.create((int) WORLD.width(), (int) WORLD.height()),
            WORLD, VARIATIONS, SAMPLES, ITER_PER_SAMPLE, SEED
        ));
        long multiThreadWorkTime = System.nanoTime() - multiThreadStart;

        System.out.printf("Single thread work time: %d nanos%nMulti thread work time: %d nanos%nMulti faster: %f times"
            .formatted(singleThreadWorkTime, multiThreadWorkTime, (double) singleThreadWorkTime / multiThreadWorkTime));

        assertTrue(multiThreadWorkTime < singleThreadWorkTime);
    }

    @Test
    public void assertThatFractalImageSavesSuccessfully() {
        Renderer renderer = new MultiThreadRenderer(4);

        FractalImage image = renderer.render(
            FractalImage.create(200, 100),
            new Rect(0, 0, 200, 100),
            List.of(new TrigonometricTransformation()),
            400,
            (short) 100,
            System.currentTimeMillis()
        );

        Path path = Path.of("fractal.png");
        ImageUtils.save(renderer.gammaCorrect(image), path, ImageFormat.PNG);
        assertTrue(path.toFile().exists());
    }
}
