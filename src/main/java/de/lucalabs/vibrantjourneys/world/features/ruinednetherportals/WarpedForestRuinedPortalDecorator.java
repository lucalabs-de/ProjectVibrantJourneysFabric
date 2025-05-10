package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import dev.orderedchaos.projectvibrantjourneys.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WarpedForestRuinedPortalDecorator extends RuinedPortalDecoratorBase {

  public WarpedForestRuinedPortalDecorator() {
    super("warped_forest_ruined_portal_decorator");
  }

  @Nullable
  @Override
  public BlockState getTopSoil(StructureWorldAccess level, Random random) {
    float chance = random.nextFloat();
    if (chance <= 0.75F) {
      return Blocks.WARPED_NYLIUM.getDefaultState();
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
  public void decorate(StructureWorldAccess level, ChunkGenerator generator, Random random, BlockPos groundPos) {
    BlockPos pos = groundPos.up();
    if (WorldUtils.isEmptyOrReplaceable(level, pos)) {
      float chance = random.nextFloat();
      if (chance < 0.02F) {
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> bigMushroom = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(TreeFeatures.WARPED_FUNGUS);
        bigMushroom.ifPresent((feature) -> feature.value().place(level, generator, random, pos));
      } else if (chance < 0.15F) {
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> warpedForestVegetation = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(NetherFeatures.WARPED_FOREST_VEGETION);
        warpedForestVegetation.ifPresent((feature) -> feature.value().place(level, generator, random, pos));
      } else if (chance < 0.16F) {
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> twistingVines = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(NetherFeatures.TWISTING_VINES_BONEMEAL);
        twistingVines.ifPresent((feature) -> feature.value().place(level, generator, random, pos));
      }
    }
  }
}
