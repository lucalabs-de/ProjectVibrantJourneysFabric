package de.lucalabs.vibrantjourneys.blocks;

import com.mojang.serialization.MapCodec;
import dev.orderedchaos.projectvibrantjourneys.core.registry.PVJBlocks;

public class PinkVinesPlantBlock extends GrowingPlantBodyBlock {

  private static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

  public PinkVinesPlantBlock(AbstractBlock.Settings props) {
    super(props, Direction.DOWN, SHAPE, false);
    this.setDefaultState(this.stateDefinition.any());
  }

  @Override
  protected AbstractPlantStemBlock getHeadBlock() {
    return (AbstractPlantStemBlock) PVJBlocks.PINK_VINES;
  }

  @Override
  public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
    BlockPos blockpos = pPos.offset(this.growthDirection.getOpposite());
    BlockState blockstate = pLevel.getBlockState(blockpos);
    return blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) || blockstate.is(BlockTags.LEAVES);
  }

  @Override
  public void randomDisplayTick(BlockState state, World level, BlockPos pos, Random randomSource) {
    super.randomDisplayTick(state, level, pos, randomSource);
    if (randomSource.nextInt(10) == 0) {
      BlockPos blockpos = pos.down();
      BlockState blockstate = level.getBlockState(blockpos);
      if (!isFaceFullSquare(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
        ParticleUtil.spawnParticleBelow(level, pos, randomSource, ParticleTypes.CHERRY_LEAVES);
      }
    }
  }
}