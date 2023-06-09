package it.unibo.geosurv.model.monsters;

/**
 * Interface to model Monsters.
 */
public interface IMonster {

    /**
     * @return an integer showing how much health entity has left.
     */
    int getHealth();

    /**
     * @return an integer showing how much power entity has hit the player.
     */
    int getPower();

    /**
     * Check if Entity is alive.
     * 
     * @return boolen value
     */
    boolean isDead();

    /**
     * Entity's been hit by player weapon.
     * 
     * @param weaponDamage whih hits the entity
     */
    void hit(int weaponDamage);

    /**
     * Allow the monster to try to reach the player.
     */
    void reachTarget();

    /**
     * Define if a monster is a big one.
     * 
     * @param b
     */
    void setIsBig(boolean b);

    /**
     * Set the starting position of a monster after it's born.
     * Position is a random point between two circle (the first of radius
     * minDistance and the second with a radius of maxDistance).
     * 
     * @param minDistance from player
     * @param maxDistance from player
     */
    void setStartingPosition(float minDistance, float maxDistance);

    /**
     * Entity dies, drop experience or life and it is removed.
     */
    void die();
}
