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
    private Handler handler;
    private int experience;
    private int health;
    private Collisions collisions;

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
    }

    public void tick() {
        x += velX;
        y += velY;
        collisions.checkPlayerCollisions();
        movePlayer();
        notifyObservers(); // notify player position
    }

    public void render(Graphics g) {
        this.drawRect(g, Color.blue);
        g.setColor(Color.blue);
        g.drawString("Life: " + this.getLife() + " Exp: " + this.getExperience(), (int) x, (int) y - 5);
        
        //Draw bar progres for player's life
        int barWidth = 100;
        int barHeight = 10;
        int barX = (int) (x - barWidth / 2);
        int barY = (int) y - barHeight -30;
        
        g.setColor(Color.BLACK);
        g.fillRect(barX, barY, barWidth, barHeight);
        
        float percentage = (float) health / MAX_LIFE;
        int filledWidth = (int) (barWidth * percentage);
        
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, filledWidth, barHeight);
        
        g.setColor(Color.WHITE);
        g.drawRect(barX, barY, barWidth, barHeight);
        
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

    public void stopMovements() {
        x += velX * -1;
        y += velY * -1;
    }

    public void movePlayer() {
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
}
