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
    private int life;
    private Collisions collisions;
    private PlayerMovement playerMovement;
    private PlayerLevels playerLevels;
    private List<ObserverEntity> observers;
    private ArrayList<Weapon> weapons;
    private WeaponLevels weaponLevels;

    public MainPlayer(float x, float y, Handler handler) {
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

    public void tick() {
        x += velX;
        y += velY;
        collisions.checkPlayerCollisions();
        this.playerMovement.movePlayer();
        notifyObservers(); // notify player position
    }

    public int getExperience() {
        return this.playerLevels.getCurrentExp();
    }

    public int getMaxExperience() {
        return this.playerLevels.getExpToLevel();
    }

    public int getLevel() {
        return this.playerLevels.getCurrentLevel();
    }

    public float getExpPercentage() {
        if (this.getExperience() == 0) {
            return 0;
        }
        return ((float) this.getExperience() / this.getMaxExperience()) * 100;
    }

    /**
     * increases player experience.
     * 
     * @param int experience
     */
    public void setExperience(final int experience) {
        this.playerLevels.expUp(experience);
    }

    /**
     * @return how much health the Player has left
     */
    public int getLife() {
        return this.life;
    }

    /**
	 * heals/damages the player.
	 */
    public void setLife(int life) {
        this.life += life;
    }

    /**
	 * adds an observer to the player.
	 */
    public void addObserver(ObserverEntity observer) {
        this.observers.add(observer);
    }
	
	/**
	 * removes an observer from the player.
	 */
    public void removeObserver(ObserverEntity observer) {
        this.observers.remove(observer);
    }

    /**
	 * notifies each observer.
	 */
    private void notifyObservers() {
        for (ObserverEntity observer : observers) {
            observer.update(this);
        }
    }

    /**
	 * manages hit cooldown and damage.
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
