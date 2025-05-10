package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class BushFeature extends Feature<BushConfiguration> {
  public BushFeature(Codec<BushConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<BushConfiguration> context) {
    StructureWorldAccess world = context.level();
    BlockPos origin = context.origin();

    if (world.isAir(origin)) {
      if (world.getBlockState(origin.down()).is(Blocks.GRASS_BLOCK) || world.getBlockState(origin.down()).is(BlockTags.DIRT)) {
        for (Direction dir : Direction.Type.HORIZONTAL) {
          if (!world.getBlockState(origin.offset(dir.getNormal())).canReplace()) {
            return false;
          }
        }
        world.setBlockState(origin, context.config().log(), 2);
        for (Direction dir : Direction.Type.HORIZONTAL) {
          world.setBlockState(origin.offset(dir.getNormal()), context.config().leaves().with(LeavesBlock.DISTANCE, 1), 2);
        }
        world.setBlockState(origin.up(), context.config().leaves().with(LeavesBlock.DISTANCE, 1), 2);
      }
    }
    return true;
  }


}