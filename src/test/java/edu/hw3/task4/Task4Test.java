package edu.hw3.task4;

import org.junit.jupiter.api.Test;
import static edu.hw3.task4.Task4.convertToRoman;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    @Test
    public void assertThatMethodReturnedRightAnswerTest() {
        assertEquals(convertToRoman(2), "II");
        assertEquals(convertToRoman(12), "XII");
        assertEquals(convertToRoman(16), "XVI");
        assertEquals(convertToRoman(109), "CIX");
        assertEquals(convertToRoman(843), "DCCCXLIII");
        assertEquals(convertToRoman(65), "LXV");
        assertEquals(convertToRoman(2016), "MMXVI");
    }

    @Test
    public void assertThatImpossibleRomanNumberReturnedNullTest() {
        assertNull(convertToRoman(-1000));
        assertNull(convertToRoman(0));
        assertNull(convertToRoman(4000));
    }
}
