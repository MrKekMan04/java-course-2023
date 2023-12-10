package edu.project3.entity;

import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

public class LogRecordTest {
    @Test
    public void assertThatLogRecordCreatesRight() {
        String logLine =
            "137.117.182.62 - - [29/May/2015:23:05:30 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"";
        LogRecord record = new LogRecord(logLine);

        assertEquals("137.117.182.62", record.getAddress());
        assertEquals("-", record.getUser());
        assertEquals(OffsetDateTime.of(2015, 5, 29, 23, 5, 30, 0, ZoneOffset.UTC), record.getDateTime());
        assertEquals(RequestType.GET, record.getRequestMethod());
        assertEquals("/downloads/product_2", record.getRequestPath());
        assertEquals("HTTP/1.1", record.getRequestProtocol());
        assertEquals(304, record.getResponseStatus());
        assertEquals(0, record.getBodyBytesSent());
        assertEquals("-", record.getHttpReferer());
        assertEquals("Debian APT-HTTP/1.3 (1.0.1ubuntu2)", record.getHttpUserAgent());
    }

    @Test
    public void assertThatIncorrectInputThrowException() {
        String incorrectTime =
            "137.117.182.62 - - [29/May/2015:25:05:30 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"";

        assertThrows(IllegalArgumentException.class, () -> new LogRecord("Some incorrect line"));
        assertThrows(DateTimeParseException.class, () -> new LogRecord(incorrectTime));
    }
}
