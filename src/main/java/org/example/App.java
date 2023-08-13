package org.example;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        var generator = new StickerGenerator();
        var directory = new File("output/");
        directory.mkdir();

        //creates an HTTP connection to retrieve data
        API api = API.IMDB;
        String url = api.getURL();
        ContentExtractor extractor = api.getExtractor();

        var http = new HttpClient();
        String json = http.retrieveData(url);

        List<Content> contents = extractor.extractContent(json);

        //uses the JSON contents to generate PNG files in the "output" directory, with custom text
        for (int i = 0; i < 5; i++) {
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.imageUrl()).openStream();

            String title = content.title();
            String fileName = "output/" + title + ".png";

            generator.create(inputStream, fileName, "AWESOME");

            System.out.println("Title: " + "\u001b[1m" + title + "\u001b[m");
            System.out.println("\n");

        }
    }
}