package de.lucalabs.vibrantjourneys.blocks;

public class SlimeNoduleBlock extends EpiphyteBlock {

  protected static final VoxelShape EAST = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
  protected static final VoxelShape WEST = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
  protected static final VoxelShape SOUTH = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
  protected static final VoxelShape NORTH = Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);


  public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

  public SlimeNoduleBlock(Properties props) {
    super(props);
  }

  @Override
  public boolean canAttachTo(BlockView world, BlockPos pos, Direction direction) {
    return Block.isFaceFullSquare(world.getBlockState(pos).getCollisionShape(world, pos), direction);
  }

  @Override
  public ActionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult brt) {
    return ActionResult.FAIL;
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
    return switch (state.get(FACING)) {
      case NORTH -> NORTH;
      case SOUTH -> SOUTH;
      case WEST -> WEST;
      default -> EAST;
    };
  }
}