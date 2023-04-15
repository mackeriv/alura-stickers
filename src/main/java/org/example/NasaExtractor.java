package org.example;

import java.util.List;
import java.util.Map;

public class NasaExtractor implements ContentExtractor {

    public List<Content> extractContent(String json) {

        //extracts relevant data (title, photo)
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        //creates and populates a list
        return attributeList.stream()
            .map(attributes -> new Content(attributes.get("title"), attributes.get("url")))
            .toList();

    }
}
