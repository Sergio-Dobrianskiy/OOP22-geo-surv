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
import it.unibo.geosurv.model.utility.Pair;

public class Rhombus extends Monster {

    private static int counter = 0;
    private int DEFAULT_HEALTH = 2; // default health of Rect
    private String name = "Rhombus-";
    private int DEFAULT_DIMENSION = 12; // default size of Rect
    private int dimension;
    private boolean isBig = false;

    /**
     * Rhombus constructor
     */
    public Rhombus(float x, float y, Handler handler, Game game, boolean isBig) { // TODO: handler & gamo not used! to
                                                                                  // delete
        super(x, y);
        // this.speed = speed;
        // this.handler = handler;
        // this.game = game;
        Rhombus.counter++;
        this.name = name + Rhombus.counter;
        this.health = DEFAULT_HEALTH;
        this.dimension = DEFAULT_DIMENSION;
        this.power = 2;
    };

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval((int) x, (int) y, this.dimension, this.dimension);
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle((int) x, (int) y, this.dimension, this.dimension);
    }

    public boolean isBig() {
        return isBig;
    }

    @Override
    public void reachTarget() {
    }

}
