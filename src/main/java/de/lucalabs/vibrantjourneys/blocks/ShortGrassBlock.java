package de.lucalabs.vibrantjourneys.blocks;

public class ShortGrassBlock extends PlantBlock {

  protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
  public static final IntegerProperty MODEL = IntegerProperty.create("model", 0, 6);

  public ShortGrassBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(MODEL, 0));
  }

  @Override
  public BlockState getStateForPlacement(ItemPlacementContext context) {
    int model = context.getWorld().getRandom().nextInt(7);
    return this.getDefaultState().with(MODEL, model);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(MODEL);
  }
}