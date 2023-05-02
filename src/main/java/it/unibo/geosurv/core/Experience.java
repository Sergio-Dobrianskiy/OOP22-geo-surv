package it.unibo.geosurv.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Experience extends GameObject {

    public Experience(float x, float y, ID id) {
        super(x, y, id);

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
