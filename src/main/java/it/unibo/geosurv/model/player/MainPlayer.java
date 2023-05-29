package it.unibo.geosurv.model.player;

import java.util.ArrayList;
import java.util.List;
import it.unibo.geosurv.control.PlayerMovement;
import it.unibo.geosurv.control.PlayerMovementImpl;
import it.unibo.geosurv.control.weapons.Weapon;
import it.unibo.geosurv.control.weapons.WeaponLevels;
import it.unibo.geosurv.model.Collisions;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.ObserverEntity;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a SatelliteGun weapon.
 */
public class MainPlayer extends GameObject implements MainPlayerInterf {

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
    private final int playerSpeed = 5;
    /**
     * Player's maximum live.
     */
    private final int maxLife = 100;

    private static final int MAX_HITS_PER_SECOND = 2;
    private static final long HIT_COOLDOWN = 1000 / MAX_HITS_PER_SECOND;
    private long lastHitTime; // last time Player is touched/hit by a monster
    private Handler handler;
    private int life;
    private Collisions collisions;
    private PlayerMovement playerMovement;
    private PlayerLevels playerLevels;
    private List<ObserverEntity> observers;
    private ArrayList<Weapon> weapons;
    private WeaponLevels weaponLevels;

    /**
     * Constructor for this class.
     *
     * @param x       explosion coordinate
     * @param y       explosion coordinate
     * @param handler game's Handler
     */
    public MainPlayer(final float x, final float y, final Handler handler) {
        super(x, y, ID.Player);
        this.handler = handler;
        this.life = maxLife;
        this.lastHitTime = 0;
        this.observers = new ArrayList<>();
        this.height = PLAYER_HEIGHT;
        this.width = PLAYER_WIDTH;
        this.collisions = new Collisions(handler);
        this.playerMovement = new PlayerMovementImpl(handler);
        // this.texture = Texture.PLAYER_DUCK; // alternative texture
        this.texture = Texture.PLAYER_MOUSE;
        this.playerLevels = new PlayerLevels(this);
        this.weapons = new ArrayList<>();
    }

    @Override
    public final void tick() {
        x += velX;
        y += velY;
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
        return ((float) this.getExperience() / this.getMaxExperience()) * 100;
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
        return this.life;
    }

    /**
     * returns player speed.
     * 
     * @return player's speed
     */
    public final int getSpeed() {
        return this.playerSpeed;
    }

    @Override
    public final void setLife(final int plusLife) {
        this.life += plusLife;
    }

    @Override
    public final void addObserver(final ObserverEntity observer) {
        this.observers.add(observer);
    }

    @Override
    public final void removeObserver(final ObserverEntity observer) {
        this.observers.remove(observer);
    }

    /**
     * notifies each observer.
     */
    private void notifyObservers() {
        for (final ObserverEntity observer : observers) {
            observer.update(this);
        }
    }

    /**
     * manages hit cooldown and damage.
     * 
     * @param damage
     */
    public void hit(final int damage) {
        long currentTime = System.currentTimeMillis();
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
    public void setWeapons(final ArrayList<Weapon> weapons) {
        this.weapons = weapons;
        this.weaponLevels = new WeaponLevels(weapons);
    }

    /**
     * levels up a random weapon.
     */
    public void levelUpWeapon() {
        this.weaponLevels.levelUpWeapon();
    }
}
