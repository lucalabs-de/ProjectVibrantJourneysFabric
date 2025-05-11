package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

import java.util.function.Consumer;

public class IcicleFeature extends Feature<DefaultFeatureConfig> {

  private static final int MAX_LENGTH = 4;

  public IcicleFeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
    WorldAccess level = context.getWorld();
    BlockPos origin = context.getOrigin();
    Random random = context.getRandom();

    int height = checkVerticalSpace(level, origin, random.nextInt(4) + 1);
    if (height == 0)
      return false;

    growIcicle(level, origin, Direction.DOWN, height);


    return true;
  }

  private int checkVerticalSpace(WorldAccess level, BlockPos pos, int height) {
    Direction dir = Direction.DOWN;
    BlockPos.Mutable mutable = pos.mutable();
    int temp = 0;
    while (temp < height && level.isAir(mutable)) {
      temp++;
      mutable.move(dir);
    }

    return Math.min(temp, MAX_LENGTH);
  }

  private void buildBaseToTipColumn(Direction dir, int height, Consumer<BlockState> consumer) {
    if (height >= 3) {
      consumer.accept(createIcicle(dir, Thickness.BASE));

      for (int i = 0; i < height - 3; ++i) {
        consumer.accept(createIcicle(dir, Thickness.MIDDLE));
      }
    }

    if (height >= 2) {
      consumer.accept(createIcicle(dir, Thickness.FRUSTUM));
    }

    if (height >= 1) {
      consumer.accept(createIcicle(dir, Thickness.TIP));
    }

  }

  private void growIcicle(WorldAccess level, BlockPos pos, Direction dir, int height) {
    if (level.getBlockState(pos.offset(dir.getOpposite())).isCollisionShapeFullBlock(level, pos.offset(dir.getOpposite()))) {
      BlockPos.Mutable blockpos$mutableblockpos = pos.mutable();
      buildBaseToTipColumn(dir, height, (state) -> {
        WorldUtils.setBlockState(level, blockpos$mutableblockpos, state, 2);
        blockpos$mutableblockpos.move(dir);
      });
    }
  }

  private BlockState createIcicle(Direction dir, Thickness thickness) {
    return PVJBlocks.ICICLE.getDefaultState().with(PointedDripstoneBlock.TIP_DIRECTION, dir)
      .with(PointedDripstoneBlock.THICKNESS, thickness);
  }
}