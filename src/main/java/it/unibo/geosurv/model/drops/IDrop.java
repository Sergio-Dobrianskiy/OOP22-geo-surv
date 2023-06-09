package it.unibo.geosurv.model.drops;

/**
 * Interface to allow drop of {@link GameObject}.
 * 
 * @param <T>
 */
public interface IDrop<T> {

    /**
     * @return the dropped object
     */
    T drop();

}
