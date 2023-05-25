package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rhombus;

public class GenerateMonsterRh implements GenerateMonster {

    private float minDistance = 150.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 250.0f; // default monster creation min radius (distance from player)

    public Monster createMonster(Handler h) {
        Rhombus r = new Rhombus(h);
        r.setStartingPosition(minDistance, maxDistance);
        return r;
    }

}
