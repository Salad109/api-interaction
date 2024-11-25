package zlosnik.jp.lab04;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        String apiUrl = "https://api.nfz.gov.pl/app-itl-api/localities?page=1&limit=10&format=json&name=Wroc%C5%82aw&province=01&api-version=1.3";
        String response = makeHttpRequest(apiUrl);
        if (response != null) {
            parseJsonResponse(response);
        }
    }

    private static String makeHttpRequest(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Failed to fetch data. HTTP status code: " + response.statusCode());
            }

            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println("Error occurred while making the HTTP request: " + e.getMessage());
            return null;
        }
    }

    private static void parseJsonResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse apiResponse = objectMapper.readValue(jsonResponse, ApiResponse.class);

            // Print parsed data
            System.out.println("Meta: " + apiResponse.getMeta());
            System.out.println("Links: " + apiResponse.getLinks());
            System.out.println("Data: " + apiResponse.getData());
        } catch (IOException e) {
            System.err.println("Error occurred while parsing the JSON response: " + e.getMessage());
        }
    }
}