package it.unibo.geosurv.model.monsters.triangle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.utility.Func;

public class Rect extends Monster {

    // private Handler handler;
    // private Game game;
    private float DEFAULT_SPEED = 2f; // default speed of Rect
    private float MAX_SPEED = 4f; // max speed of BIG Rect
    private float speed;
    private static int counter = 0;
    private int DEFAULT_HEALTH = 4; // default health of Rect
    private int MAX_HEALTH = 15; // default health of BIG Rect
    private String name = "Rect-";
    private GameObject tempPlayer = Game.returnHandler().getPlayer();
    private int DEFAULT_DIMENSION = 15; // default size of Rect
    private int MAX_DIMENSION = 35; // max size of (big) Rect
    private int dimension;
    private boolean isBig = false;

    /**
     * Rect constructor
     */
    public Rect(float x, float y, Handler handler, Game game, boolean isBig) { // TODO: handler & gamo not used! to
                                                                               // delete
        super(x, y);
        // this.speed = speed;
        // this.handler = handler;
        // this.game = game;
        Rect.counter++;
        this.name = name + Rect.counter;
        if (!isBig) {
            this.health = DEFAULT_HEALTH;
            this.dimension = DEFAULT_DIMENSION;
            this.speed = DEFAULT_SPEED;
        } else {
            this.health = MAX_HEALTH;
            this.dimension = MAX_DIMENSION;
            this.speed = MAX_SPEED;
        }
    };

    @Override
    public void tick() {
        reachTarget();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, this.dimension, this.dimension);
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle((int) x, (int) y, this.dimension, this.dimension);
    }

    @Override
    public void reachTarget() {
        x += velX;
        y += velY;
        // evaluated only once at creation istead of each tick()
        // tempPlayer = Func.findPlayer(handler);

        int mx = (int) this.tempPlayer.getX();
        int my = (int) this.tempPlayer.getY();

        float angle = (float) Math.atan2(my - this.getY() + 8, mx - this.getX() + 4);

        this.velX = (float) ((this.speed) * Math.cos(angle));
        this.velY = (float) ((this.speed) * Math.sin(angle));
        // System.out.println("T trying to reach the target");
    }

    public boolean isBig() {
        return isBig;
    }

}