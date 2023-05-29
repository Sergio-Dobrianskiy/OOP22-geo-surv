package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rect;

/**
 * class to generate Rect monsters.
 */
public class GenerateMonsterR implements GenerateMonster {

    private static final float MIN_DISTANCE = 500.0f; // default monster creation min radius (distance from player)
    private static final float MAX_DISTANCE = 600.0f; // default monster creation min radius (distance from player)

    @Override
    public final Monster createMonster(final Handler h) {
        Monster m = new Rect(h);
        m.setStartingPosition(MIN_DISTANCE, MAX_DISTANCE);
        return m;
    }

}
