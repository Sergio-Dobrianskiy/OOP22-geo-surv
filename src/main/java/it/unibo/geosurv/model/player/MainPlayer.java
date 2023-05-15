package it.unibo.geosurv.model.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.geosurv.control.PlayerMovement;
import it.unibo.geosurv.control.PlayerMovementImpl;
import it.unibo.geosurv.model.Collisions;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.MonstersObserver;
import it.unibo.geosurv.view.graphics.Texture;

import java.awt.Color;

public class MainPlayer extends GameObject {

    public final static int PLAYER_HEIGHT = 59;
    public final static int PLAYER_WIDTH = 59;
    public final static int HALF_PLAYER_HEIGHT = PLAYER_HEIGHT / 2;
    public final static int HALF_PLAYER_WIDTH = PLAYER_WIDTH / 2;
    public final static int PLAYER_SPEED = 5;
    public final static int MAX_LIFE = 100;
    private long lastHitTime; // last time Player is touched/hit by a monster
    private static final int MAX_HITS_PER_SECOND = 2;
    private static final long HIT_COOLDOWN = 1000 / MAX_HITS_PER_SECOND;
    private Handler handler;
    private int experience;
    private int health;
    private Collisions collisions;
    private PlayerMovement playerMovement;

    private List<MonstersObserver> observers;

    public MainPlayer(float x, float y, Handler handler) {
        super(x, y, ID.Player);
        this.handler = handler;
        this.health = MAX_LIFE;
        this.lastHitTime = 0;
        this.observers = new ArrayList<>();
        this.height = PLAYER_HEIGHT;
        this.width = PLAYER_WIDTH;
        this.collisions = new Collisions(handler);
        this.playerMovement = new PlayerMovementImpl(handler);
//        this.texture = Texture.PLAYER_DUCK;
        this.texture = Texture.PLAYER_MOUSE;
    }

    public void tick() {
        x += velX;
        y += velY;
        collisions.checkPlayerCollisions();
        this.playerMovement.movePlayer();
        notifyObservers(); // notify player position
    }

//    public void render(Graphics g) {
//        this.drawRect(g, Color.blue);
//        g.setColor(Color.blue);
//        g.drawString("Life: " + this.getLife() + " Exp: " + this.getExperience(), (int) x, (int) y - 5);
//    }

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
     * @return how much health the Player has left
     */
    public int getLife() {
        return health;
    }

    public void setLife(int life) {
        this.health = life;
    }

    public void addObserver(MonstersObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MonstersObserver observer) {
        observers.remove(observer);
        // System.out.println("Removed Observer: " + observer.toString());
    }

    private void notifyObservers() {
        for (MonstersObserver observer : observers) {
            observer.update(this);
        }
    }

    public void hit(final int damage) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime >= HIT_COOLDOWN) {
            this.health -= damage;
            lastHitTime = currentTime;
        }
    }

    
}
