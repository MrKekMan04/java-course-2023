package edu.project3.repository;

import edu.project3.entity.LogRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LogRepositoryTest {
    private static LogRepository repository;
    private static final LogRecord RECORD = new LogRecord(
        "5.63.153.40 - - [30/May/2015:00:05:04 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.8)\"");

    @BeforeEach
    public void setUp() {
        repository = new LogRepository();
    }

    @Test
    public void assertThatRepositoryWorksRight() {
        assertEquals(0, repository.size());
        assertEquals(List.of(), repository.getAll());

        repository.add(RECORD);

        assertEquals(1, repository.size());
        assertEquals(List.of(RECORD), repository.getAll());
    }

    @Test
    public void assertThatMetaDataUpdatesRight() {
        assertNull(repository.getNewestRecord());
        assertNull(repository.getOldestRecord());
        assertEquals(0, repository.getResponsesSizeInBytes());

        repository.add(RECORD);

        assertEquals(RECORD, repository.getNewestRecord());
        assertEquals(RECORD, repository.getOldestRecord());
        assertEquals(RECORD.getBodyBytesSent(), repository.getResponsesSizeInBytes());
    }
}
