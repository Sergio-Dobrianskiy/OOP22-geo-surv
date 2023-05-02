package it.unibo.geosurv.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Experience extends GameObject {

    /** more experience make player go to new levels */
    private int experience;

    public Experience(float x, float y, ID id, int exp) {
        super(x, y, id);
        this.experience = exp;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval((int) x, (int) y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
