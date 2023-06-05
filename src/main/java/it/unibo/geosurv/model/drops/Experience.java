package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.IObserverEntity;
import it.unibo.geosurv.model.collisions.ICollisionBehavior;
import it.unibo.geosurv.model.collisions.RemoveOnCollisionBehavior;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Class for experience pills, created at monsters death.
 * More experience make player go to new levels.
 */
public class Experience extends GameObject implements IObserverEntity<MainPlayer> {

    private static int experienceCounter = 0;
    protected static final int EXPERIENCE_HEIGHT = 25;
    protected static final int EXPERIENCE_WIDTH = 20;
    private final MainPlayer player;
    private final Handler handler;
    private int experience;
    private float mx; // Player Position throu observer
    private float my; // Player Position throu observer
    private float pickUpSpeed = 10;
    private ICollisionBehavior collisionBehavior;

    /**
     * The experience pill is created at monster's death, at the posistion where
     * moster dies.
     * 
     * @param x   position
     * @param y   position
     * @param exp experience quantity
     * @param h   handler
     */
    public Experience(final float x, final float y, final int exp, final Handler h) {
        super(x, y, ID.Experience);
        this.experience = exp;
        Experience.experienceCounter++;
        this.height = EXPERIENCE_HEIGHT;
        this.width = EXPERIENCE_WIDTH;
        this.texture = Texture.EXPERIENCE;
        this.handler = h;
        this.player = this.handler.getPlayer();
        this.player.addObserver(this);
        this.collisionBehavior = new RemoveOnCollisionBehavior();
    }

    @Override
    public final void tick() {
        if (player.getLevel() > 1) {
            reachTarget();
        }
    }

    /**
     * Pill experience.
     * 
     * @return int amount of experience in the pill
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @return how many pills have been created.
     */
    public static int getExperienceCounter() {
        return experienceCounter;
    }

    @Override
    public final void update() {
        mx = this.player.getX();
        my = this.player.getY();
    }

    /**
     * If player is near and has gained some experience(levelsup) pills are
     * attracted.
     */
    public void reachTarget() {
        // distance from the player and experience pill
        float distance = calculateDistance(player.getX(), player.getY(), this.getX(), this.getY());
        // maxDistance: to determine the max distance a pill can reach the player and
        // increment +10% distance each level
        float maxDistance = 80 + (10 * player.getLevel());

        if (distance <= maxDistance) {
            this.setX(this.getX() + this.velX);
            this.setY(this.getY() + this.velY);

            float angle = (float) Math.atan2(my - this.getY() + 8, mx - this.getX() + 4);

            this.velX = (float) ((this.pickUpSpeed) * Math.cos(angle));
            this.velY = (float) ((this.pickUpSpeed) * Math.sin(angle));
        }
    }

    /**
     * calculate distance beetwen two point (x1,y1) and (x2,y2) in a 2d space.
     * 
     * @param x1 first point x
     * @param y1 first point y
     * @param x2 second point x
     * @param y2 second point y
     * 
     * @return float distance
     */
    private float calculateDistance(final float x1, final float y1, final float x2, final float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * starts collision behavior, no behavior by default.
     */
    public void collide() {
        this.collisionBehavior.collide(this, this.handler);
    }

}
