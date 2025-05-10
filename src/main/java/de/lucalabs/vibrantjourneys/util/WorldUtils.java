package de.lucalabs.vibrantjourneys.util;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.mixin.ChunkRegionAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;

public class WorldUtils {
  public static boolean setBlockState(final WorldAccess level, final BlockPos position, final BlockState blockState, int parameter) {
    if (!isValid(level, position, blockState)) {
      return false;
    }

    return level.setBlockState(position, blockState, parameter);
  }

  public static boolean isEmptyOrReplaceable(ServerWorldAccess level, BlockPos pos) {
    return level.isAir(pos) || level.getBlockState(pos).isIn(BlockTags.REPLACEABLE);
  }

  public static boolean setBlockState(final WorldAccess level, final BlockPos position, final BlockState blockState) {
    if (level instanceof ServerWorld serverLevel) {
      return serverLevel.setBlockState(position, blockState);
    }

    return false;
  }

  private static boolean isValid(final WorldAccess level, final BlockPos position, final BlockState blockState) {
    boolean respectsCutoff = respectsCutoff(level, position, blockState);

    if (!respectsCutoff) {
        ProjectVibrantJourneys.LOGGER.debug("Skipped placing feature ({}) [respectsCutoff: {}]", blockState.getBlock().getTranslationKey(), false);
    }

    return respectsCutoff;
  }

  private static boolean respectsCutoff(final WorldAccess level, final BlockPos position, BlockState blockState) {
    if (level instanceof ChunkRegion region) {
      int x = ChunkSectionPos.getSectionCoord(position.getX());
      int z = ChunkSectionPos.getSectionCoord(position.getZ());

      ChunkPos chunkpos = region.getCenterPos();
      int xResult = Math.abs(chunkpos.x - x);
      int zResult = Math.abs(chunkpos.z - z);

//            ProjectVibrantJourneys.LOGGER.debug("Feature: " + blockState.getBlock().getDescriptionId() + " x: " + x + " | z: " + z + " | xResult: " + xResult + " | zResult: " + zResult + " | chunkPos: " + chunkpos + " | writeRadiusCutoff: " + region.writeRadiusCutoff);

      return xResult <= ((ChunkRegionAccessor) region).getPlacementRadius()
              && zResult <= ((ChunkRegionAccessor) region).getPlacementRadius();
    } else {
      return false;
      // Has not reached this point yet
//            ProjectVibrantJourneys.LOGGER.warn("WorldGenRegion is not being used to place features, instead it is: " + level.getClass());
    }
  }
}
