package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class MultipleWaterloggedVegetationPatchFeature extends Feature<MultipleVegetationPatchConfiguration> {

  public MultipleWaterloggedVegetationPatchFeature(Codec<MultipleVegetationPatchConfiguration> codec) {
    super(codec);
  }

  @Override
  public boolean place(FeaturePlaceContext<MultipleVegetationPatchConfiguration> context) {
    StructureWorldAccess worldgenlevel = context.level();
    MultipleVegetationPatchConfiguration config = context.config();
    Random random = context.random();

    if (random.nextFloat() > config.placementChance)
      return false;

    BlockPos blockpos = context.origin();
    Predicate<BlockState> predicate = (p_204782_) -> {
      return p_204782_.is(config.replaceable);
    };
    int i = config.xzRadius.sample(random) + 1;
    int j = config.xzRadius.sample(random) + 1;
    Set<BlockPos> set = this.placeGroundPatch(worldgenlevel, config, random, blockpos, predicate, i, j);
    this.distributeVegetation(context, worldgenlevel, config, random, set, i, j);

    return !set.isEmpty();
  }

  protected Set<BlockPos> placeGroundPatch(StructureWorldAccess level, MultipleVegetationPatchConfiguration config, Random rand, BlockPos pos, Predicate<BlockState> replace, int x, int z) {
    Set<BlockPos> set = createGround(level, config, rand, pos, replace, x, z);
    Set<BlockPos> set1 = new HashSet<>();
    BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

    for (BlockPos blockpos : set) {
      if (!isExposed(level, set, blockpos, blockpos$mutableblockpos)) {
        set1.add(blockpos);
      }
    }

    for (BlockPos blockpos1 : set1) {
      WorldUtils.setBlockState(level, blockpos1, Blocks.WATER.getDefaultState(), 2);
    }

    return set1;
  }

  private static boolean isExposed(StructureWorldAccess level, Set<BlockPos> set, BlockPos pos, BlockPos.Mutable posMutable) {
    return isExposedDirection(level, pos, posMutable, Direction.NORTH)
      || isExposedDirection(level, pos, posMutable, Direction.EAST)
      || isExposedDirection(level, pos, posMutable, Direction.SOUTH)
      || isExposedDirection(level, pos, posMutable, Direction.WEST)
      || isExposedDirection(level, pos, posMutable, Direction.DOWN);
  }

  private static boolean isExposedDirection(StructureWorldAccess level, BlockPos pos, BlockPos.Mutable posMutable, Direction dir) {
    posMutable.setWithOffset(pos, dir);
    return !level.getBlockState(posMutable).isFaceSturdy(level, posMutable, dir.getOpposite());
  }

  protected Set<BlockPos> createGround(StructureWorldAccess level, MultipleVegetationPatchConfiguration config, Random rand, BlockPos pos, Predicate<BlockState> predicate, int x, int z) {
    Set<BlockPos> set = new HashSet<>();

    carve(set, level, config, rand, pos, predicate, x, z, true);
    carve(set, level, config, rand, pos.down(), predicate, x - 1, z - 1, false);

    return set;
  }

  private void carve(Set<BlockPos> set, StructureWorldAccess level, MultipleVegetationPatchConfiguration config, Random rand, BlockPos pos, Predicate<BlockState> predicate, int x, int z, boolean surface) {
    BlockPos.Mutable blockpos$mutableblockpos = pos.mutable();
    BlockPos.Mutable blockpos$mutableblockpos1 = blockpos$mutableblockpos.mutable();
    Direction direction = config.surface.getDirection();
    Direction direction1 = direction.getOpposite();

    for (int i = -x; i <= x; ++i) {
      boolean flag = i == -x || i == x;

      for (int j = -z; j <= z; ++j) {
        boolean flag1 = j == -z || j == z;
        boolean flag2 = flag || flag1;
        boolean flag3 = flag && flag1;
        boolean flag4 = flag2 && !flag3;
        if (!flag3 && (!flag4 || config.extraEdgeColumnChance != 0.0F && !(rand.nextFloat() > config.extraEdgeColumnChance))) {
          blockpos$mutableblockpos.setWithOffset(pos, i, 0, j);

          for (int k = 0; (surface ? level.isStateAtPosition(blockpos$mutableblockpos, AbstractBlock.BlockStateBase::isAir) : true) && k < config.verticalRange; ++k) {
            blockpos$mutableblockpos.move(direction);
          }

          for (int i1 = 0; level.isStateAtPosition(blockpos$mutableblockpos, (p_204784_) -> {
            return !p_204784_.isAir();
          }) && i1 < config.verticalRange; ++i1) {
            blockpos$mutableblockpos.move(direction1);
          }

          blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, config.surface.getDirection());
          BlockState blockstate = level.getBlockState(blockpos$mutableblockpos1);
          if (blockstate.isFaceSturdy(level, blockpos$mutableblockpos1, config.surface.getDirection().getOpposite())) {
            int l = config.depth.sample(rand) + (config.extraBottomBlockChance > 0.0F && rand.nextFloat() < config.extraBottomBlockChance ? 1 : 0);
            BlockPos blockpos = blockpos$mutableblockpos1.immutable();
            boolean flag5 = this.placeGround(level, config, predicate, rand, blockpos$mutableblockpos1, l);
            if (flag5) {
              set.add(blockpos);
            }
          }
        }
      }
    }
  }

  //TODO: move hardcoded vegetation to config for flexibility (may re-use this feature for other things who knows)
  protected void distributeVegetation(FeaturePlaceContext<MultipleVegetationPatchConfiguration> context, StructureWorldAccess level, MultipleVegetationPatchConfiguration config, Random rand, Set<BlockPos> set, int p_160619_, int p_160620_) {
    for (BlockPos blockpos : set) {
      if (config.vegetationChance > 0.0F && rand.nextFloat() < config.vegetationChance) {
        this.placeVegetation(level, config, context.chunkGenerator(), rand, blockpos);

        if (rand.nextInt(20) == 0) {
          for (int i = 0; i < 32; ++i) {
            BlockPos pos = blockpos;
            BlockState blockstate = Blocks.SEAGRASS.getDefaultState();

            for (int j = 0; j < i / 16; ++j) {
              pos = pos.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

              if (level.getBlockState(pos.down()).isCollisionShapeFullBlock(level, pos.down())) {
                if (blockstate.canPlaceAt(level, pos)) {
                  BlockState blockstate1 = level.getBlockState(pos);
                  if (blockstate1.is(Blocks.WATER) && level.getFluidState(pos).getAmount() == 8) {
                    WorldUtils.setBlockState(level, pos, blockstate, 3);
                  } else if (blockstate1.is(Blocks.KELP) && rand.nextBoolean()) {
                    int l = Math.min(blockstate1.get(AbstractPlantStemBlock.AGE) + 1, 25);
                    if (level.getBlockState(pos.up()).getFluidState().getType() == Fluids.WATER) {
                      WorldUtils.setBlockState(level, pos, blockstate1.with(AbstractPlantStemBlock.AGE, Integer.valueOf(l)), 3);
                    }
                  } else if (blockstate1.is(Blocks.SEAGRASS) && rand.nextInt(3) == 0) {
                    if (level.getBlockState(pos.up()).getFluidState().getType() == Fluids.WATER) {
                      WorldUtils.setBlockState(level, pos, Blocks.TALL_SEAGRASS.getDefaultState(), 3);
                    }
                  }
                }
              }
            }
          }
        }


      } else {
        if (level.getFluidState(blockpos).getType() == Fluids.WATER && level.getBlockState(blockpos).getBlock() == Blocks.WATER && rand.nextFloat() < 0.25F) {
          tryPlaceCoral(level, blockpos, rand);
        }
      }
    }
  }

  private void tryPlaceCoral(StructureWorldAccess level, BlockPos pos, Random rand) {
    if (level.getBlockState(pos.down()).isCollisionShapeFullBlock(level, pos.down())) {
      if (rand.nextBoolean()) {
        Optional<Block> coral = ForgeRegistries.BLOCKS.tags().getTag(BlockTags.CORALS).getRandomElement(rand);
        if (coral.isPresent()) {
          Block block = coral;
          WorldUtils.setBlockState(level, pos, block.getDefaultState(), 2);
        }
      }
    } else {
      for (Direction direction : Direction.Type.HORIZONTAL) {
        if (rand.nextBoolean()) {
          if (level.getBlockState(pos.offset(direction)).isCollisionShapeFullBlock(level, pos.offset(direction))) {
            Optional<Block> coral = ForgeRegistries.BLOCKS.tags().getTag(BlockTags.WALL_CORALS).getRandomElement(rand);
            if (coral.isPresent()) {
              BlockState blockstate = coral.getDefaultState();
              if (blockstate.hasProperty(BaseCoralWallFanBlock.FACING)) {
                blockstate = blockstate.with(BaseCoralWallFanBlock.FACING, direction.getOpposite());
              }
              WorldUtils.setBlockState(level, pos, blockstate, 2);
            }
          }
        }
      }
    }
  }

  protected boolean placeVegetation(StructureWorldAccess level, MultipleVegetationPatchConfiguration config, ChunkGenerator chunkGenerator, Random rand, BlockPos pos) {
    boolean flag = false;

    for (RegistryEntry<PlacedFeature> feature : config.vegetationFeature) {
      if (feature.value().place(level, chunkGenerator, rand, pos.down().offset(config.surface.getDirection().getOpposite()))) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.hasProperty(Properties.WATERLOGGED) && !blockstate.get(Properties.WATERLOGGED)) {
          WorldUtils.setBlockState(level, pos, blockstate.with(Properties.WATERLOGGED, Boolean.valueOf(true)), 2);
        }
        flag = true;
      }
    }

    return flag;
  }

  protected boolean placeGround(StructureWorldAccess level, MultipleVegetationPatchConfiguration config, Predicate<BlockState> pred, Random rand, BlockPos.Mutable pos, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      BlockState blockstate = config.groundState.getState(rand, pos);
      BlockState blockstate1 = level.getBlockState(pos);
      if (!blockstate.is(blockstate1.getBlock())) {
        if (!pred.test(blockstate1)) {
          return i != 0;
        }

        WorldUtils.setBlockState(level, pos, blockstate, 2);
        pos.move(config.surface.getDirection());
      }
    }

    return true;
  }
}