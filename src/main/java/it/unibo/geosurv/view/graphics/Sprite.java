package it.unibo.geosurv.view.graphics;

import java.awt.image.BufferedImage;

public class Sprite {
	private final BufferedImage image;

	public Sprite(final BufferedImage image) {
		this.image = image;
	}

	public BufferedImage grabImage(final int col, final int row, final int width, final int height) {
		return image.getSubimage(col * 32 - 32, row * 32 - 32, width, height);
	}
}
