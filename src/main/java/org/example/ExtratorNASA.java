package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorNASA implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        //criar lista vazia para popular
        return listaDeAtributos.stream()
            .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
            .toList();

    }
}
