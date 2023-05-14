package it.unibo.geosurv.model.walls.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;

public class Block extends GameObject {
	
	protected final static int BLOCK_HEIGHT = 32;
	protected final static int BLOCK_WIDTH = 32;

    public Block(float x, float y) {
        super(x, y, ID.Block);
        this.height = BLOCK_HEIGHT;
        this.width = (int) BLOCK_WIDTH;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        this.drawRect(g,  Color.black);
    }

    public Rectangle getShape() {
        return this.setRectangleShape();
    }
    
}
