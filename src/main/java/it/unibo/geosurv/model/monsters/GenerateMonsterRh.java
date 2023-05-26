package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rhombus;

/**
 * class to generate Rhombus monsters.
 */
public class GenerateMonsterRh implements GenerateMonster {

    private float minDistance = 150.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 250.0f; // default monster creation min radius (distance from player)

    @Override
    public final Monster createMonster(final Handler h) {
        Rhombus r = new Rhombus(h);
        r.setStartingPosition(minDistance, maxDistance);
        return r;
    }

}
