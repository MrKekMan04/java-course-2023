package edu.hw10.task1;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {
    @Test
    public void assertThatObjectsGeneratesRight()
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        GameRecord record = generator.nextObject(GameRecord.class);
        Game game = generator.nextObject(Game.class);

        assertNotNull(record.name());
        assertNotNull(game.getName());
        assertTrue(record.releaseYear() < 3000 && record.releaseYear() >= 1960);
        assertTrue(game.getReleaseYear() < 3000 && game.getReleaseYear() >= 1960);
    }
}
