package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class NetherPlantBlock extends PlantBlock {

    public NetherPlantBlock(AbstractBlock.Settings props) {
        super(props);
    }

    @Override
    protected boolean canPlantOnTop(BlockState state, BlockView worldIn, BlockPos pos) {
        return state.isIn(BlockTags.NYLIUM) || state.isOf(Blocks.SOUL_SOIL) || super.canPlantOnTop(state, worldIn, pos);
    }
}