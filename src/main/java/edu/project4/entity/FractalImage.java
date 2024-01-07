package edu.project4.entity;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];

        for (int i = 0; i < width * height; i++) {
            pixels[i] = new Pixel();
        }

        return new FractalImage(pixels, width, height);
    }

    public Pixel pixel(int x, int y) {
        return data[y * width + x];
    }
}
