package edu.project3.repository;

import edu.project3.entity.LogRecord;
import java.time.Duration;

public class LogRepository extends Repository<LogRecord> {
    private LogRecord newestRecord;
    private LogRecord oldestRecord;
    private long responsesSizeInBytes;

    @Override
    public void add(LogRecord logRecord) {
        updateMetaData(logRecord);

        super.add(logRecord);
    }

    public LogRecord getNewestRecord() {
        return newestRecord;
    }

    public LogRecord getOldestRecord() {
        return oldestRecord;
    }

    public long getResponsesSizeInBytes() {
        return responsesSizeInBytes;
    }

    private void updateMetaData(LogRecord logRecord) {
        if (newestRecord == null
            || Duration.between(logRecord.getDateTime(), newestRecord.getDateTime()).toMillis() > 0) {
            newestRecord = logRecord;
        }

        if (oldestRecord == null
            || Duration.between(logRecord.getDateTime(), oldestRecord.getDateTime()).toMillis() < 0) {
            oldestRecord = logRecord;
        }

        responsesSizeInBytes += logRecord.getBodyBytesSent();
    }
}
