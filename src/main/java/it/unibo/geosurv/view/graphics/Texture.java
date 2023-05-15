package it.unibo.geosurv.view.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Enum that contains all maps.
 */
public enum Texture {
	BLOCK("/sprite/block/block.png"),
	/**
	 * Represents the big map.
	 */
	BIG_MAP("/maps/map_big.png"),
	/**
	 * Represents the big map.
	 */
	BIG_MAP_2("/maps/mapGame_copia2.png"),
	/**
	 * Represents the horizontal laser.
	 */
	LASER_H("/sprite/laser/laser_H.png"),
	/**
	 * Represents the vertical laser.
	 */
	LASER_V("/sprite/laser/laser_V.png"),
	/**
	 * Represents the small map.
	 */
	SMALL_MAP("/maps/map_small.png"),
	/**
	 * Represents the GameObject default texture.
	 */
	MISSING_TEXTURE("/sprite/error/missing_texture.png"),
	/**
	 * Represents the square.
	 */
	SQUARE("/sprite/square/square.png"),
	/**
	 * Represents the big square.
	 */
	SQUARE_BIG("/sprite/square/square_big.png"),
	/**
	 * Represents the test map.
	 */
	TEST_MAP("/maps/map_test.png"),
	/**
	 * Represents the triangle.
	 */
	TRIANGLE("/sprite/triangle/triangle.png"),
	/**
	 * Represents the triangle.
	 */
	TRIANGLE_BIG("/sprite/triangle/triangle_big.png");;

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
	public void load() throws IOException {
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
	public BufferedImage extractTexture() {
		return this.texture;
	}
}
