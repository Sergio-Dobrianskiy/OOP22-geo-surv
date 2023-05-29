package it.unibo.geosurv.model.block;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;

public abstract class Block extends GameObject {

    /**
     * Constructor for this class.
     *
     * @param x block coordinate
     * @param y block coordinate
     */
    public Block(float x, float y) {
        super(x, y, ID.Block);
    }

}
