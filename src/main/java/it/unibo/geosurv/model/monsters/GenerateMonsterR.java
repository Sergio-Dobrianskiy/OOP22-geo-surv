package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.monsters.types.Rect;

/**
 * class to generate Rect monsters.
 */
public class GenerateMonsterR implements GenerateMonster<Monster, Handler> {

    private static final float MIN_DISTANCE = 500.0f; // default monster creation min radius (distance from player)
    private static final float MAX_DISTANCE = 600.0f; // default monster creation min radius (distance from player)
    private boolean isBig;

    @Override
    public final Monster createMonster(final Handler h) {
        final Monster m = new Rect(h);
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
     * @return GenerateMonsterR
     */
    @Override
    public GenerateMonster<Monster, Handler> toBig() {
        this.isBig = true;
        return this;
    }

}
