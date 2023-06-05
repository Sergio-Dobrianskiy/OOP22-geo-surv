package it.unibo.geosurv.model.monsters.types;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Class managing Rectangular typoe of monsters.
 */
public class Rect extends Monster {

    // private Handler handler;
    // private Game game;
    private static final float DEFAULT_SPEED = 2f; // default speed of Rect
    private static final float MAX_SPEED = 4f; // max speed of BIG Rect
    private static final int DEFAULT_HEALTH = 7; // default health of Rect
    private static final int MAX_HEALTH = 15; // default health of BIG Rect
    private static final int DEFAULT_H_DIMENSION = 15; // default height of Rect
    private static final int MAX_H_DIMENSION = 35; // max height of (big) Rect
    private static final int DEFAULT_W_DIMENSION = 15; // default width of Rect
    private static final int MAX_W_DIMENSION = 35; // max width of (big) Rect
    private static final int POWER = 8; // power of (big) Rect
    private static int counter;
    private String name = "Rect-";
    // private static final boolean isBig = false;

    /**
     * Rect constructor.
     * 
     * @param h handler
     */
    public Rect(final Handler h) {

        super(0, 0, h);

        Rect.counter++;
        this.name = name + Rect.counter;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        this.power = 2;
        this.height = DEFAULT_H_DIMENSION;
        this.width = DEFAULT_W_DIMENSION;
        texture = Texture.SQUARE;
    }

    @Override
    public final void tick() {
        reachTarget();
    }

    @Override
    public final void setBig(final boolean isBig) {
        this.isBig = isBig;
        this.health = MAX_HEALTH;
        this.speed = MAX_SPEED;
        this.power = POWER;
        this.height = MAX_H_DIMENSION;
        this.width = MAX_W_DIMENSION;
        this.texture = Texture.SQUARE_BIG;
    }

}
