package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class RocksGroundcoverFeature extends Feature<RandomPatchConfiguration> {

  public RocksGroundcoverFeature(Codec<RandomPatchConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
    Random randomSource = context.random();
    BlockPos origin = context.origin();
    StructureWorldAccess level = context.level();
    BlockState originState = level.getBlockState(origin);

    if (!originState.canReplace() ) {
      return false;
    }

    BlockState ground = level.getBlockState(origin.down());
    if (ground.is(Blocks.SCULK) || ground.is(Blocks.DEEPSLATE) || ground.is(Blocks.AMETHYST_BLOCK) || ground.is(dev.orderedchaos.projectvibrantjourneys.common.tags.PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)) {
      return false;
    }
    BlockState rocks = getRocksToPlace(randomSource, originState, origin, ground.getBlock());
    if (!rocks.canPlaceAt(level, origin)) {
      return false;
    } else {
      if (WorldUtils.setBlockState(level, origin, rocks, 2)) {
        if (level.getBlockState(origin.up()).hasProperty(Properties.DOUBLE_BLOCK_HALF) && level.getBlockState(origin.up()).get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
          level.removeBlock(origin.up(), false);
        }
        return true;
      }
    }
    return false;
  }

  private BlockState getRocksToPlace(Random randomSource, BlockState originState, BlockPos origin, Block ground) {
    Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
    int model = randomSource.nextInt(5);

    BlockState rocks = PVJBlocks.ROCKS.getDefaultState();

    if (ground == Blocks.RED_SAND || ground == Blocks.RED_SANDSTONE) {
      rocks = PVJBlocks.RED_SANDSTONE_ROCKS.getDefaultState();
    } else if (ground == Blocks.SAND || ground == Blocks.SANDSTONE) {
      rocks = PVJBlocks.SANDSTONE_ROCKS.getDefaultState();
    } else if (randomSource.nextFloat() < 0.2F && origin.getY() > 8) {
      rocks = PVJBlocks.MOSSY_ROCKS.getDefaultState();
    }
    rocks = rocks.with(GroundcoverBlock.FACING, dir).with(GroundcoverBlock.MODEL, model);

    if (originState.getFluidState().getType() == Fluids.WATER) {
      rocks = rocks.with(Properties.WATERLOGGED, true);
    }

    return rocks;
  }
}
