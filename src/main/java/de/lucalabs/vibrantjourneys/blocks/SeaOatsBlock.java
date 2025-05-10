package de.lucalabs.vibrantjourneys.blocks;

public class SeaOatsBlock extends TallPlantBlock {

  public SeaOatsBlock(AbstractBlock.Settings props) {
    super(props);
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    if (state.get(HALF) != DoubleBlockHalf.UPPER) {
      BlockState ground = world.getBlockState(pos.down());

      if (!ground.isFaceSturdy(world, pos.down(), Direction.UP))
        return false;
      return ground.is(BlockTags.SAND) || ground.is(BlockTags.DIRT);
    } else {
      BlockState blockstate = world.getBlockState(pos.down());
      if (state.getBlock() != this)
        return false;
      return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
    }
  }
}