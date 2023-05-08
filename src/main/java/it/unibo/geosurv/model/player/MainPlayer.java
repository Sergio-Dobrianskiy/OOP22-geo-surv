package it.unibo.geosurv.model.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

import java.awt.Color;

public class MainPlayer extends GameObject {

	public final static int PLAYER_HEIGHT = 48;
	public final static int PLAYER_WIDTH = 32;
	public final static int HALF_PLAYER_HEIGHT = PLAYER_HEIGHT / 2;
	public final static int HALF_PLAYER_WIDTH = PLAYER_WIDTH / 2;
	public final static int PLAYER_SPEED = 5;
	
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
            velY = -PLAYER_SPEED;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if (handler.isDown()) {
            velY = PLAYER_SPEED;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if (handler.isRight()) {
            velX = PLAYER_SPEED;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if (handler.isLeft()) {
            velX = -PLAYER_SPEED;
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
        g.fillRect((int) x, (int) y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public Rectangle getShape() {
        return new Rectangle((int) x, (int) y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }
}
