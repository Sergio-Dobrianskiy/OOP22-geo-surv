package it.unibo.geosurv.model.monsters.triangle;

import java.awt.Color;
import java.awt.Graphics;

import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Class for enemies which do not move.
 */
public class Rhombus extends Monster {

    private int DEFAULT_HEALTH = 2;
    private int DEFAULT_DIMENSION = 32;
    private static int counter = 0;
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

}
