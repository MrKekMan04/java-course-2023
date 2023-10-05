package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    @Test
    public void taskDataTest() {
        assertEquals(Task4.fixString("оПомигети псаривьтс ртко!и"), "Помогите исправить строки!");
        assertEquals(Task4.fixString("123456"), "214365");
        assertEquals(Task4.fixString("hTsii  s aimex dpus rtni.g"), "This is a mixed up string.");
        assertEquals(Task4.fixString("badce"), "abcde");
    }

    @Test
    public void nullStringTest() {
        assertNull(Task4.fixString(null));
    }
}
