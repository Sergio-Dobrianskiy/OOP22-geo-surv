package it.unibo.geosurv.model.monsters;

import it.unibo.geosurv.model.Handler;

/**
 * interface which models the creation of different type of monsters
 * 
 */
public interface GenerateMonster {

    Monster createMonster(Handler h);

}
