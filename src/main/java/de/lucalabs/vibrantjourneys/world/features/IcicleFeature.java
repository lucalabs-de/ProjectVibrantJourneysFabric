package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

import java.util.function.Consumer;

public class IcicleFeature extends Feature<NoneFeatureConfiguration> {

  private static final int MAX_LENGTH = 4;

  public IcicleFeature(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
    WorldAccess level = context.level();
    BlockPos origin = context.origin();
    Random random = context.random();

    int height = checkVerticalSpace(level, origin, random.nextInt(4) + 1);
    if (height == 0)
      return false;

    growIcicle(level, origin, Direction.DOWN, height);


    return true;
  }

  private int checkVerticalSpace(WorldAccess level, BlockPos pos, int height) {
    Direction dir = Direction.DOWN;
    BlockPos.MutableBlockPos mutable = pos.mutable();
    int temp = 0;
    while (temp < height && level.isAir(mutable)) {
      temp++;
      mutable.move(dir);
    }

    return Math.min(temp, MAX_LENGTH);
  }

  private void buildBaseToTipColumn(Direction dir, int height, Consumer<BlockState> consumer) {
    if (height >= 3) {
      consumer.accept(createIcicle(dir, DripstoneThickness.BASE));

      for (int i = 0; i < height - 3; ++i) {
        consumer.accept(createIcicle(dir, DripstoneThickness.MIDDLE));
      }
    }

    if (height >= 2) {
      consumer.accept(createIcicle(dir, DripstoneThickness.FRUSTUM));
    }

    if (height >= 1) {
      consumer.accept(createIcicle(dir, DripstoneThickness.TIP));
    }

  }

  private void growIcicle(WorldAccess level, BlockPos pos, Direction dir, int height) {
    if (level.getBlockState(pos.relative(dir.getOpposite())).isCollisionShapeFullBlock(level, pos.relative(dir.getOpposite()))) {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
      buildBaseToTipColumn(dir, height, (state) -> {
        LevelUtils.setBlockState(level, blockpos$mutableblockpos, state, 2);
        blockpos$mutableblockpos.move(dir);
      });
    }
  }

  private BlockState createIcicle(Direction dir, DripstoneThickness thickness) {
    return PVJBlocks.ICICLE.getDefaultState().with(PointedDripstoneBlock.TIP_DIRECTION, dir)
      .with(PointedDripstoneBlock.THICKNESS, thickness);
  }
}