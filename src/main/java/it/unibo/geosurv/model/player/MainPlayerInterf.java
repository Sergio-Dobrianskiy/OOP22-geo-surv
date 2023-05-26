package it.unibo.geosurv.model.player;

import it.unibo.geosurv.model.ObserverEntity;

/**
 * Interface which model the player.
 */
public interface MainPlayerInterf {

    /**
     * Actions player does each tick.
     */
    void tick();

    /**
     * @return the experience of ther player.
     */
    int getExperience();

    /**
     * increases player experience.
     * 
     * @param experience
     */
    void setExperience(int experience);

    /**
     * @return how much health the Player has left.
     */
    int getLife();

    /**
     * heals/damages the player.
     * 
     * @param life
     */
    void setLife(int life);

    /**
     * adds an observer to the player.
     * 
     * @param observer
     */
    void addObserver(ObserverEntity observer);

    /**
     * removes an observer from the player.
     * 
     * @param observer
     */
    void removeObserver(ObserverEntity observer);

    /**
     * Player life is diminished by damage.
     * 
     * @param damage
     */
    void hit(int damage);
}
