package it.unibo.geosurv.model.walls.blocks;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.view.graphics.Texture;

public class Block extends GameObject {
	
	protected final static int BLOCK_HEIGHT = 32;
	protected final static int BLOCK_WIDTH = 32;

    public Block(float x, float y) {
        super(x, y, ID.Block);
        this.height = BLOCK_HEIGHT;
        this.width = (int) BLOCK_WIDTH;
        this.texture = Texture.BLOCK;
    }

    /**
	 * a wall doesn't tick
	 */
    @Override
    public void tick() {
    }
}
