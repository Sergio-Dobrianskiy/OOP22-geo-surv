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
    
    /**
     * Returns the level the player is in.
     * 
     */
    int getLevel();
    
    /**
     * 
     */
    float getExpPercentage();
    
    /**
     * 
     */
    float getLifePercentage();
    
    
    /**
     * @return how much health the Player has left.
     */
    int getLife();
    
    /**
     * returns player maximum life.
     * 
     * @return player's maximum life
     */
    int getMaxLife();
    
    /**
     * manages players collisions.
     */
    void collide();
}
