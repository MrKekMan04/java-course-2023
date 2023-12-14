package edu.hw6.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HackerNewsTest {
    @Test
    public void assertThatHackerNewsTopStoriesWorksRight() {
        final int maxLength = 500;

        assertTrue(maxLength >= HackerNews.hackerNewsTopStories().length);
    }

    @Test
    public void assertThatNewsWorksRight() {
        final String expected1 = "JDK 21 Release Notes";
        final String expected2 = "Why Is Russia Introducing an Islamic Banking System [video]";

        assertEquals(expected1, HackerNews.news(37570037));
        assertEquals(expected2, HackerNews.news(37570033));
    }

    @Test
    public void assertThatNewsReturnedNullOnNonExistsId() {
        assertNull(HackerNews.news(Long.MAX_VALUE));
    }
}
