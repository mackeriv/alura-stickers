package org.example;

import java.util.List;
import java.util.Map;

public class ImdbExtractor implements ContentExtractor {

    public List<Content> extractContent(String json) {

        //extracts relevant data (title, image, rating, etc)
        var parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        //creates and populates a list
        return attributesList.stream()
                .map(attributes -> new Content(attributes.get("title"), attributes.get("image")))
                .toList();
    }
}
