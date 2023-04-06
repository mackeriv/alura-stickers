package org.example;

public enum Infos {
    NASA("", "https://api.nasa.gov/planetary/apod?api_key=ZAnyccCrxbiJSNY1UYC4FqRfhnXsHONBGQu7lYyM&start_date=2022-06-12&end_date=2022-06-14"),
    IMDB("", "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json"),
    local("", "http://localhost:8080/linguagens"),
    Fly("", "https://alr-linguagens.fly.dev/linguagens");

    final String extrator;
    final String URL;

    Infos(String extrator, String URL) {
        this.extrator = extrator;
        this.URL = URL;
    }





}
