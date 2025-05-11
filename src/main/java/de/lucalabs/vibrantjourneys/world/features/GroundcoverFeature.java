package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class GroundcoverFeature extends Feature<RandomPatchFeatureConfig> {
  public GroundcoverFeature(Codec<RandomPatchFeatureConfig> codec) {
    super(codec);
  }

  public boolean generate(FeatureContext<RandomPatchFeatureConfig> context) {
    RandomPatchFeatureConfig randompatchconfiguration = context.getConfig();
    Random randomsource = context.random();
    BlockPos blockpos = context.getOrigin();
    StructureWorldAccess worldgenlevel = context.level();
    int i = 0;
    BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
    int j = randompatchconfiguration.xzSpread() + 1;
    int k = randompatchconfiguration.ySpread() + 1;

    for(int l = 0; l < randompatchconfiguration.tries(); ++l) {
      blockpos$mutableblockpos.setWithOffset(blockpos, randomsource.nextInt(j) - randomsource.nextInt(j), randomsource.nextInt(k) - randomsource.nextInt(k), randomsource.nextInt(j) - randomsource.nextInt(j));
      if (!worldgenlevel.getBlockState(blockpos$mutableblockpos.down()).is(dev.orderedchaos.projectvibrantjourneys.common.tags.PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)) {
        if (randompatchconfiguration.feature().value().place(worldgenlevel, context.chunkGenerator(), randomsource, blockpos$mutableblockpos)) {
          if (worldgenlevel.getBlockState(blockpos$mutableblockpos.up()).hasProperty(Properties.DOUBLE_BLOCK_HALF) && worldgenlevel.getBlockState(blockpos$mutableblockpos.up()).get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
            worldgenlevel.removeBlock(blockpos$mutableblockpos.up(), false);
          }
          ++i;
        }
      }
    }

    return i > 0;
  }
}