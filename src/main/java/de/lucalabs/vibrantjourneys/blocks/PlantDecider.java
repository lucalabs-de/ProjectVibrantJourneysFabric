package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public interface PlantDecider {
    boolean acceptsPlant(BlockState state, BlockView world, BlockPos pos, Direction facing, BlockState plant);
}
