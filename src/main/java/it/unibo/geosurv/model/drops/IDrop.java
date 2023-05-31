package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;

/**
 * Interface toallow drop of {@link GameObject}.
 * 
 * @param <T>
 */
public interface IDrop<T> {

    /**
     * @return the dropped object
     */
    T drop();

}
