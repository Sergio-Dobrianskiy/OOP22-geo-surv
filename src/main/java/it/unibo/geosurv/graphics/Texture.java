package it.unibo.geosurv.graphics;

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
}
