package it.unibo.geosurv.model.monsters.types;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.MonsterImpl;
import it.unibo.geosurv.view.graphics.Texture;

public class Ball extends MonsterImpl {
    private static final float DEFAULT_SPEED = 2f; // default speed of Ball
    private static final float MAX_SPEED = 3f; // max speed of BIG Ball
    private static final int DEFAULT_H_DIMENSION = 40; // default height of Ball
    private static final int MAX_H_DIMENSION = 40; // max height of (big) Ball
    private static final int DEFAULT_W_DIMENSION = 40; // default width of Ball
    private static final int MAX_W_DIMENSION = 40; // max width of (big) Ball
    private static final int DEFAULT_HEALTH = 10; // default health of Ball
    private static final int MAX_HEALTH = 15; // default health of BIG Ball
    private static final int DEFAULT_POWER = 10; // default power of Ball
    private static final int MAX_POWER = 20; // default power of BIG Ball

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
     * On each tick, Ball monsters try to reach the player.
     */
    @Override
    public void tick() {
        this.reachTarget();
    }

    /**
     * @return whether a Ball is big or not.
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

    @Override
    public void reachTarget() {
        this.setX(this.getX() + this.velX);
        this.setY(this.getY() + this.velY);

        // added a little bit of deviation with Math.random
        float angle = (float) (Math.atan2(my - this.getY() + 8, mx - this.getX() + 4) + Math.random());
        this.velX = (float) ((this.speed) * Math.cos(angle));
        this.velY = (float) ((this.speed) * Math.sin(angle));
    }
}
