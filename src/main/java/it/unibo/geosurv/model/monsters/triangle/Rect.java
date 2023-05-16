package it.unibo.geosurv.model.monsters.triangle;

import java.awt.Color;
import java.awt.Graphics;

import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.view.graphics.Texture;

public class Rect extends Monster {

    // private Handler handler;
    // private Game game;
    private static final float DEFAULT_SPEED = 2f; // default speed of Rect
    private static final float MAX_SPEED = 4f; // max speed of BIG Rect
    private static final int DEFAULT_HEALTH = 4; // default health of Rect
    private static final int MAX_HEALTH = 15; // default health of BIG Rect
    private static final int DEFAULT_DIMENSION = 15; // default size of Rect
    private static final int MAX_DIMENSION = 35; // max size of (big) Rect
    private static int counter;
    private String name = "Rect-";
    // private static final boolean isBig = false;

    /**
     * Rect constructor
     */
    public Rect(final float x, final float y, final boolean isBig) {

        super(x, y);
        Rect.counter++;
        this.name = name + Rect.counter;
        if (!isBig) {
            this.health = DEFAULT_HEALTH;
            this.dimension = DEFAULT_DIMENSION;
            this.speed = DEFAULT_SPEED;
            this.power = 2;
            this.height = DEFAULT_DIMENSION;
            this.width = DEFAULT_DIMENSION;
            texture = Texture.SQUARE;
        } else {
            this.health = MAX_HEALTH;
            this.dimension = MAX_DIMENSION;
            this.speed = MAX_SPEED;
            this.power = 8;
            this.height = MAX_DIMENSION;
            this.width = MAX_DIMENSION;
            this.texture = Texture.SQUARE_BIG;
        }
    }

    @Override
    public void tick() {
        reachTarget();
    }
//
//    @Override
//    public void render(Graphics g) {
//        this.drawRect(g,  Color.green);
//    }

    // public boolean isBig() {
    // return isBig;
    // }

}
