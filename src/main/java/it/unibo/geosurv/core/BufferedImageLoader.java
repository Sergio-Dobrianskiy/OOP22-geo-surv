package it.unibo.geosurv.core;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return image;
    }
    
}