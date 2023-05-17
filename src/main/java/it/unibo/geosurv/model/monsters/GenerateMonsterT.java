package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.monsters.types.Triangle;

public class GenerateMonsterT implements GenerateMonster {

    private float minDistance = 300.0f; // default monster creation min radius (distance from player)
    private float maxDistance = 400.0f; // default monster creation min radius (distance from player)

    public Monster createMonster() {
        Monster m = new Triangle();
        m.setStartingPosition(minDistance, maxDistance);
        return m;
    }

}
