package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NetherConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
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
                Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> bigMushroom = level.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(TreeConfiguredFeatures.CRIMSON_FUNGUS);
                bigMushroom.ifPresent((feature) -> feature.value().generate(level, generator, random, pos));
            } else if (chance < 0.15F) {
                Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> warpedForestVegetation = level.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(NetherConfiguredFeatures.CRIMSON_FOREST_VEGETATION);
                warpedForestVegetation.ifPresent((feature) -> feature.value().generate(level, generator, random, pos));
            } else if (chance < 0.25F) {
                Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> twistingVines = level.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(NetherConfiguredFeatures.WEEPING_VINES);
                twistingVines.ifPresent((feature) -> feature.value().generate(level, generator, random, pos));
            }
        }
    }
}
