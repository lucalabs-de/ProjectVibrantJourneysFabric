package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockView;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.ShapeContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LotusBlock extends WaterlilyBlock {

  protected static final VoxelShape AABB = Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 10, 12.0);

  public LotusBlock(Properties props) {
    super(props);
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
    return AABB;
  }

  @Override
  public void entityInside(BlockState pState, World pLevel, BlockPos pPos, Entity pEntity) {}
}