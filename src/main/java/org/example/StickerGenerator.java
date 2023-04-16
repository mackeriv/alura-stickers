package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerGenerator {

    public void create(InputStream inputStream, String fileName, String text) throws Exception {

        //reads image
        BufferedImage originalImage = ImageIO.read(inputStream);

        //creates a new transparent canvas with increased height
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //copy the original image to the transparent canvas (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //setting-up font
        var font = new Font("Impact", Font.PLAIN, 60);
        graphics.setColor(Color.MAGENTA);
        graphics.setFont(font);

        //places text in the new image
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);
        int textWidth = (int) rectangle.getWidth();
        int textPositionX = (width - textWidth)/2;
        int textPositionY = newHeight-55;
        graphics.drawString(text, textPositionX, textPositionY);

        //setting-up an outline (stroke)
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(textPositionX, textPositionY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(width * 0.008f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        //save the new image in a file
        ImageIO.write(newImage, "png", new File(fileName));

    }
}
