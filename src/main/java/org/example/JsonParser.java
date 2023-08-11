package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_JSON_ATTRIBUTES = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Couldn't find any items");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> itemAttributes = new HashMap<>();

            Matcher matcherAttributesJson = REGEX_JSON_ATTRIBUTES.matcher(item);
            while (matcherAttributesJson.find()) {
                String attributes = matcherAttributesJson.group(1);
                String value = matcherAttributesJson.group(2);
                itemAttributes.put(attributes, value);
            }


            data.add(itemAttributes);
        }

        return data;

    }
}