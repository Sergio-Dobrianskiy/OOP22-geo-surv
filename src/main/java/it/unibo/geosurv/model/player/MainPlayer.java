package it.unibo.geosurv.model.player;

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

public class MainPlayer extends GameObject implements MainPlayerInterf {

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
    private Levels levels;
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
//        this.texture = Texture.PLAYER_DUCK;  // alternative texture
        this.texture = Texture.PLAYER_MOUSE;
        this.levels = new Levels();
    }

    public void tick() {
        x += velX;
        y += velY;
        collisions.checkPlayerCollisions();
        this.playerMovement.movePlayer();
        notifyObservers(); // notify player position
    }     

    public Rectangle getShape() {
        return this.setRectangleShape();
    }

    public int getExperience() {
        return this.levels.getCurrentExp();
    }
    
    public int getMaxExperience() {
    	return this.levels.getExpToLevel();
    }
    
    public int getLevel() {
    	return this.levels.getCurrentLevel();
    }
    
    public float getExpPercentage() {
    	if (this.getExperience() == 0) {
    		return 0;
    	}
    	return ((float) this.getExperience() / this.getMaxExperience()) * 100;
    }

    public void setExperience(final int experience) {
    	this.levels.expUp(experience);

    }

    /**
     * @return how much health the Player has left
     */
    public int getLife() {
        return this.health;
    }

    public void setLife(int life) {
        this.health = life;
    }

    public void addObserver(MonstersObserver observer) {
    	this.observers.add(observer);
    }

    public void removeObserver(MonstersObserver observer) {
    	this.observers.remove(observer);
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
