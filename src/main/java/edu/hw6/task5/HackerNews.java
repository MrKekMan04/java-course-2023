package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HackerNews {
    private static final Pattern STORIES_PATTERN = Pattern.compile("^\\[(\\d+)?(,(\\d+))*]$");
    private static final Pattern STORY_TITLE_PATTERN = Pattern.compile("^\\{.*\"title\":\"(?<title>[^\"]*)\".*}$");
    private static final int STATUS_OK = 200;

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        try {
            try (HttpClient client = HttpClient.newHttpClient()) {
                HttpResponse<String> response =
                    sendGetRequest(client, "https://hacker-news.firebaseio.com/v0/topstories.json");

                if (response.statusCode() == STATUS_OK) {
                    String body = response.body();
                    Matcher matcher = STORIES_PATTERN.matcher(body);

                    if (matcher.matches()) {
                        body = body.substring(1, body.length() - 1);

                        return Arrays.stream(body.split(","))
                            .mapToLong(Long::valueOf)
                            .toArray();
                    }
                }
            }
        } catch (URISyntaxException | InterruptedException | IOException ignored) {
        }

        return new long[] {};
    }

    public static String news(long id) {
        try {
            try (HttpClient client = HttpClient.newHttpClient()) {
                HttpResponse<String> response =
                    sendGetRequest(client, "https://hacker-news.firebaseio.com/v0/item/%d.json".formatted(id));

                if (response.statusCode() == STATUS_OK) {
                    String body = response.body();
                    Matcher matcher = STORY_TITLE_PATTERN.matcher(body);

                    if (matcher.matches()) {
                        return matcher.group("title");
                    }
                }
            }
        } catch (URISyntaxException | InterruptedException | IOException ignored) {
        }

        return null;
    }

    private static HttpResponse<String> sendGetRequest(HttpClient client, String url)
        throws URISyntaxException, IOException, InterruptedException {
        return client.send(HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .build(), HttpResponse.BodyHandlers.ofString());
    }
}
