package de.lucalabs.vibrantjourneys.blocks;

public class NetherPlantBlock extends PlantBlock {

  public NetherPlantBlock(AbstractBlock.Settings props) {
    super(props);
  }

  @Override
  protected boolean mayPlaceOn(BlockState state, BlockView worldIn, BlockPos pos) {
    return state.is(BlockTags.NYLIUM) || state.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(state, worldIn, pos);
  }
}