package it.unibo.geosurv.model.block;

import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a Block.
 */
public class Wall extends Block {

    /**
     * block's height.
     */
    private final int blockHeight = 32;
    /**
     * block's width.
     */
    private final int blockWidth = 32;

    /**
     * Constructor for this class.
     *
     * @param x block coordinate
     * @param y block coordinate
     */
    public Wall(final float x, final float y) {
        super(x, y);
        this.height = blockHeight;
        this.width = (int) blockWidth;
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
