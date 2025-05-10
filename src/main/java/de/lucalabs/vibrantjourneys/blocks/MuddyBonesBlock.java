package de.lucalabs.vibrantjourneys.blocks;

public class MuddyBonesBlock extends MudBlock {

  public static final IntProperty MODEL = IntProperty.create("model", 0, 2);
  public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

  public MuddyBonesBlock(Properties props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(MODEL, 0).with(FACING, Direction.NORTH));
  }

  @Override
  public BlockState getStateForPlacement(ItemPlacementContext context) {
    int model = context.getWorld().getRandom().nextInt(3);
    Direction facing = Direction.Type.HORIZONTAL.random(context.getWorld().getRandom());
    FluidState ifluidstate = context.getWorld().getFluidState(context.getClickedPos());
    return this.getDefaultState()
      .with(MODEL, model)
      .with(FACING, facing);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(MODEL, FACING);
  }
}