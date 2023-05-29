package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;

/**
 * interface which models the creation of different type of monsters.
 * 
 */
public interface GenerateMonster {

    /**
     * Method to create monsters.
     * 
     * @param h handler
     * @return Monster
     */
    MonsterImpl createMonster(Handler h);

}
