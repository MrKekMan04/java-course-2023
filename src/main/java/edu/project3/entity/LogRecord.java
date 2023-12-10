package edu.project3.entity;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRecord {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(?<remoteAddress>(\\d{1,3}\\.){3}\\d{1,3}|((:|\\w{1,4}):).+:\\w{1,4}) - (?<remoteUser>.*) "
            + "\\[(?<timeLocal>\\d{2}/\\w{3}/(\\d+:){3}\\d+ \\+\\d+)] "
            + "\"(?<request>(?<method>GET|POST|PUT|PATCH|DELETE|HEAD|OPTIONS) "
            + "(?<path>.+) (?<protocol>.+))\" (?<status>\\d+) (?<bodyBytesSent>\\d+) "
            + "\"(?<httpReferer>.+)\" \"(?<httpUserAgent>.+)\"$"
    );
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss xx").localizedBy(Locale.US);

    private final String address;
    private final String user;
    private final OffsetDateTime dateTime;
    private final RequestType requestMethod;
    private final String requestPath;
    private final String requestProtocol;
    private final int responseStatus;
    private final int bodyBytesSent;
    private final String httpReferer;
    private final String httpUserAgent;

    public LogRecord(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);

        if (matcher.matches()) {
            address = matcher.group("remoteAddress");
            user = matcher.group("remoteUser");
            dateTime = OffsetDateTime.parse(matcher.group("timeLocal"), DATE_TIME_FORMATTER);
            requestMethod = Enum.valueOf(RequestType.class, matcher.group("method"));
            requestPath = matcher.group("path");
            requestProtocol = matcher.group("protocol");
            responseStatus = Integer.parseInt(matcher.group("status"));
            bodyBytesSent = Integer.parseInt(matcher.group("bodyBytesSent"));
            httpReferer = matcher.group("httpReferer");
            httpUserAgent = matcher.group("httpUserAgent");
        } else {
            throw new IllegalArgumentException("Log line input incorrect format!");
        }
    }

    public String getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public RequestType getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getRequestProtocol() {
        return requestProtocol;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public int getBodyBytesSent() {
        return bodyBytesSent;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }
}
