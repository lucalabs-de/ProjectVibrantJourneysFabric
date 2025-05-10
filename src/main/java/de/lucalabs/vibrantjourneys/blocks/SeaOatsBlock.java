package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class SeaOatsBlock extends TallPlantBlock {

    public SeaOatsBlock(AbstractBlock.Settings props) {
        super(props);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            BlockState ground = world.getBlockState(pos.down());

            if (!ground.isSideSolidFullSquare(world, pos.down(), Direction.UP))
                return false;
            return ground.isIn(BlockTags.SAND) || ground.isIn(BlockTags.DIRT);
        } else {
            BlockState blockstate = world.getBlockState(pos.down());
            if (state.getBlock() != this)
                return false;
            return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }
}