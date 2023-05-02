package it.unibo.geosurv.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Enum that contains all maps.
 */
public enum Texture {
	/**
	 * Represents the big map.
	 */
	BIG_MAP("/maps/map_big.png"),
	/**
	 * Represents the small map.
	 */
	SMALL_MAP("/maps/map_small.png"),
	/**
	 * Represents the test map.
	 */
	TEST_MAP("/maps/map_test.png");

	//////////////////////////////////
	private final String filePath;
	private BufferedImage texture;

	/**
     * Constructor for this Class.
     *
     * @param fileName path to the texture
     */
	Texture(String filePath) {
		this.filePath = filePath;
	}
	
	public String getPath() {
		return this.filePath;
	}
	
    /**
     * Load the specific texture.
     *
     * @throws IOException              error while reading image
     * @throws IllegalArgumentException if the file is null
     * @throws NullPointerException     if the given file is null
     */
	public void load () throws IOException {
		final InputStream inputStream = getClass().getResourceAsStream(this.filePath);
		if (inputStream == null) {
			throw new NullPointerException("" + this.filePath + " is a wrong path");
		}
		this.texture = ImageIO.read(inputStream);
		inputStream.close();
	}
	
    /**
     * Get texture as a BufferImage.
     *
     * @return texture
     */
	public BufferedImage getTexture() {
		return this.texture;
	}
}
