package it.unibo.geosurv.model.monsters.triangle;

import java.awt.Color;
import java.awt.Graphics;

import it.unibo.geosurv.model.monsters.Monster;

/**
 * Class for triangle type of Monsters.
 */
public class Triangle extends Monster {

    // private Handler handler;
    // private Game game;
    private static final float DEFAULT_SPEED = 1.2f; // default speed of triangle
    private static final float MAX_SPEED = 3f; // max speed of BIG triangle
    private static final int DEFAULT_DIMENSION = 20; // default size of triangle
    private static final int MAX_DIMENSION = 50; // max size of (big) triangle
    private static final int DEFAULT_HEALTH = 2; // default health of triangle
    private static final int MAX_HEALTH = 10; // default health of BIG triangle
    private static final int POWER = 5; // default speed of triangle
    private static int counter;
    private final boolean isBig;
    private String name = "Triangle-";

    /**
     * Triangle constructor.
     * 
     * @param x     x position
     * @param y     y position
     * @param isBig
     */
    public Triangle(final float x, final float y, final boolean isBig) {
        super(x, y);
        Triangle.counter++;
        this.name = name + Triangle.counter;
        this.isBig = isBig;
        if (!isBig) {
            this.health = DEFAULT_HEALTH;
            this.dimension = DEFAULT_DIMENSION;
            this.speed = DEFAULT_SPEED;
            this.power = 1;
            this.height = DEFAULT_DIMENSION;
            this.width = DEFAULT_DIMENSION;
        } else {
            this.health = MAX_HEALTH;
            this.dimension = MAX_DIMENSION;
            this.speed = MAX_SPEED;
            this.power = POWER;
            this.power = 5;
            this.height = MAX_DIMENSION;
            this.width = MAX_DIMENSION;
        }

    }

    /**
     * On each tick, triangle monsters try to reach the player.
     */
    @Override
    public void tick() {
        reachTarget();
    }

    /**
     * @param g
     */
    @Override
    public void render(final Graphics g) {
        this.drawRect(g, Color.DARK_GRAY);
        g.setColor(Color.white);
        g.drawString(String.valueOf(this.health), (int) x - 4, (int) y);
    }

    /**
     * @return whether a triangle is big or not.
     */
    public boolean isBig() {
        return isBig;
    }

}
