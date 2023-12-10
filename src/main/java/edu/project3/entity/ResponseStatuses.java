package edu.project3.entity;

import java.net.HttpURLConnection;
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

        STATUSES.put(HttpURLConnection.HTTP_OK, "OK");
        STATUSES.put(HttpURLConnection.HTTP_CREATED, "Created");
        STATUSES.put(HttpURLConnection.HTTP_ACCEPTED, "Accepted");
        STATUSES.put(HttpURLConnection.HTTP_NOT_AUTHORITATIVE, "Non-Authoritative Information");
        STATUSES.put(HttpURLConnection.HTTP_NO_CONTENT, "No Content");
        STATUSES.put(HttpURLConnection.HTTP_RESET, "Reset Content");
        STATUSES.put(HttpURLConnection.HTTP_PARTIAL, "Partial Content");
        STATUSES.put(207, "Multi-Status");
        STATUSES.put(208, "Already Reported");
        STATUSES.put(226, "IM Used");

        STATUSES.put(HttpURLConnection.HTTP_MULT_CHOICE, "Multiple Choices");
        STATUSES.put(HttpURLConnection.HTTP_MOVED_PERM, "Moved Permanently");
        STATUSES.put(HttpURLConnection.HTTP_MOVED_TEMP, "Moved Temporary");
        STATUSES.put(HttpURLConnection.HTTP_SEE_OTHER, "See Other");
        STATUSES.put(HttpURLConnection.HTTP_NOT_MODIFIED, "Not Modified");
        STATUSES.put(HttpURLConnection.HTTP_USE_PROXY, "Use Proxy");
        STATUSES.put(307, "Temporary Redirect");
        STATUSES.put(308, "Permanent Redirect");

        STATUSES.put(HttpURLConnection.HTTP_BAD_REQUEST, "Bad Request");
        STATUSES.put(HttpURLConnection.HTTP_UNAUTHORIZED, "Unauthorized");
        STATUSES.put(HttpURLConnection.HTTP_PAYMENT_REQUIRED, "Payment Required");
        STATUSES.put(HttpURLConnection.HTTP_FORBIDDEN, "Forbidden");
        STATUSES.put(HttpURLConnection.HTTP_NOT_FOUND, "Not Found");
        STATUSES.put(HttpURLConnection.HTTP_BAD_METHOD, "Method Not Allowed");
        STATUSES.put(HttpURLConnection.HTTP_NOT_ACCEPTABLE, "Not Acceptable");
        STATUSES.put(HttpURLConnection.HTTP_PROXY_AUTH, "Proxy Authentication Required");
        STATUSES.put(HttpURLConnection.HTTP_CLIENT_TIMEOUT, "Request Timeout");
        STATUSES.put(HttpURLConnection.HTTP_CONFLICT, "Conflict");
        STATUSES.put(HttpURLConnection.HTTP_GONE, "Gone");
        STATUSES.put(HttpURLConnection.HTTP_LENGTH_REQUIRED, "Length Required");
        STATUSES.put(HttpURLConnection.HTTP_PRECON_FAILED, "Precondition Failed");
        STATUSES.put(HttpURLConnection.HTTP_ENTITY_TOO_LARGE, "Request Entity Too Large");
        STATUSES.put(HttpURLConnection.HTTP_REQ_TOO_LONG, "URI Too Long");
        STATUSES.put(HttpURLConnection.HTTP_UNSUPPORTED_TYPE, "Unsupported Media Type");
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

        STATUSES.put(HttpURLConnection.HTTP_INTERNAL_ERROR, "Internal Server Error");
        STATUSES.put(HttpURLConnection.HTTP_NOT_IMPLEMENTED, "Not Implemented");
        STATUSES.put(HttpURLConnection.HTTP_BAD_GATEWAY, "Bad Gateway");
        STATUSES.put(HttpURLConnection.HTTP_UNAVAILABLE, "Service Unavailable");
        STATUSES.put(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, "Gateway Timeout");
        STATUSES.put(HttpURLConnection.HTTP_VERSION, "HTTP Version Not Supported");
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
