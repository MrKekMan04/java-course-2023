package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    @Test
    public void callingInfoTest() {
        var callingInfos = Task4.callingInfo();
        var callingMethod = new CallingInfo(Task4.class.getName(), Task4.class.getMethods()[0].getName());

        assertTrue(callingInfos.stream()
            .anyMatch(info -> info.equals(callingMethod)));
    }
}
