package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.monsters.types.Rect;

public class GenerateMonsterR implements GenerateMonster {

    private float minDistance = 500.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 600.0f; // default monster creation min radius (distance from player)

    public Monster createMonster() {
        Monster m = new Rect();
        m.setStartingPosition(minDistance, maxDistance);
        return m;
    }

}
