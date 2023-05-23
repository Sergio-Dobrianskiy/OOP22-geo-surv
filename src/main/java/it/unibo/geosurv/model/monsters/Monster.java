package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.MonstersObserver;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.drops.Life;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

/** Abstract Class for generic evil */
public abstract class Monster extends GameObject implements MonstersObserver {

    private final int DEFAULT_EXPERIENCE = 1;
    private final int BOUNCING_SPEED_MULTIPLYER = 10;
    private final int LIFE_PILLS_PROB = 50; // probability to get a life pill: 1/50 at monster death
    private long lastHitTime; // last time monster is touched/hit by player
    private static final int MAX_HITS_PER_SECOND = 5;
    private static final long HIT_COOLDOWN = 1000 / MAX_HITS_PER_SECOND;

    protected int health; // need to be shared with monters subclasses @Sergio-Dobrianskiy
    protected int power; // power which the plyer is hit by when in contact with a monster
    protected static int monstersCounter;
    protected float mx; // Player Position throu observer
    protected float my; // Player Position throu observer
    protected MainPlayer player;
    protected int dimension;
    protected double speed;
    protected boolean isBig;

    static long counterxx = 0;

    protected Monster(float x, float y) {
        super(x, y, ID.Monster);
        Monster.monstersCounter++;
        player = Game.returnHandler().getPlayer();
        player.addObserver(this);
        this.lastHitTime = 0;
        // System.out.println("Added Observer: " + this.toString());

    }

    /**
     * @return an integer showing how much health entity has left
     */
    public int getHealth() {
        return this.health;
    }

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
        return this.getHealth() <= 0;
    }

    /**
     * At death, Entity drop experience pill
     * 
     * @return Experience pill
     */
    public Experience dropExperience() {
        return new Experience(this.x, this.y, DEFAULT_EXPERIENCE);
    }

    /**
     * At death, Entity drop experience pill
     * 
     * @return Experience pill
     */
    public Life dropLife() {
        return new Life(this.x, this.y);
    }

    /**
     * Entity's been hit by player weapon
     * 
     * @param weapon whih hits the entity
     */
    public void hit(int weaponDamage) {
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
    }

    private boolean shouldDropLife() {
        // Generate a random number between 0 and 49
        double num = Math.random();
        int randomNumber = (int) (num * LIFE_PILLS_PROB);
        // Return true if the random number is 0 (probability of 1/50)
        // System.out.println("Math.random() + " + num + " random: " + randomNumber);
        return randomNumber == 0;
    }

    /**
     * Entity dies, drop experience and it is removed
     */
    public void die() {

        Handler h = Game.returnHandler();
        if (shouldDropLife()) {
            h.addObject(this.dropLife());
        } else {
            h.addObject(this.dropExperience());
        }
        monstersCounter--;
        h.removeObject(this); // monster is removed from Monsters set/list
    }

    public static int getMonstersCounter() {
        return monstersCounter;
    }

    /** Monster is update about player posistion throu notify - Observer method */
    public void update(MainPlayer mp) {
        mx = mp.getX();
        my = mp.getY();
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

    public void setBig(boolean b) {
        this.isBig = b;
    }

    public void setStartingPosition(float minDistance, float maxDistance) {
        Pair<Float, Float> randomPosition = Func.randomPoint(minDistance, maxDistance);
        update(player); // to get player position
        x = mx + randomPosition.getX();
        y = my + randomPosition.getY();
        // System.out.println("[" + mx + "," + my + "]");
    }

}
