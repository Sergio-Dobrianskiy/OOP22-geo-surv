package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Triangle;

/**
 * class to generate Triangle monsters.
 */
public class GenerateMonsterT implements GenerateMonster {

    private float minDistance = 300.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 400.0f; // default monster creation min radius (distance from player)

    @Override
    public final Monster createMonster(final Handler h) {
        Monster m = new Triangle(h);
        m.setStartingPosition(minDistance, maxDistance);
        return m;
    }

}
