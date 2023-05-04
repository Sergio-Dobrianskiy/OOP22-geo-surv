package it.unibo.geosurv.core;

import it.unibo.geosurv.weapons.Weapon;

/** Interface for generic evil */
public abstract class Monster {

    private int health;

    /**
     * @return an integer showing how much health entity has left
     */
    int getHealth() {
        return this.health;
    };

    /**
     * Check if Entity is alive
     * 
     * @return boolen value
     */
    boolean isDead() {
        return this.getHealth() < 0 ? true : false;
    };

    /**
     * At death, Entity drop experience pill
     * 
     * @return Experience pill
     */
    Experience dropExperience() {
        // TODO: to correct!
        return new Experience((float) 11, (float) 11, ID.Experience, 1);
    };

    /**
     * Entity's been hit by player weapon
     * 
     * @param weapon whih hits the entity
     */
    // TODO: int or void ?? -> //int takeHit(Weapon weapon);
    int takeHit(int damage) {
        return this.health - damage;
    };

    /**
     * Entity dies and it is removed
     */
    void die() {
        this.dropExperience();
        // monster should be removed from Monsters set/list
    };

}
