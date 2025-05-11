package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class GlowingBlueFungusFeature extends Feature<DefaultFeatureConfig> {

  public GlowingBlueFungusFeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
    Random randomSource = context.getRandom();
    BlockPos origin = context.getOrigin();
    StructureWorldAccess level = context.getWorld();
    BlockPos.Mutable mutable = origin.mutable();
    GlowingFungusBlock blockToPlace = (GlowingFungusBlock) PVJBlocks.GLOWING_BLUE_FUNGUS;
    int count = 0;

    for (int i = -64; i < origin.getY(); i++) {
      mutable.set(origin);
      mutable.move(randomSource.nextInt(4) - randomSource.nextInt(4), 0, randomSource.nextInt(4) - randomSource.nextInt(4));
      mutable.setY(i);
      if (blockToPlace.canAttachTo(level, mutable, Direction.DOWN)) {
        boolean flag = false;
        while (!flag) {
          Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
          if (level.isAir(mutable.offset(dir.getNormal())) && level.getBlockState(mutable).isCollisionShapeFullBlock(level, mutable)) {
            if (level.setBlockState(mutable.offset(dir.getNormal()), blockToPlace.getDefaultState().with(HorizontalFacingBlock.FACING, dir), 2)) {
              count++;
            }
          }

          if (randomSource.nextFloat() < 0.8F) {
            flag = true;
          }
        }
      }
    }

    return count > 0;
  }
}
