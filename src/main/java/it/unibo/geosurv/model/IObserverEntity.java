package it.unibo.geosurv.model;

/**
 * Interface for observers.
 * 
 * @param <E> specific type for the observer
 */
public interface IObserverEntity<E> {
    /**
     * Allow to update observers/monsters to follow the player.
     * 
     */
    void update();
}
