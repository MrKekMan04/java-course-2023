package edu.project4.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {

    private static final int COLOR_BITS_LENGTH = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage img = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                img.setRGB(x, y, pixelToIntRGB(image.pixel(x, y)));
            }
        }

        try {
            ImageIO.write(img, format.name(), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int pixelToIntRGB(Pixel pixel) {
        int rgb = pixel.getR();
        rgb = (rgb << COLOR_BITS_LENGTH) + pixel.getG();
        rgb = (rgb << COLOR_BITS_LENGTH) + pixel.getB();

        return rgb;
    }
}
