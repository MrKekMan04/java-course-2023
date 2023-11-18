package edu.project3.entity;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public final class ResponseStatuses {
    private static final Map<Integer, String> STATUSES = new HashMap<>();

    static {
        STATUSES.put(100, "Continue");
        STATUSES.put(101, "Switching Protocols");
        STATUSES.put(102, "Processing");
        STATUSES.put(103, "Early Hints");

        STATUSES.put(200, "OK");
        STATUSES.put(201, "Created");
        STATUSES.put(202, "Accepted");
        STATUSES.put(203, "Non-Authoritative Information");
        STATUSES.put(204, "No Content");
        STATUSES.put(205, "Reset Content");
        STATUSES.put(206, "Partial Content");
        STATUSES.put(207, "Multi-Status");
        STATUSES.put(208, "Already Reported");
        STATUSES.put(226, "IM Used");

        STATUSES.put(300, "Multiple Choices");
        STATUSES.put(301, "Moved Permanently");
        STATUSES.put(302, "Found");
        STATUSES.put(303, "See Other");
        STATUSES.put(304, "Not Modified");
        STATUSES.put(305, "Use Proxy");
        STATUSES.put(307, "Temporary Redirect");
        STATUSES.put(308, "Permanent Redirect");

        STATUSES.put(400, "Bad Request");
        STATUSES.put(401, "Unauthorized");
        STATUSES.put(402, "Payment Required");
        STATUSES.put(403, "Forbidden");
        STATUSES.put(404, "Not Found");
        STATUSES.put(405, "Method Not Allowed");
        STATUSES.put(406, "Not Acceptable");
        STATUSES.put(407, "Proxy Authentication Required");
        STATUSES.put(408, "Request Timeout");
        STATUSES.put(409, "Conflict");
        STATUSES.put(410, "Gone");
        STATUSES.put(411, "Length Required");
        STATUSES.put(412, "Precondition Failed");
        STATUSES.put(413, "Payload Too Large");
        STATUSES.put(414, "URI Too Long");
        STATUSES.put(415, "Unsupported Media Type");
        STATUSES.put(416, "Range Not Satisfiable");
        STATUSES.put(417, "Expectation Failed");
        STATUSES.put(418, "I'm a teapot");
        STATUSES.put(421, "Misdirected Request");
        STATUSES.put(422, "Unprocessable Entity");
        STATUSES.put(423, "Locked");
        STATUSES.put(424, "Failed Dependency");
        STATUSES.put(425, "Too Early");
        STATUSES.put(426, "Upgrade Required");
        STATUSES.put(428, "Precondition Required");
        STATUSES.put(429, "Too Many Requests");
        STATUSES.put(431, "Request Header Fields Too Large");
        STATUSES.put(449, "Retry With");
        STATUSES.put(451, "Unavailable For Legal Reasons");
        STATUSES.put(499, "Client Closed Request");

        STATUSES.put(500, "Internal Server Error");
        STATUSES.put(501, "Not Implemented");
        STATUSES.put(502, "Bad Gateway");
        STATUSES.put(503, "Service Unavailable");
        STATUSES.put(504, "Gateway Timeout");
        STATUSES.put(505, "HTTP Version Not Supported");
        STATUSES.put(506, "Variant Also Negotiates");
        STATUSES.put(507, "Insufficient Storage");
        STATUSES.put(508, "Loop Detected");
        STATUSES.put(510, "Not Extended");
        STATUSES.put(511, "Network Authentication Required");
        STATUSES.put(520, "Unknown Error");
        STATUSES.put(521, "Web Server Is Down");
        STATUSES.put(522, "Connection Timed Out");
        STATUSES.put(523, "Origin Is Unreachable");
        STATUSES.put(524, "A Timeout Occurred");
        STATUSES.put(525, "SSL Handshake Failed");
        STATUSES.put(526, "Invalid SSL Certificate");
    }

    private ResponseStatuses() {
    }

    public static String getStatusFromCode(int code) {
        return STATUSES.getOrDefault(code, "Unknown");
    }
}
