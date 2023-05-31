package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rhombus;

/**
 * class to generate Rhombus monsters.
 */
public class GenerateMonsterRh implements GenerateMonster<Monster, Handler> {

    private static final float MIN_DISTANCE = 150.0f; // default monster creation min radius (distance from player)
    private static final float MAX_DISTANCE = 250.0f; // default monster creation min radius (distance from player)

    @Override
    public final Monster createMonster(final Handler h) {
        Monster r = new Rhombus(h);
        r.setStartingPosition(MIN_DISTANCE, MAX_DISTANCE);
        return r;
    }

}
