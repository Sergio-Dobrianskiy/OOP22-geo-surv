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

public class MainPlayer extends GameObject implements MainPlayerInterf {

    public static final int PLAYER_HEIGHT = 59;
    public static final int PLAYER_WIDTH = 59;
    public static final int HALF_PLAYER_HEIGHT = PLAYER_HEIGHT / 2;
    public static final int HALF_PLAYER_WIDTH = PLAYER_WIDTH / 2;
    public static final int PLAYER_SPEED = 5;
    public static final int MAX_LIFE = 100;
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
     * MainPlayer Constructor
     * 
     * @param x
     * @param y
     * @param handler
     */
    public MainPlayer(final float x, final float y, final Handler handler) {
        super(x, y, ID.Player);
        this.handler = handler;
        this.life = MAX_LIFE;
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
     * TODO: javadoc
     * 
     * @return
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

    @Override
    public final int getLife() {
        return this.life;
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
