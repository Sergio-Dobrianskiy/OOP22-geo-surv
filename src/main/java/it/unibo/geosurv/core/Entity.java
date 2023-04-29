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
    void takeHit(Weapon weapon);

}
