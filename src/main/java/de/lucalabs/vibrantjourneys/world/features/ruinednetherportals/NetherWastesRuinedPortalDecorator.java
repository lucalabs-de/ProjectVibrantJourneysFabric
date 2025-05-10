package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Random;
import net.minecraft.world.level.StructureWorldAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

public class NetherWastesRuinedPortalDecorator extends RuinedPortalDecoratorBase {

  public NetherWastesRuinedPortalDecorator() {
    super("nether_wastes_ruined_portal_decorator");
  }

  @Nullable
  @Override
  public BlockState getTopSoil(StructureWorldAccess level, Random random) {
    float oreChance = random.nextFloat();
    if (oreChance <= 0.03F) {
      return Blocks.NETHER_QUARTZ_ORE.getDefaultState();
    } else if (oreChance <= 0.05F) {
      return Blocks.NETHER_GOLD_ORE.getDefaultState();
    }
    return null;
  }

  @Nullable
  @Override
  public BlockState getFillerSoil(StructureWorldAccess level, Random random) {
    float oreChance = random.nextFloat();
    if (oreChance <= 0.03F) {
      return Blocks.NETHER_QUARTZ_ORE.getDefaultState();
    } else if (oreChance <= 0.05F) {
      return Blocks.NETHER_GOLD_ORE.getDefaultState();
    }
    return null;
  }

  @Override
  public void decorate(StructureWorldAccess level, ChunkGenerator generator, Random random, BlockPos groundPos) {}
}
