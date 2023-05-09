package it.unibo.geosurv.model.monsters;

/**
 * interface which models the creation of different type of monsters
 * 
 */
public interface GenerateMonster {

    public Monster generateMonster(String monsterName, boolean isBig);

}
