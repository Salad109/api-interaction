package zlosnik.jp.lab04.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;

public class ApiServiceClient implements ApiService {

    @Override
    public ApiResponse makeHttpRequest(String url) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to fetch data. HTTP status code: " + response.statusCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), ApiResponse.class);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println("Error occurred while making the HTTP request: " + e.getMessage());
            return null;
        }
    }
}