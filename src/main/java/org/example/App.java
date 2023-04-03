package org.example;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP e pegar os conteudos
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        ExtratorDeConteudo extrator = new ExtratorIMDB();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=ZAnyccCrxbiJSNY1UYC4FqRfhnXsHONBGQu7lYyM&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudo extrator = new ExtratorNASA();

        //String url = "https://alr-linguagens.fly.dev/linguagens";
        //ExtratorDeConteudo extrator = new ExtratorIMDB();

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

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String titulo = conteudo.getTitulo();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo, "TOPZERA");

            System.out.println("Título: " + "\u001b[1m" + titulo + "\u001b[m");

            System.out.println("\n");

        }

    }
}