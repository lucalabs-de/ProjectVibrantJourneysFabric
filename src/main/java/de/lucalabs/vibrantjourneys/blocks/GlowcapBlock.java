package de.lucalabs.vibrantjourneys.blocks;

public class GlowcapBlock extends MushroomBlock {

  public GlowcapBlock(AbstractBlock.Settings props) {
    super(props, null);
    //AbstractBlock.Settings.of(Material.PLANT, MaterialColor.COLOR_YELLOW)
    //				.noCollission()
    //				.randomTicks()
    //				.instabreak()
    //				.sound(SoundType.GRASS).lightLevel((state) -> {
    //		      return 12;
    //		   }), () -> null
  }

  @Override
  public boolean growMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
    return false;
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
    BlockPos blockpos = pos.down();
    BlockState blockstate = worldIn.getBlockState(blockpos);
    if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
      return true;
    } else {
      return blockstate.canSustainPlant(worldIn, blockpos, Direction.UP, this);
    }
  }

  @Override
  public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
    return false;
  }

  @Override
  public boolean isValidBonemealTarget(WorldView worldIn, BlockPos pos, BlockState state, boolean isClient) {
    return false;
  }
}