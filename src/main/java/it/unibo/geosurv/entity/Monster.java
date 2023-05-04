package it.unibo.geosurv.entity;

import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.ID;

/** Interface for generic evil */
public abstract class Monster extends GameObject {

    private int DEFAULT_EXPERIENCE = 1;
    private int health;

    protected Monster(float x, float y, ID id) {
        super(x, y, id);

    }

    /**
     * @return an integer showing how much health entity has left
     */
    public int getHealth() {
        return this.health;
    };

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
        // TODO: to correct!
        return new Experience(this.x, this.y, ID.Experience, DEFAULT_EXPERIENCE);
    };

    /**
     * Entity's been hit by player weapon
     * 
     * @param weapon whih hits the entity
     */
    // TODO: int or void ?? -> //int takeHit(Weapon weapon);
    public int takeHit(int damage) {
        return this.health - damage;
    };

    /**
     * Attach target/Player
     */
    public abstract void reachTarget();

    /**
     * Entity dies and it is removed
     */
    public void die() {
        this.dropExperience();
        // monster should be removed from Monsters set/list
    };

}
