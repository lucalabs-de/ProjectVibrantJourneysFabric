package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import dev.orderedchaos.projectvibrantjourneys.util.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Random;
import net.minecraft.world.level.StructureWorldAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

public class SoulSandValleyRuinedPortalDecorator extends RuinedPortalDecoratorBase {

  public SoulSandValleyRuinedPortalDecorator() {
    super("soul_sand_valley_ruined_portal_decorator");
  }

  @Nullable
  @Override
  public BlockState getTopSoil(StructureWorldAccess level, Random random) {
    float oreChance = random.nextFloat();
    if (oreChance < 0.5F) {
      return Blocks.SOUL_SAND.getDefaultState();
    }
    return Blocks.SOUL_SOIL.getDefaultState();
  }

  @Nullable
  @Override
  public BlockState getFillerSoil(StructureWorldAccess level, Random random) {
    return Blocks.SOUL_SAND.getDefaultState();
  }

  @Override
  public void decorate(StructureWorldAccess level, ChunkGenerator generator, Random random, BlockPos groundPos) {
    float chance = random.nextFloat();
    if (chance < 0.15F) {
      level.setBlockState(groundPos.up(), Blocks.SOUL_FIRE.getDefaultState(), 2);
    } else if (chance < 0.18F) {
      int height = 3 + random.nextInt(3);
      boolean placeExtraBlock = false;
      for (int i = 1; i <= height; i++) {
        BlockPos pos = groundPos.up(i);
        if (LevelUtils.isEmptyOrReplaceable(level, pos)) {
          level.setBlockState(pos, Blocks.BONE_BLOCK.getDefaultState(), 2);
          if (i == height) { // if the loop completed without reaching break statement
            placeExtraBlock = random.nextBoolean();
          }
        } else {
          break;
        }
      }
      if (placeExtraBlock) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos pos = groundPos.up(height).offset(direction.getNormal()).up(random.nextBoolean() ? 1 : 0);
        if (LevelUtils.isEmptyOrReplaceable(level, pos)) {
          level.setBlockState(pos, Blocks.BONE_BLOCK.getDefaultState(), 2);
        }
      }
    }
  }
}
