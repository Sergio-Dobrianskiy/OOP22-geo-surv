package it.unibo.geosurv.model.player;

import it.unibo.geosurv.model.ObserverEntity;

public interface MainPlayerInterf {

    // *TODO:javadoc */
    void tick();

    // *TODO:javadoc */
    int getExperience();

    /**
     * increases player experience
     * 
     * @param int experience
     */
    void setExperience(int experience);

    /**
     * @return how much health the Player has left
     */
    int getLife();

    /**
     * heals/damages the player
     */
    void setLife(int life);

    /**
     * adds an observer to the player
     */
    void addObserver(ObserverEntity observer);

    // *TODO:javadoc */
    void removeObserver(ObserverEntity observer);

    // *TODO:javadoc */
    void hit(int damage);
}
