package it.unibo.geosurv.model.monsters;

import java.awt.Rectangle;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.MonstersObserver;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.player.MainPlayer;

/** Abstract Class for generic evil */
public abstract class Monster extends GameObject implements MonstersObserver {

    private int DEFAULT_EXPERIENCE = 1;
    private int BOUNCING_SPEED_MULTIPLYER = 10;

    protected int health; // need to be shared with monters subclasses @Sergio-Dobrianskiy
    protected int power; // power which the plyer is hit by when in contact with a monster
    protected static int monstersCounter;
    protected float mx, my; // Player Position throu observer
    protected MainPlayer p;
    protected int dimension;
    protected double speed;

    protected Monster(float x, float y) {
        super(x, y, ID.Monster);
        monstersCounter++;
        p = Game.returnHandler().getPlayer();
        p.addObserver(this);
        // System.out.println("Added Observer: " + this.toString());
    }

    /**
     * @return an integer showing how much health entity has left
     */
    public int getHealth() {
        return this.health;
    };

    /**
     * @return an integer showing how much power entity has tohit the player
     */
    public int getPower() {
        return power;
    }

    /**
     * Check if Entity is alive
     * 
     * @return boolen value
     */
    public boolean isDead() {
        return this.getHealth() < 0 ? true : false;
    };

    /**
     * At death, Entity drop experience pill
     * 
     * @return Experience pill
     */
    public Experience dropExperience() {
        return new Experience(this.x, this.y, DEFAULT_EXPERIENCE);
    };

    /**
     * Entity's been hit by player weapon
     * 
     * @param weapon whih hits the entity
     */
    public void hit(int weaponDamage) {
        // System.out.println("damage: " + weaponDamage + " health: " + this.health);
        this.health -= weaponDamage;
        if (this.isDead()) {
            p.removeObserver(this);
            this.die();
        }
    };

    /**
     * Entity bounce if hit by weapon
     * 
     * @param weapon whih hits the entity
     */
    public void bounce() {

        boolean isBouncing = false;

        if (!isBouncing) { // start bouncing behavior
            isBouncing = true;

            // reverse the horizontal and vertical velocities to bounce the object off the
            // player
            this.velX = -this.velX * BOUNCING_SPEED_MULTIPLYER;
            this.velY = -this.velY * BOUNCING_SPEED_MULTIPLYER;

            // System.out.println(this.toString() + " inverting direction..");
        }
        isBouncing = false;

    };

    /**
     * Entity dies, drop experience and it is removed
     */
    public void die() {

        Handler h = Game.returnHandler();
        h.addObject(this.dropExperience());
        monstersCounter--;
        h.removeObject(this); // monster is removed from Monsters set/list

    };

    public static int getMonstersCounter() {
        return monstersCounter;
    }

    /** Monster is update about player posistion throu notify - Observer method */
    public void update(MainPlayer mp) {
        mx = mp.getX();
        my = mp.getY();

    }

    @Override
    public Rectangle getShape() {
        return new Rectangle((int) x, (int) y, this.dimension, this.dimension);
    }

    public void reachTarget() {
        x += velX;
        y += velY;
        // evaluated only once at creation istead of each tick()
        // tempPlayer = Func.findPlayer(handler);

        // int mx = (int) this.tempPlayer.getX();
        // int my = (int) this.tempPlayer.getY();
        //
        float angle = (float) Math.atan2(my - this.getY() + 8, mx - this.getX() + 4);

        this.velX = (float) ((this.speed) * Math.cos(angle));
        this.velY = (float) ((this.speed) * Math.sin(angle));
        // System.out.println("T trying to reach the target");
    }

}
