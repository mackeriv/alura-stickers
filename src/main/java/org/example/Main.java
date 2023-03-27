package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        //fazer uma conexão HTTP e pegar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        var client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);

        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String, String> filme: listaDeFilmes) {
            double nota = Double.parseDouble(filme.get("imDbRating"));

            System.out.println("Título: " + "\u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("Pôster: " + "\u001b[3m" + filme.get("image") + "\u001b[m");
            System.out.println("Nota: " + "\u001b[1m" + "\u001b[38;2;255;255;255m" + "\u001b[42m" + " " + filme.get("imDbRating") + " " + "\u001b[m");

            for (int j = 0; j <= nota; j++) {
                System.out.print("\u2B50");
            }
            System.out.println("\n");

        }

    }
}