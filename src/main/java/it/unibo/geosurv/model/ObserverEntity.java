package it.unibo.geosurv.model;

/** Interface for observers. */
public interface ObserverEntity<E> {
    /**
     * Allow to update observers/monsters to follow the player.
     * 
     * @param e the entity being observed
     */
    void update(E e);
}
