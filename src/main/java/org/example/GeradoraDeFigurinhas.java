package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        //leitura da imagem

        //PARA ARQUIVO
        //InputStream inputStream = new FileInputStream(new File("entrada\shawshank.jpg"));

        //PARA URL
        //URI uri = new URI("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg");
        //URL novoUrl = uri.toURL();
        //InputStream inputStream = novoUrl.openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memória com transparência e novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 20, 20, null);

        //escrever uma frase na nova imagem

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.MAGENTA);
        graphics.setFont(fonte);

        graphics.drawString("TOPZERA", 100, novaAltura-60);

        //escrever a nova imagem em um arquivo

        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }
}
