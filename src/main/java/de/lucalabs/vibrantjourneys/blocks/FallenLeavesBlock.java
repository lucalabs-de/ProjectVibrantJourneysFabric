package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class FallenLeavesBlock extends Block implements Waterloggable {

  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
  protected static final VoxelShape SHAPE = Block.createCuboidShape(0.01D, 0.0D, 0.0D, 16.0D, 2.01D, 16.0D);

  public FallenLeavesBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(getDefaultState().with(WATERLOGGED, false));
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return context.getStack().isEmpty() || !context.getStack().isOf(this.asItem());
    }
    return super.canReplace(state, context);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canBucketPlace(BlockState state, Fluid fluid) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return true;
    }
    return super.canBucketPlace(state, fluid);
  }

  @Override
  @SuppressWarnings("deprecation")
  public BlockState getStateForNeighborUpdate(
          BlockState state,
          Direction facing,
          BlockState facingState,
          WorldAccess world,
          BlockPos currentPos,
          BlockPos facingPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }
    return !state.canPlaceAt(world, currentPos) ? Blocks.AIR.getDefaultState() : state;
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return Block.isFaceFullSquare(world.getBlockState(pos.down()).getCollisionShape(world, pos.down()), Direction.UP);
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  @SuppressWarnings("deprecation")
  public FluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(WATERLOGGED);
  }
}
