package it.unibo.geosurv.model.walls.blocks;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a Block.
 */
public class Block extends GameObject {

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
    public Block(final float x, final float y) {
        super(x, y, ID.Block);
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
}
