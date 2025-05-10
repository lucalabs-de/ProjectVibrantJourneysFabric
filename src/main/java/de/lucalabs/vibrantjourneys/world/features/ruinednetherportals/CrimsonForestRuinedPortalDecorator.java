package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import dev.orderedchaos.projectvibrantjourneys.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.Random;
import net.minecraft.world.level.StructureWorldAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrimsonForestRuinedPortalDecorator extends RuinedPortalDecoratorBase {

  public CrimsonForestRuinedPortalDecorator() {
    super("crimson_forest_ruined_portal_decorator");
  }

  @Nullable
  @Override
  public BlockState getTopSoil(StructureWorldAccess level, Random random) {
    float chance = random.nextFloat();
    if (chance <= 0.75F) {
      return Blocks.CRIMSON_NYLIUM.getDefaultState();
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
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> bigMushroom = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(TreeFeatures.CRIMSON_FUNGUS);
        bigMushroom.ifPresent((feature) -> feature.value().place(level, generator, random, pos));
      } else if (chance < 0.15F) {
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> warpedForestVegetation = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(NetherFeatures.CRIMSON_FOREST_VEGETATION);
        warpedForestVegetation.ifPresent((feature) -> feature.value().place(level, generator, random, pos));
      } else if (chance < 0.25F) {
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> twistingVines = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(NetherFeatures.WEEPING_VINES);
        twistingVines.ifPresent((feature) -> feature.value().place(level, generator, random, pos));
      }
    }
  }
}
