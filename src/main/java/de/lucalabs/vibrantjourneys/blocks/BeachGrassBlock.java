package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class BeachGrassBlock extends PlantBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public BeachGrassBlock(AbstractBlock.Settings props) {
        super(props);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState blockstate, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

//  @Override
//  protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
//    return floor.isIn(BlockTags.SAND) || floor.isOf(Blocks.TERRACOTTA) || floor.getBlock() instanceof GlazedTerracottaBlock;
//  }

    // TODO check if the above does the same as the below

    //  @Override
//  public PlantType getPlantType(BlockView world, BlockPos pos) {
//    return PlantType.DESERT;
//  }


}
