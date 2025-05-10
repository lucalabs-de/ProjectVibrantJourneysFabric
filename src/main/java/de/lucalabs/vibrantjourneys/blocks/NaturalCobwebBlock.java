package de.lucalabs.vibrantjourneys.blocks;

/**
 * Mimic vanilla block but make it destroy itself when not adjacent to any leaves
 * to prevent odd floating cobwebs when trees are cut down
 */
public class NaturalCobwebBlock extends WebBlock {

  public NaturalCobwebBlock() {
    super(AbstractBlock.Settings.copy(Blocks.COBWEB));
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    for (Direction d : Direction.values()) {
      if (world.getBlockState(pos.offset(d.getNormal())).getBlock() instanceof LeavesBlock) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    if (!canPlaceAt(state, world, pos))
      world.removeBlock(pos, isMoving);
  }
}