package org.example;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP e pegar os conteudos
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";

        var http = new ClienteHTTP();
        String json = http.buscaDados(url);

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(json);

        //exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String, String> conteudo: listaDeConteudos) {
            String urlImagem = conteudo.get("image");
            URI uri = new URI(urlImagem);
            URL novoUrl = uri.toURL();

            InputStream inputStream = novoUrl.openStream();

            String titulo = conteudo.get("title");
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Título: " + "\u001b[1m" + conteudo.get("title") + "\u001b[m");

            System.out.println("\n");

        }

    }
}