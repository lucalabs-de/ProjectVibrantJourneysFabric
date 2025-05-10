package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class BarkMushroomBlock extends EpiphyteBlock {
    protected static final VoxelShape EAST = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH = Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    public BarkMushroomBlock(AbstractBlock.Settings props) {
        super(props);
    }

    public static BarkMushroomBlock getRandom(Random rand) {
        float f = rand.nextFloat();
        if (f > 0.66F)
            return (BarkMushroomBlock) PVJBlocks.BARK_MUSHROOM;
        else if (f > 0.33F)
            return (BarkMushroomBlock) PVJBlocks.LIGHT_BROWN_BARK_MUSHROOM;

        return (BarkMushroomBlock) PVJBlocks.ORANGE_BARK_MUSHROOM;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> EAST;
        };
    }
}