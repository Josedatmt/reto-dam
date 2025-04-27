package com.frontend.config;

import java.net.http.*;
import java.net.URI;
import java.time.Duration;


public class ApiClient {
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    public static String send(String method, String endpoint, String jsonBody, String auth) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333" + endpoint))
                .timeout(Duration.ofSeconds(30));

        if (auth != null) builder.header("Authorization", auth);
        if (jsonBody != null) builder.header("Content-Type", "application/json");

        switch (method.toUpperCase()) {
            case "GET" -> builder.GET();
            case "POST" -> builder.POST(HttpRequest.BodyPublishers.ofString(jsonBody));
            case "PUT" -> builder.PUT(HttpRequest.BodyPublishers.ofString(jsonBody));
            case "DELETE" -> builder.DELETE();
        }

        HttpResponse<String> response = client.send(
                builder.build(),
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() >= 400) {
            throw new RuntimeException("Error " + response.statusCode() + ": " + response.body());
        }
        return response.body();
    }
}