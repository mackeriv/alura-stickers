package org.example;

public enum API {
    NASA(new NasaExtractor(), "https://api.nasa.gov/planetary/apod?api_key=ZAnyccCrxbiJSNY1UYC4FqRfhnXsHONBGQu7lYyM&start_date=2022-06-12&end_date=2022-06-14"),
    IMDB(new ImdbExtractor(), "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json"),
    LOCAL(new ImdbExtractor(), "http://localhost:8080/languages"),
    FLY(new ImdbExtractor(), "https://alr-linguagens.fly.dev/linguagens");

    private final ContentExtractor extractor;
    private final String URL;

    API(ContentExtractor extractor, String URL) {
        this.extractor = extractor;
        this.URL = URL;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }

    public String getURL() {
        return URL;
    }
}