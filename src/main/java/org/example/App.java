package org.example;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP e pegar os conteudos
        API api = API.IMDB;
        String url = api.getURL();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHTTP();
        String json = http.buscaDados(url);

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(json);

        //exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        var diretorio = new File("saida/");
        diretorio.mkdir();

        for (int i = 0; i <= 5; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();

            String titulo = conteudo.titulo();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo, "TOPZERA");

            System.out.println("Título: " + "\u001b[1m" + titulo + "\u001b[m");

            System.out.println("\n");

        }
    }
}