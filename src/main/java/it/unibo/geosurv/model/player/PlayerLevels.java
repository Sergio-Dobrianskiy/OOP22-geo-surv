package it.unibo.geosurv.model.player;

/**
 * Manages payer levels.
 */
public class PlayerLevels {

    /**
     * player starts at level 1.
     */
    private final int startingLevel = 1;
    /**
     * player's maximum level (currently: 4 weapons * 3 levels each).
     */
    private final int maxLevel = 12;
    /**
     * experience needed to level up from level 1 to 2.
     */
    private final int baseExp = 2;
    /**
     * experience multiplier for each level up.
     */
    private final float levelMultiplier = 1.1f;
    /**
     * player's current level.
     */
    private int currentLevel;
    /**
     * player's experience.
     */
    private int currentExperience;
    /**
     * total experience needed to level up.
     */
    private int expToLevelUp;
    private final Player player;

    /**
     * Constructor for this class.
     *
     * @param player game's player
     */
    public PlayerLevels(final Player player) {
        this.currentLevel = this.startingLevel;
        this.currentExperience = 0;
        this.expToLevelUp = this.baseExp;
        this.player = player;
    }

    /**
     * Constructor for this class.
     *
     * @param exp experience gained
     */
    public void expUp(final int exp) {
        this.currentExperience += exp;
        if (this.currentExperience > this.expToLevelUp && this.currentLevel < this.maxLevel) {
            this.levelUp();
        }
    }

    /**
     * Levels up the player.
     * 
     */
    public void levelUp() {
        if (this.currentLevel < this.maxLevel) {
            this.currentLevel += 1;
            this.currentExperience = 0;
            this.expToLevelUp *= this.levelMultiplier;
            this.player.levelUpWeapon();
        }
    }

    /**
     * Returns player level.
     * 
     * @return int player level
     */
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Returns player experience.
     * 
     * @return int player's experience
     */
    public int currentExperience() {
        return this.currentExperience;
    }

    /**
     * return total experience needed to reach the next level.
     * 
     * @return int experience needed to reach the next level
     */
    public int getExpToLevelUp() {
        return this.expToLevelUp;
    }

}
