package it.unibo.geosurv.model.player;

/**
 * Interface which model the player.
 */
public interface IPlayer {

    /**
     * Actions player does each tick.
     */
    void tick();

    /**
     * @return the experience of ther player.
     */
    int getExperience();

    /**
     * increases player experience.
     * 
     * @param experience
     */
    void setExperience(int experience);

    /**
     * @return how much health the Player has left.
     */
    int getLife();

    /**
     * heals/damages the player.
     * 
     * @param life
     */
    void setLife(int life);

    /**
     * Player life is diminished by damage.
     * 
     * @param damage
     */
    void hit(int damage);
    
    /**
     * check if the player is alive
     *  
     * @return true if the player is alive
     */
    boolean isAlive();
}
