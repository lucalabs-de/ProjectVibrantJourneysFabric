package de.lucalabs.vibrantjourneys.blocks;

public class FallenLeavesBlock extends Block implements Waterloggable {

  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
  protected static final VoxelShape SHAPE = Block.createCuboidShape(0.01D, 0.0D, 0.0D, 16.0D, 2.01D, 16.0D);

  public FallenLeavesBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(getDefaultState().with(WATERLOGGED, false));
  }

  @Override
  public boolean canBeReplaced(BlockState state, ItemPlacementContext context) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return context.getItemInHand().isEmpty() || !context.getItemInHand().is(this.asItem());
    }
    return super.canBeReplaced(state, context);
  }

  @Override
  public boolean canBeReplaced(BlockState state, Fluid fluid) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return true;
    }
    return super.canBeReplaced(state, fluid);
  }

  @Override
  public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
    }
    return !state.canSurvive(world, currentPos) ? Blocks.AIR.getDefaultState() : state;
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return Block.isFaceFullSquare(world.getBlockState(pos.down()).getCollisionShape(world, pos.down()), Direction.UP);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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
