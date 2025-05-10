package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class BarkMushroomFeature extends Feature<NoneFeatureConfiguration> {

  public BarkMushroomFeature(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
    Random randomSource = context.random();
    BlockPos origin = context.origin();
    StructureWorldAccess level = context.level();
    BlockPos.Mutable mutable = origin.mutable();
    BarkMushroomBlock blockToPlace = BarkMushroomBlock.getRandom(randomSource);
    int count = 0;

    for (int i = 0; i < origin.getY() + 50; i++) {
      mutable.set(origin);
      mutable.move(randomSource.nextInt(4) - randomSource.nextInt(4), 0, randomSource.nextInt(4) - randomSource.nextInt(4));
      mutable.setY(i);
      if (blockToPlace.canAttachTo(level, mutable, Direction.DOWN)) {
        boolean flag = false;
        while (!flag) {
          Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
          if (level.isAir(mutable.offset(dir.getNormal())) && level.getBlockState(mutable).isCollisionShapeFullBlock(level, mutable)) {
            if (WorldUtils.setBlockState(level, mutable.offset(dir.getNormal()), blockToPlace.getDefaultState().with(HorizontalFacingBlock.FACING, dir), 2)) {
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
