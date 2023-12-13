package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public record GameRecord(@NotNull String name, @Min(1960) @Max(3000) int releaseYear) {
}
