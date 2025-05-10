package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class MuddyBonesFeature extends Feature<NoneFeatureConfiguration> {
  public MuddyBonesFeature(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
    StructureWorldAccess world = context.level();
    BlockPos origin = context.origin();
    Random randomSource = context.random();

    Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
    int model = randomSource.nextInt(3);

    if (world.getBlockState(origin).is(Blocks.MUD)) {
      BlockState state = PVJBlocks.MUDDY_BONES.getDefaultState().with(MuddyBonesBlock.FACING, dir).with(MuddyBonesBlock.MODEL, model);
      if(world.setBlockState(origin, state, 2)) {
        ProjectVibrantJourneys.LOGGER.info(origin.toString());
      }
    }

    return true;
  }


}