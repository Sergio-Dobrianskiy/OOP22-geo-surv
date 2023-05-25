package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rect;

public class GenerateMonsterR implements GenerateMonster {

    private float minDistance = 500.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 600.0f; // default monster creation min radius (distance from player)

    public Monster createMonster(Handler h) {
        Monster m = new Rect(h);
        m.setStartingPosition(minDistance, maxDistance);
        return m;
    }

}
