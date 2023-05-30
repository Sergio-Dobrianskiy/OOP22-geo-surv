package it.unibo.geosurv.model.loader;

import it.unibo.geosurv.view.graphics.Camera;

/**
 * Interface for a loader class that manages all loads in the game
 */
public interface ILoader {
    
    /*
     * loads Player, textures, weapons, level
     */
    void loadAll();
    
    /*
     * Loads the Camera
     */
    Camera loadCamera();
}
