package it.unibo.geosurv.model.block;

import java.util.Optional;

/**
 * Factory that creates Blocks.
 */
public class BlockFactory implements IBlockFactory {

    /**
     * Create a Block.
     * 
     * @param type of the block
     * @param x coordinate
     * @param y coordinate
     * 
     * @return the created block
     */
    @Override
    public Optional<Block> createBlock(final BlockType type, final float x, final float y) {
        if (type == BlockType.Wall) {
            return Optional.of(new Wall(x, y));
        }
        return Optional.empty();
    }
}
