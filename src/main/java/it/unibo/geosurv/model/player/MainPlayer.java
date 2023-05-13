package it.unibo.geosurv.model.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.geosurv.model.Collisions;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.MonstersObserver;
import it.unibo.geosurv.model.monsters.Monster;

import java.awt.Color;

public class MainPlayer extends GameObject {

    public final static int PLAYER_HEIGHT = 48;
    public final static int PLAYER_WIDTH = 32;
    public final static int HALF_PLAYER_HEIGHT = PLAYER_HEIGHT / 2;
    public final static int HALF_PLAYER_WIDTH = PLAYER_WIDTH / 2;
    public final static int PLAYER_SPEED = 5;
    public final static int MAX_LIFE = 100;
    private long lastHitTime; // last time Player is touched/hit by a monster
    private static final int MAX_HITS_PER_SECOND = 2;
    private static final long HIT_COOLDOWN = 1000 / MAX_HITS_PER_SECOND;

    Handler handler;
    private int experience;
    private int life;
    private List<MonstersObserver> observers;

    public MainPlayer(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.life = MAX_LIFE;
        this.lastHitTime = 0;
        this.observers = new ArrayList<>();
        this.height = PLAYER_HEIGHT;
        this.width = PLAYER_WIDTH;
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

        notifyObservers(); // notify player position
    }

    private void collision() {
    		ArrayList<GameObject> tmpObjects = handler.getObjects();
        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);
            if (Collisions.isColliding(this, tempObject, ID.Block)) { // if player touches wall => stop
                x += velX * -1;
                y += velY * -1;
            }
            if (Collisions.isColliding(this, tempObject, ID.Monster)) { // if player touches Monsters
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastHitTime >= HIT_COOLDOWN) {
                    // System.out.println("Player hit by: " + tempObject.toString());
                    // DONE: should be hit only once or twice a second..
                    this.life -= ((Monster) tempObject).getPower(); // TODO: verify the cast => maybe if we call a
                                                                    // function here that works at Monster we do not
                                                                    // need the cast
                                                                    // (Monster.over(handler.player)->decrease life)
                    lastHitTime = currentTime;
                }
            }
        }
    }

    public void render(Graphics g) {
    		this.drawRect(g,  Color.blue);
        g.setColor(Color.blue);
        g.drawString("Life: " + this.getLife() + " Exp: " + this.getExperience(), (int) x, (int) y - 5);
    }

    public Rectangle getShape() {
        return this.setRectangleShape();
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }

    /**
     * @return how much life the Player has left
     */
    public int getLife() {
        return life;
    }

    public void addObserver(MonstersObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MonstersObserver observer) {
        observers.remove(observer);
        System.out.println("Removed Observer: " + observer.toString());
    }

    private void notifyObservers() {
        for (MonstersObserver observer : observers) {
            observer.update(this);
        }

    }

}
