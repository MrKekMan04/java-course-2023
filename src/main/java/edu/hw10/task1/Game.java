package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public class Game {
    @NotNull
    private String name;
    @Min(1960)
    @Max(3000)
    private int releaseYear;

    public static Game create() {
        return new Game();
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
