package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.ObserverEntity;
import it.unibo.geosurv.model.drops.Drop;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

/**
 * Abstract Class for generic evil.
 * 
 * @param <O>
 */
public abstract class Monster extends GameObject implements IMonster, ObserverEntity<MainPlayer> {

    private static final int DEFAULT_EXPERIENCE = 1;
    private static final int BOUNCING_SPEED_MULTIPLYER = 10;

    private static final int MAX_HITS_PER_SECOND = 5;
    private static final long HIT_COOLDOWN = 1000 / MAX_HITS_PER_SECOND;

    private long lastHitTime; // last time monster is touched/hit by player

    private static int monstersCounter;
    protected final Handler handler;
    protected int health; // need to be shared with monters subclasses @Sergio-Dobrianskiy
    protected int power; // power which the plyer is hit by when in contact with a monster
    protected float mx; // Player Position throu observer
    protected float my; // Player Position throu observer
    protected MainPlayer player;
    protected double speed;
    protected boolean isBig;
    protected Drop dropStrategy; // strategy for dropping life or experience

    /**
     * Monster constructor.
     * 
     * @param x
     * @param y
     * @param h
     */
    protected Monster(final float x, final float y, final Handler h) {
        super(x, y, ID.Monster);
        Monster.monstersCounter++;
        this.player = h.getPlayer();
        this.player.addObserver(this);
        this.lastHitTime = 0;
        this.handler = h;
        this.dropStrategy = new Drop(this, this.handler);

        // System.out.println("Added Observer: " + this.toString());
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public boolean isDead() {
        return this.getHealth() <= 0;
    }

    /**
     * At death, Entity drop experience pill.
     * 
     * @return Experience pill
     */
    // public Experience dropExperience() {
    // return new Experience(this.getX(), this.getY(), DEFAULT_EXPERIENCE,
    // this.handler);
    // }

    /**
     * At death, Entity drop experience pill.
     * 
     * @return Experience pill
     */
    // public Life dropLife() {
    // return new Life(this.getX(), this.getY());
    // }

    @Override
    public void hit(final int weaponDamage) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime >= HIT_COOLDOWN) {
            this.health -= weaponDamage;
            lastHitTime = currentTime;
            this.bounce();
        }

        if (this.isDead()) {
            player.removeObserver(this);
            this.die();
        }
    }

    /**
     * Entity bounce if hit by weapon.
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
    }

    @Override
    public void die() {

        this.handler.addObject(this.dropStrategy.drop());
        this.handler.removeObject(this); // monster is removed from Monsters list
        this.removeMonster(this);
    }

    /**
     * @return number of total monster.
     */
    public static int getMonstersCounter() {
        return monstersCounter;
    }

    @Override
    public void update(final MainPlayer mp) {
        mx = mp.getX();
        my = mp.getY();
    }

    @Override
    public void reachTarget() {
        this.setX(this.getX() + this.velX);
        this.setY(this.getY() + this.velY);
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

    @Override
    public void setBig(final boolean b) {
        this.isBig = b;
    }

    @Override
    public void setStartingPosition(final float minDistance, final float maxDistance) {
        Pair<Float, Float> randomPosition = Func.randomPoint(minDistance, maxDistance);
        update(player); // to get player position
        this.setX(mx + randomPosition.getX());
        this.setY(my + randomPosition.getY());
        // System.out.println("[" + mx + "," + my + "]");
    }

    @Override
    public void removeMonster(Monster monster) {
        monster = null;
        monstersCounter--;
    }

    public int getDefaultExperience() {
        return DEFAULT_EXPERIENCE;
    }

}
