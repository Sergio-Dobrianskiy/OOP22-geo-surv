package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;

/**
 * Interface toallow drop of {@link GameObject}.
 */
public interface Drop {

    /**
     * @return the dropped object
     */
    GameObject drop();

}
