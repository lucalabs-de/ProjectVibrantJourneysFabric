package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SmallCactusBlock extends PlantBlock implements Fertilizable {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public SmallCactusBlock(AbstractBlock.Settings props) {
        super(props);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState blockstate, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean isFertilizable(WorldView level, BlockPos pos, BlockState state, boolean isClientSide) {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            BlockState blockstate = level.getBlockState(pos.offset(direction));
            if (blockstate.isSolid()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canGrow(World level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld level, Random random, BlockPos pos, BlockState state) {
        WorldUtils.setBlockState(level, pos, Blocks.CACTUS.getDefaultState(), 2);
    }
}
