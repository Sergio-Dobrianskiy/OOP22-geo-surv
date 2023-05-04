package it.unibo.geosurv.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.Handler;
import it.unibo.geosurv.core.ID;

public class Triangle extends Monster {

    private Handler handler;

    /**
     * Triangel constructor
     */
    public Triangle(float x, float y, ID id) {
        super(x, y, ID.Monster);
    };

    @Override
    public void tick() {
        x += velX;
        y += velY;
        LinkedList<GameObject> tmpObjects = handler.getObjects();

        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);
            // TODO: better use ID.weapon -> only one
            if (tempObject.getId() == ID.Satellite) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    System.out.println(this.id + " is removed");
                    handler.removeObject(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void reachTarget() {
        System.out.println("T trying to reach the target");
    }

}
