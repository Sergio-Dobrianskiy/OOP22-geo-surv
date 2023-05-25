package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.ObserverEntity;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.view.graphics.Texture;

public class Experience extends GameObject implements ObserverEntity {

    /** more experience make player go to new levels */
    private static int experienceCounter = 0;
    protected final static int EXPERIENCE_HEIGHT = 25;
    protected final static int EXPERIENCE_WIDTH = 20;
    private int experience;
    private float mx; // Player Position throu observer
    private float my; // Player Position throu observer
    private final MainPlayer player;
    private float pickUpSpeed = 10;

    public Experience(float x, float y, int exp, Handler handler) {
        super(x, y, ID.Experience);
        this.experience = exp;
        Experience.experienceCounter++; // TODO: decrease the counter as Player gather experiences pills
        this.height = EXPERIENCE_HEIGHT;
        this.width = EXPERIENCE_WIDTH;
        this.texture = Texture.EXPERIENCE;
        this.player = handler.getPlayer();
        this.player.addObserver(this);
    }

    @Override
    public void tick() {
        if (player.getLevel() > 1) {
            reachTarget();
        }
    }

    /**
     * @return int amount of experience in the pill
     */
    public int getExperience() {
        return experience;
    }

    public static int getExperienceCounter() {
        return experienceCounter;
    }

    @Override
    public void update(MainPlayer mainPlayer) {
        mx = mainPlayer.getX();
        my = mainPlayer.getY();
    }

    /**
     * if player is near and has gained some experience pills are attracted
     */
    public void reachTarget() {
        // distance from the player and experience pill
        float distance = calculateDistance(player.getX(), player.getY(), this.getX(), this.getY());
        // maxDistance: to determine the max distance a pill can reach the player and
        // increment +10% distance each level
        float maxDistance = 80 + (10 * player.getLevel());

        if (distance <= maxDistance) {
            x += velX;
            y += velY;

            float angle = (float) Math.atan2(my - this.getY() + 8, mx - this.getX() + 4);

            this.velX = (float) ((this.pickUpSpeed) * Math.cos(angle));
            this.velY = (float) ((this.pickUpSpeed) * Math.sin(angle));
        }
    }

    /**
     * calculate distance beetwen two point (x1,y1) and (x2,y2) in a 2d space
     * 
     * @param x1,x2,y1,y2
     * 
     * @return float distance
     */
    private float calculateDistance(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
