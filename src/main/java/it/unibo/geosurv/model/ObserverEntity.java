package it.unibo.geosurv.model;

import it.unibo.geosurv.model.player.MainPlayer;

/** Interface for observers. */
public interface ObserverEntity {
    /**
     * Allow to update observers/monsters to follow the player.
     * 
     * @param mainPlayer reference.
     */
    void update(MainPlayer mainPlayer);
}
