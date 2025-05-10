package de.lucalabs.vibrantjourneys.blocks;

public class SandySproutsBlock extends BeachGrassBlock {

  protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
  public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

  public SandySproutsBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
  }

  @Override
  public VoxelShape getShape(BlockState blockstate, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  public BlockState getStateForPlacement(ItemPlacementContext context) {
    Direction facing = Direction.Plane.HORIZONTAL.getRandomDirection(context.getWorld().getRandom());
    return this.getDefaultState().with(FACING, facing);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}
