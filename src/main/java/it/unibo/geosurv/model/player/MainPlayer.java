package it.unibo.geosurv.model.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

import java.awt.Color;

public class MainPlayer extends GameObject {

    Handler handler;
    private int experience;

    public MainPlayer(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        collision();

        /* movements */
        if (handler.isUp()) {
            velY = -5;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if (handler.isDown()) {
            velY = 5;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if (handler.isRight()) {
            velX = 5;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if (handler.isLeft()) {
            velX = -5;
        } else if (!handler.isRight()) {
            velX = 0;
        }
    }

    private void collision() {
        LinkedList<GameObject> tmpObjects = handler.getObjects();
        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);
            if (tempObject.getId() == ID.Block) { // if player touches wall => stop
                if (getShape().getBounds2D().intersects(tempObject.getShape().getBounds2D())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, 32, 48);
//        g.fillOval((int) x, (int) y, 32, 32);

    }

    public Rectangle getShape() {
        return new Rectangle((int) x, (int) y, 32, 48);
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }
}