package it.unibo.geosurv.model.monsters;

/**
 * interface which models creation of evils entities.
 * 
 * @param <O> Output: returned created Object
 * @param <I> Handler input
 * 
 */
public interface GenerateMonster<O, I> {

    /**
     * Method to create monsters.
     * 
     * @param i Handler input
     * @return Output
     */
    O createMonster(I i);

    /**
     * Builder constructor to make Big Monsters.
     *
     * @return GenerateMonster<O, I>
     */
    GenerateMonster<O, I> toBig();
}
