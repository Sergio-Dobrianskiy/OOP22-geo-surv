package it.unibo.geosurv.view.graphics;

import java.awt.image.BufferedImage;

public class Sprite {
	private BufferedImage image;
		
	public Sprite(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
	}
}
