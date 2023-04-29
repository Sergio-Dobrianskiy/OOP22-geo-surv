package it.unibo.geosurv.core;

/** Interface for generic evil */
public interface Entity {

    /**
     * 
     * @return
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

    /** Entity's been hit by player weapon */
    void hitByWeapon();
}
