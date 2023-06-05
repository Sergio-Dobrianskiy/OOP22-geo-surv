package it.unibo.geosurv.model;

/**
 * Interface for Observable
 */
public interface IObservable {

    /**
     * adds an observer to the player.
     * 
     * @param observer
     */
    void addObserver(IObserverEntity<? extends GameObject> observer);

    /**
     * removes an observer from the player.
     * 
     * @param observer
     */
    void removeObserver(IObserverEntity<? extends GameObject> observer);

    /**
     * notifies each observer.
     */
    void notifyObservers();
}