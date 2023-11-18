package edu.project3.entity.chain;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UrlFinder extends AbstractFinder {
    private static final Pattern URL_PATTERN = Pattern.compile("http(s)?://.*");
    private static final int STATUS_OK = 200;

    public UrlFinder(AbstractFinder next) {
        super(next);
    }

    @Override
    public List<Stream<String>> getLogsLines(String path) {
        Matcher urlMatcher = URL_PATTERN.matcher(path);

        try {
            if (urlMatcher.matches()) {
                try (HttpClient client = HttpClient.newHttpClient()) {
                    HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI(path))
                        .build(), HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == STATUS_OK) {
                        return List.of(Arrays.stream(response.body().split("\n")));
                    }
                }
            }
        } catch (Exception ignored) {
        }

        return super.getLogsLines(path);
    }
}
