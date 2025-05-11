package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class SimpleBlockMatchWaterFeature extends Feature<SimpleBlockFeatureConfig> {

  public SimpleBlockMatchWaterFeature(Codec<SimpleBlockFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(FeatureContext<SimpleBlockFeatureConfig> context) {
    boolean placed = false;

    SimpleBlockFeatureConfig config = context.getConfig();
    StructureWorldAccess level = context.getWorld();
    BlockPos origin = context.getOrigin();
    BlockState state = config.toPlace().getState(context.getRandom(), origin);

    if (state.canPlaceAt(level, origin)) {
      if (state.getBlock() instanceof TallPlantBlock) {
        if (!level.isAir(origin.up())) {
          return false;
        }

        TallPlantBlock.placeAt(level, state, origin, 2);
      } else {
        if (state.getBlock() instanceof Waterloggable) {
          if (level.isFluidAtPosition(origin, (fluidstate) -> fluidstate.getType() == Fluids.WATER)) {
            placed = WorldUtils.setBlockState(level, origin, state.with(Properties.WATERLOGGED, true), 2);
          } else {
            placed = WorldUtils.setBlockState(level, origin, state, 2);
          }
        } else {
          placed = WorldUtils.setBlockState(level, origin, state, 2);
        }
      }

      return placed;
    } else {
      return false;
    }
  }
}