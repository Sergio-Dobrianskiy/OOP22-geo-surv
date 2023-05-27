package it.unibo.geosurv.view.graphics;

import java.awt.image.BufferedImage;

/**
 * Represents sprites.
 */
public class Sprite {
    private final BufferedImage image;

    /**
     * Constructor for this class.
     *
     * @param image
     */
    public Sprite(final BufferedImage image) {
        this.image = image;
    }

    /**
     * Picks one sprite from a larger image.
     *
     * @param image
     */
    public final BufferedImage grabImage(final int col, final int row, final int width, final int height) {
        return image.getSubimage(col * 32 - 32, row * 32 - 32, width, height);
    }
}
