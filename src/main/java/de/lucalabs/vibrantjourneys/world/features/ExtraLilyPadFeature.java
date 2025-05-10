package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class ExtraLilyPadFeature extends Feature<ProbabilityFeatureConfiguration> {

  public ExtraLilyPadFeature(Codec<ProbabilityFeatureConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
    ProbabilityFeatureConfiguration config = context.config();
    StructureWorldAccess level = context.level();
    BlockPos origin = context.origin();
    BlockState blockstate = Blocks.LILY_PAD.getDefaultState();

    if (level.getBlockState(origin.down()).is(Blocks.ICE)) {
      return false;
    }

    if (context.random().nextFloat() < config.probability && level.getBlockState(origin.down()).is(Blocks.WATER)) {
      int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, origin.getX(), origin.getZ());
      int oceanFloorY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.getX(), origin.getZ());
      int waterDepth = surfaceY - oceanFloorY;

      if (waterDepth <= 3) {
        return WorldUtils.setBlockState(level, origin, blockstate, 2);
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}