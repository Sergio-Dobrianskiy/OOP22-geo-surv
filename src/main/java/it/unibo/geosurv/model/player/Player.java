package it.unibo.geosurv.model.player;

import java.util.ArrayList;
import java.util.List;
import it.unibo.geosurv.control.IPlayerMovement;
import it.unibo.geosurv.control.PlayerMovement;
import it.unibo.geosurv.control.weapons.Weapon;
import it.unibo.geosurv.control.weapons.WeaponLevels;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.IObservable;
import it.unibo.geosurv.model.IObserverEntity;
import it.unibo.geosurv.model.collisions.Collisions;
import it.unibo.geosurv.model.collisions.ICollisionBehavior;
import it.unibo.geosurv.model.collisions.StopPlayerBehavior;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents Player.
 * 
 */
public class Player extends GameObject implements IPlayer, IObservable {

    /**
     * Player's height.
     */
    public static final int PLAYER_HEIGHT = 59;
    /**
     * Player's width.
     */
    public static final int PLAYER_WIDTH = 59;
    /**
     * Player's speed.
     */
    private static final int PLATER_SPEED = 5;
    /**
     * Player's maximum live.
     */
    private static final int MAX_LIFE = 100;

    private static final int MAX_HITS_PER_SECOND = 2;
    private static final long HIT_COOLDOWN = 1000 / MAX_HITS_PER_SECOND;
    private long lastHitTime; // last time Player is touched/hit by a monster
    private int life;
    private final Collisions collisions;
    private final IPlayerMovement playerMovement;
    private final PlayerLevels playerLevels;
    private final List<IObserverEntity<? extends GameObject>> observers;
    private WeaponLevels weaponLevels;
    private final ICollisionBehavior collisionBehavior;
    private final Handler handler;


    /**
     * Constructor for this class.
     *
     * @param x       explosion coordinate
     * @param y       explosion coordinate
     * @param handler game's Handler
     */
    public Player(final float x, final float y, final Handler handler) {
        super(x, y, ID.Player);
        this.life = MAX_LIFE;
        this.lastHitTime = 0;
        this.observers = new ArrayList<>();
        this.height = PLAYER_HEIGHT;
        this.width = PLAYER_WIDTH;
        this.handler = handler;
        this.collisions = new Collisions(handler);
        this.playerMovement = new PlayerMovement(handler);
        // this.texture = Texture.PLAYER_DUCK; // alternative texture
        this.texture = Texture.PLAYER_MOUSE;
        this.playerLevels = new PlayerLevels(this);
                 this.collisionBehavior = new StopPlayerBehavior();
    }

    @Override
    public final void tick() {
        this.setX(this.getX() + this.velX);
        this.setY(this.getY() + this.velY);
        collisions.checkPlayerCollisions();
        this.playerMovement.movePlayer();
        notifyObservers(); // notify player position
    }

    @Override
    public final int getExperience() {
        return this.playerLevels.currentExperience();
    }

    /**
     * TODO: javadoc
     * 
     * @return
     */
    public int getMaxExperience() {
        return this.playerLevels.getExpToLevelUp();
    }

    /**
     * Returns the level the player is in.
     * 
     * @return the level the player is in
     */
    public int getLevel() {
        return this.playerLevels.getCurrentLevel();
    }

    /**
     * TODO: javadoc
     * 
     * @return
     */
    public float getExpPercentage() {
        if (this.getExperience() == 0) {
            return 0;
        }
        return (float) this.getExperience() / this.getMaxExperience();
    }

    /**
     * TODO: javadoc
     * 
     * @return
     */
    public float getLifePercentage() {
        if (this.getLife() == 0) {
            return 0;
        }
        return (float) this.getLife() / this.getMaxLife();
    }

    @Override
    public final void setExperience(final int experience) {
        this.playerLevels.expUp(experience);
    }

    /**
     * returns player current life.
     * 
     * @return player's current life
     */
    @Override
    public final int getLife() {
        return this.life;
    }

    /**
     * returns player maximum life.
     * 
     * @return player's maximum life
     */
    public final int getMaxLife() {
        return MAX_LIFE;
    }

    /**
     * returns player speed.
     * 
     * @return player's speed
     */
    public final int getSpeed() {
        return PLATER_SPEED;
    }

    @Override
    public final void setLife(final int plusLife) {
        this.life = this.life + plusLife > this.getMaxLife() // life cannot be more than maxLife
                ? this.getMaxLife()
                : this.life + plusLife;
    }

    @Override
    public final void addObserver(final IObserverEntity<? extends GameObject> observer) {
        this.observers.add(observer);
    }

    @Override
    public final void removeObserver(final IObserverEntity<? extends GameObject> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (final IObserverEntity<? extends GameObject> observer : observers) {
            observer.update();
        }
    }

    /**
     * manages hit cooldown and damage.
     * 
     * @param damage
     */
    public void hit(final int damage) {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime >= HIT_COOLDOWN) {
            this.setLife(-damage);
            lastHitTime = currentTime;
        }
    }

    /**
     * passes all weapons to the player class instance.
     * 
     * @param weapons player's weapons
     */
    public void setWeapons(final List<Weapon> w) {
        // this.weapons = w;
        this.weaponLevels = new WeaponLevels(w);
    }

    /**
     * levels up a random weapon.
     */
    public void levelUpWeapon() {
        this.weaponLevels.levelUpWeapon();
    }

    /**
     * check if the player is alive.
     * 
     * @return true if the player is alive
     */
    public boolean isAlive() {
        return life > 0;
    }

    @Override
    public void collide() {
        this.collisionBehavior.collide(this, this.handler);
    }

}
