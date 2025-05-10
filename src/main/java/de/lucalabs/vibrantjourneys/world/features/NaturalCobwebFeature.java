package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class NaturalCobwebFeature extends Feature<ProbabilityFeatureConfiguration> {
  public NaturalCobwebFeature(Codec<ProbabilityFeatureConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
    StructureWorldAccess world = context.level();
    BlockPos origin = context.origin();
    Random randomSource = context.random();
    BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(origin.getX(), origin.getY(), origin.getZ());

    for (int i = 64; i < origin.getY() + 50; i++) {
      blockpos.set(origin);
      blockpos.move(randomSource.nextInt(4) - randomSource.nextInt(4), 0, randomSource.nextInt(4) - randomSource.nextInt(4));
      blockpos.setY(i);

      if (world.getBlockState(blockpos).getBlock() instanceof LeavesBlock) {
        if (world.isAir(blockpos.down())) {
          if (randomSource.nextFloat() < context.config().probability) {
            return LevelUtils.setBlockState(world, blockpos.down(), PVJBlocks.NATURAL_COBWEB.getDefaultState(), 2);
          }
        }
      }
    }

    return true;
  }
}