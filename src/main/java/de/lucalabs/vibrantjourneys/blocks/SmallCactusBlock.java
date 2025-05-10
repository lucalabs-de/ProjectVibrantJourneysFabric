package de.lucalabs.vibrantjourneys.blocks;

import dev.orderedchaos.projectvibrantjourneys.util.LevelUtils;

public class SmallCactusBlock extends PlantBlock implements BonemealableBlock {

  protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

  public SmallCactusBlock(AbstractBlock.Settings props) {
    super(props);
  }

  @Override
  public VoxelShape getShape(BlockState blockstate, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  public PlantType getPlantType(BlockView world, BlockPos pos) {
    return PlantType.DESERT;
  }

  @Override
  public boolean isFertilizable(WorldView level, BlockPos pos, BlockState state, boolean isClientSide) {
    for (Direction direction : Direction.Type.HORIZONTAL) {
      BlockState blockstate = level.getBlockState(pos.relative(direction));
      if (blockstate.isSolid()) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean canGrow(World level, Random random, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void performBonemeal(ServerWorld level, Random random, BlockPos pos, BlockState state) {
    LevelUtils.setBlockState(level, pos, Blocks.CACTUS.getDefaultState(), 2);
  }
}
