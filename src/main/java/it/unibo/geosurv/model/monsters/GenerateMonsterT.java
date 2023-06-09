package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Triangle;

/**
 * class to generate Triangle monsters.
 */
public class GenerateMonsterT implements GenerateMonster<Monster, Handler> {

    private static final float MIN_DISTANCE = 300.0f; // default monster creation min radius (distance from player)
    private static final float MAX_DISTANCE = 400.0f; // default monster creation min radius (distance from player)
    private boolean isBig;

    @Override
    public final Monster createMonster(final Handler h) {
        final Monster m = new Triangle(h);
        m.setStartingPosition(MIN_DISTANCE, MAX_DISTANCE);
        if (this.isBig) {
            m.setBig();
        }
        this.isBig = false;
        return m;
    }

    /**
     * Builder constructor to make Big Monsters.
     * 
     * @return GenerateMonsterT
     */
    @Override
    public GenerateMonster<Monster, Handler> toBig() {
        this.isBig = true;
        return this;
    }

}
