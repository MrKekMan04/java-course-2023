package edu.project3.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseStatusesTest {
    @Test
    public void assertThatStatusCodesReturnedRightTitle() {
        assertEquals("OK", ResponseStatuses.getStatusFromCode(200));
        assertEquals("Created", ResponseStatuses.getStatusFromCode(201));
        assertEquals("Not Found", ResponseStatuses.getStatusFromCode(404));
        assertEquals("Unknown", ResponseStatuses.getStatusFromCode(10234));
    }
}
