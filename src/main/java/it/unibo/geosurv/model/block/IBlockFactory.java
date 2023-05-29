package it.unibo.geosurv.model.block;

import java.util.Optional;

/**
 * Interface for a factory that creates Blocks.
 */
public interface IBlockFactory {

    /**
     * Create a Block.
     * 
     * @return the created block
     */
    Optional<Block> createBlock(BlockType type, float x, float y);
}
