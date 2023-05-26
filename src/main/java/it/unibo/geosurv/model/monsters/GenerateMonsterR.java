package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rect;

/**
 * class to generate Rect monsters.
 */
public class GenerateMonsterR implements GenerateMonster {

    private float minDistance = 500.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 600.0f; // default monster creation min radius (distance from player)

    @Override
    public final Monster createMonster(final Handler h) {
        Monster m = new Rect(h);
        m.setStartingPosition(minDistance, maxDistance);
        return m;
    }

}
