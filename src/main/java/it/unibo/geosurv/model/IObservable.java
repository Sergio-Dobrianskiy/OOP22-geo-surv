package it.unibo.geosurv.model;

public interface IObservable {

    /**
     * adds an observer to the player.
     * 
     * @param observer
     */
    void addObserver(IObserverEntity observer);

    /**
     * removes an observer from the player.
     * 
     * @param observer
     */
    void removeObserver(IObserverEntity observer);

    /**
     * notifies each observer.
     */
    void notifyObservers();
}