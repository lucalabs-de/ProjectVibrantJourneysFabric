package de.lucalabs.vibrantjourneys.blocks;

public class GlowingFungusBlock extends EpiphyteBlock {
  protected static final VoxelShape EAST = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
  protected static final VoxelShape WEST = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
  protected static final VoxelShape SOUTH = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
  protected static final VoxelShape NORTH = Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

  public GlowingFungusBlock(AbstractBlock.Settings props) {
    super(props);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
    return switch ((Direction) state.get(FACING)) {
      case NORTH -> NORTH;
      case SOUTH -> SOUTH;
      case WEST -> WEST;
      default -> EAST;
    };
  }

  @Override
  public boolean canAttachTo(BlockView world, BlockPos pos, Direction direction) {
    BlockState blockstate = world.getBlockState(pos);
    return blockstate.is(Blocks.SCULK) && Block.isFaceFullSquare(world.getBlockState(pos).getCollisionShape(world, pos), direction);
  }
}