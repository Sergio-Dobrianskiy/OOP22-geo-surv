package it.unibo.geosurv.model.monsters.triangle;

import java.awt.Color;
import java.awt.Graphics;

import it.unibo.geosurv.model.monsters.Monster;

/**
 * Class for enemies which do not move.
 */
public class Rhombus extends Monster {

    private static final int DEFAULT_HEALTH = 2;
    private static final int DEFAULT_DIMENSION = 24;
    private static int counter;
    private String name = "Rhombus-";

    /**
     * Rhombus constructor.
     * 
     * @param x position
     * @param y position
     */
    public Rhombus(final float x, final float y) {
        super(x, y);
        Rhombus.counter++;
        this.name = name + Rhombus.counter;
        this.health = DEFAULT_HEALTH;
        this.dimension = DEFAULT_DIMENSION;
        this.power = 2;
        this.speed = 0;
        this.height = DEFAULT_DIMENSION;
        this.width = DEFAULT_DIMENSION;
    }

    /**
     * do nothing on tick().
     */
    @Override
    public void tick() {
        /* do nothing on tick() */
    }

    /**
     * render method.
     * 
     * @param g
     */
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.orange);
        g.fillOval((int) x, (int) y, this.dimension, this.dimension);
    }

}
