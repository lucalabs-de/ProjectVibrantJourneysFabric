package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class SimpleBlockMatchWaterFeature extends Feature<SimpleBlockConfiguration> {

  public SimpleBlockMatchWaterFeature(Codec<SimpleBlockConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
    boolean placed = false;

    SimpleBlockConfiguration config = context.config();
    StructureWorldAccess level = context.level();
    BlockPos origin = context.origin();
    BlockState state = config.toPlace().getState(context.random(), origin);

    if (state.canPlaceAt(level, origin)) {
      if (state.getBlock() instanceof TallPlantBlock) {
        if (!level.isAir(origin.up())) {
          return false;
        }

        TallPlantBlock.placeAt(level, state, origin, 2);
      } else {
        if (state.getBlock() instanceof Waterloggable) {
          if (level.isFluidAtPosition(origin, (fluidstate) -> fluidstate.getType() == Fluids.WATER)) {
            placed = LevelUtils.setBlockState(level, origin, state.with(Properties.WATERLOGGED, true), 2);
          } else {
            placed = LevelUtils.setBlockState(level, origin, state, 2);
          }
        } else {
          placed = LevelUtils.setBlockState(level, origin, state, 2);
        }
      }

      return placed;
    } else {
      return false;
    }
  }
}