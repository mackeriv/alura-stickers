package org.example;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP e pegar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

        var client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);

        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String, String> filme: listaDeFilmes) {
            String urlImagem = filme.get("image");
            URI uri = new URI(urlImagem);
            URL novoUrl = uri.toURL();

            InputStream inputStream = novoUrl.openStream();

            String titulo = filme.get("title");
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Título: " + "\u001b[1m" + filme.get("title") + "\u001b[m");

            System.out.println("\n");

        }

    }
}