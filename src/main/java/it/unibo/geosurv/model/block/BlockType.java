package it.unibo.geosurv.model.block;

/**
 * List of possibile blocks.
 */
public enum BlockType {
    /**
     * Empty space.
     */
    EMPTY,
    /**
     * Heals the player.
     */
    HEALING_FLOOR,
    /**
     * Damages the player.
     */
    LAVA,
    /**
     * Impenetrable wall.
     */
    WALL,
}
