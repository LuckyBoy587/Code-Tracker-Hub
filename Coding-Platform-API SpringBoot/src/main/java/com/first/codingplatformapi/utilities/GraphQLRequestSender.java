package com.first.codingplatformapi.utilities;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphQLRequestSender {
    private static final String BASE_URL = "https://leetcode.com/graphql";

    public static Map<String, Object> sendGraphQLRequest(String fileName, Map<String, Object> variables) throws Exception {
        ClassPathResource resource = new ClassPathResource(fileName);
        String query = new String(Files.readAllBytes(Paths.get(resource.getURI())));

        // Normalize the query
        String normalizedQuery = query
                .replaceAll("\\s+", " ")
                .replace("\"", "\\\"")
                .trim();

        // Convert variables map to JSON-style string manually
        String variablesJson = mapToJsonString(variables);

        // Build GraphQL request body
        String requestBody = String.format("{\"query\": \"%s\", \"variables\": %s}", normalizedQuery, variablesJson);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Referer", "https://leetcode.com");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36");

        // Send request
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                BASE_URL,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    public static Map<String, Object> sendGraphQLRequest(String fileName) throws Exception {
        return sendGraphQLRequest(fileName, null);
    }

    // Utility to convert a map to a JSON-style string (basic)
    private static String mapToJsonString(Map<String, Object> map) {
        if (map == null) return "{}";
        return "{" + map.entrySet().stream()
                .map(entry -> String.format("\"%s\": \"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", ")) + "}";
    }
}
