package it.unibo.geosurv.model.monsters.types;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.MonsterImpl;
import it.unibo.geosurv.view.graphics.Texture;

public class Ball extends MonsterImpl {
    private static final float DEFAULT_SPEED = 2f; // default speed of triangle
    private static final float MAX_SPEED = 3f; // max speed of BIG triangle
    private static final int DEFAULT_H_DIMENSION = 40; // default height of triangle
    private static final int MAX_H_DIMENSION = 40; // max height of (big) triangle
    private static final int DEFAULT_W_DIMENSION = 40; // default width of triangle
    private static final int MAX_W_DIMENSION = 40; // max width of (big) triangle
    private static final int DEFAULT_HEALTH = 5; // default health of triangle
    private static final int MAX_HEALTH = 8; // default health of BIG triangle
    private static final int DEFAULT_POWER = 5; // default power of triangle
    private static final int MAX_POWER = 7; // default power of BIG triangle

    /**
     * Ball constructor.
     * 
     * @param h handler
     */
    public Ball(final Handler h) {
        super(0, 0, h);
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        this.power = DEFAULT_POWER;
        this.height = DEFAULT_H_DIMENSION;
        this.width = DEFAULT_W_DIMENSION;
        this.texture = Texture.BALL;

    }

    /**
     * On each tick, triangle monsters try to reach the player.
     */
    @Override
    public void tick() {
        reachTarget();
    }

    /**
     * @return whether a triangle is big or not.
     */
    public boolean isBig() {
        return isBig;
    }

    @Override
    public final void setBig(final boolean isBig) {
        this.isBig = isBig;
        this.health = MAX_HEALTH;
        this.speed = MAX_SPEED;
        this.power = MAX_POWER;
        this.height = MAX_H_DIMENSION;
        this.width = MAX_W_DIMENSION;
        this.texture = Texture.BALL_BIG;
    }

}
