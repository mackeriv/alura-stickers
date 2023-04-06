package org.example;

public enum API {
    NASA(new ExtratorNASA(), "https://api.nasa.gov/planetary/apod?api_key=ZAnyccCrxbiJSNY1UYC4FqRfhnXsHONBGQu7lYyM&start_date=2022-06-12&end_date=2022-06-14"),
    IMDB(new ExtratorIMDB(), "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json"),
    LOCAL(new ExtratorIMDB(), "http://localhost:8080/linguagens"),
    FLY(new ExtratorIMDB(), "https://alr-linguagens.fly.dev/linguagens");

    private final ExtratorDeConteudo extrator;
    private final String URL;

    API(ExtratorDeConteudo extrator, String URL) {
        this.extrator = extrator;
        this.URL = URL;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

    public String getURL() {
        return URL;
    }
}
