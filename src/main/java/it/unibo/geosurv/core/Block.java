package it.unibo.geosurv.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject {

    public Block(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    public void tick() {
    	this.updateDistanceFromPlayer();
    }

    public void render(Graphics g) {
    	if (distanceFromPlayer <= 400) {
    		g.setColor(Color.black);
    		g.fillRect((int)x, (int)y, 32, 32);
    	}
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
}
