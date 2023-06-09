package it.unibo.geosurv.model.monsters.types;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Class for triangle type of Monsters.
 */
public class Triangle extends Monster {

    // private Handler handler;
    // private Game game;
    private static final float DEFAULT_SPEED = 1.2f; // default speed of triangle
    private static final float MAX_SPEED = 3f; // max speed of BIG triangle
    private static final int DEFAULT_H_DIMENSION = 20; // default height of triangle
    private static final int MAX_H_DIMENSION = 50; // max height of (big) triangle
    private static final int DEFAULT_W_DIMENSION = 20; // default width of triangle
    private static final int MAX_W_DIMENSION = 50; // max width of (big) triangle
    private static final int DEFAULT_HEALTH = 5; // default health of triangle
    private static final int MAX_HEALTH = 20; // default health of BIG triangle
    private static final int DEFAULT_POWER = 5; // default power of triangle
    private static final int MAX_POWER = 5; // default power of BIG triangle
    private static int counter;

    private String name = "Triangle-";

    /**
     * Triangle constructor.
     * 
     * @param h handler
     */
    public Triangle(final Handler h) {
        super(0, 0, h);
        Triangle.counter++;
        this.name = name + Triangle.counter;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        this.power = DEFAULT_POWER;
        this.height = DEFAULT_H_DIMENSION;
        this.width = DEFAULT_W_DIMENSION;
        this.texture = Texture.TRIANGLE;

    }

    /**
     * On each tick, triangle monsters try to reach the player.
     */
    @Override
    public void tick() {
        reachTarget();

    }


    @Override
    public final void setBig() {
        this.health = MAX_HEALTH;
        this.speed = MAX_SPEED;
        this.power = MAX_POWER;
        this.height = MAX_H_DIMENSION;
        this.width = MAX_W_DIMENSION;
        this.texture = Texture.TRIANGLE_BIG;
    }

}
