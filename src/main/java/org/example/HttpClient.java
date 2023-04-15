package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {

    public String retrieveData(String url) {

        try {
            var client = java.net.http.HttpClient.newHttpClient();
            URI address = URI.create(url);

            var request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException ex) {
            throw new HttpClientException("Error: Can't retrieve URL");

        }

    }
}
