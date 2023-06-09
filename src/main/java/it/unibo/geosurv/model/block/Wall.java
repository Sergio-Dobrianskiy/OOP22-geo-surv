package it.unibo.geosurv.model.block;

import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a Block.
 */
public class Wall extends Block {

    /**
     * block's height.
     */
    private static final int BLOCK_HEIGHT = 32;
    /**
     * block's width.
     */
    private static final int BLOCK_WIDTH = 32;

    /**
     * Constructor for this class.
     *
     * @param x block coordinate
     * @param y block coordinate
     */
    public Wall(final float x, final float y) {
        super(x, y);
        this.height = BLOCK_HEIGHT;
        this.width = BLOCK_WIDTH;
        this.texture = Texture.BLOCK;
    }

    /**
     * a wall doesn't tick.
     */
    @Override
    public void tick() {
    }

    @Override
    public void collide() {
    }
}
