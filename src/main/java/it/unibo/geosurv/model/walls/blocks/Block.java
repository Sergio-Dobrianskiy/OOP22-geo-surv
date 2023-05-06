package it.unibo.geosurv.model.walls.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;

public class Block extends GameObject {

    public Block(float x, float y) {
        super(x, y, ID.Block);
    }

    public void tick() {
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    public Rectangle getShape() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
}
