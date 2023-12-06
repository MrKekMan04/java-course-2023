package edu.hw5.task6;

import org.junit.jupiter.api.Test;
import static edu.hw5.task6.Task6.isSubstring;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {
    @Test
    public void assertThatIsSubstringReturnedRightAnswer() {
        assertTrue(isSubstring("abc", "achfdbaabgabcaabg"));
        assertTrue(isSubstring("", "ac"));
        assertFalse(isSubstring("abc", "ab"));
        assertFalse(isSubstring("abc", "abbc"));
    }

    @Test
    public void assertThatRegularSymbolsDoesNotWork() {
        assertFalse(isSubstring(".", "ab"));
        assertFalse(isSubstring("\\d{4}", "0000"));
        assertTrue(isSubstring(".", "a.b"));
        assertTrue(isSubstring("\\d{4}", "\\d{4}"));
    }

    @Test
    public void assertThatNullInputReturnedFalse() {
        assertFalse(isSubstring(null, ""));
        assertFalse(isSubstring("", null));
    }
}
