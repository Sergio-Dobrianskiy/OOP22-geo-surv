package it.unibo.geosurv.core;

import it.unibo.geosurv.weapons.Weapon;

/** Interface for generic evil */
public interface Entity {

    /**
     * 
     * @return a new {@link Entity}
     */
    Entity born();

    /**
     * @return an integer showing how much health entity has left
     */
    int getHealth();

    /**
     * Check if Entity is alive
     * 
     * @return boolen value
     */
    boolean isDead();

    /**
     * At death, Entity drop experience pill
     * 
     * @return Experience
     */
    Experience dropExperience();

    /**
     * Entity's been hit by player weapon
     * 
     * @param weapon whih hits the entity
     */
    // TODO: int or void??
    int takeHit(Weapon weapon);

    /**
     * Entity dies and it is removed
     */
    void die();

}
