package de.lucalabs.vibrantjourneys.world.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class MultipleVegetationPatchConfiguration implements FeatureConfig {

    public static final Codec<MultipleVegetationPatchConfiguration> CODEC =
            RecordCodecBuilder.create((instance) -> instance.group(
                            TagKey.codec(RegistryKeys.BLOCK).fieldOf("replaceable").forGetter((config) -> config.replaceable),
                            BlockStateProvider.TYPE_CODEC.fieldOf("ground_state").forGetter((config) -> config.groundState),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("placement_chance").forGetter((config) -> config.placementChance),
                            RegistryKey.createCodec(RegistryKeys.CONFIGURED_FEATURE).listOf().fieldOf("vegetation_feature").forGetter((config) -> config.vegetationFeature),
                            VerticalSurfaceType.CODEC.fieldOf("surface").forGetter((config) -> config.surface),
                            IntProvider.createValidatingCodec(1, 128).fieldOf("depth").forGetter((config) -> config.depth),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("extra_bottom_block_chance").forGetter((config) -> config.extraBottomBlockChance),
                            Codec.intRange(1, 256).fieldOf("vertical_range").forGetter((config) -> config.verticalRange),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("vegetation_chance").forGetter((config) -> config.vegetationChance),
                            IntProvider.VALUE_CODEC.fieldOf("xz_radius").forGetter((config) -> config.xzRadius),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("extra_edge_column_chance").forGetter((config) -> config.extraEdgeColumnChance))
                    .apply(instance, MultipleVegetationPatchConfiguration::new));

    public final TagKey<Block> replaceable;
    public final BlockStateProvider groundState;
    public final float placementChance;
    public final List<RegistryKey<ConfiguredFeature<?, ?>>> vegetationFeature;
    public final VerticalSurfaceType surface;
    public final IntProvider depth;
    public final float extraBottomBlockChance;
    public final int verticalRange;
    public final float vegetationChance;
    public final IntProvider xzRadius;
    public final float extraEdgeColumnChance;

    public MultipleVegetationPatchConfiguration(
            TagKey<Block> replace,
            BlockStateProvider provider,
            float placementChance,
            List<RegistryKey<ConfiguredFeature<?, ?>>> feature,
            VerticalSurfaceType surface,
            IntProvider depth,
            float extraBottomChance,
            int verticalRange,
            float vegetationChance,
            IntProvider xzRadius,
            float extraEdgeColumnChance) {
        this.replaceable = replace;
        this.groundState = provider;
        this.placementChance = placementChance;
        this.vegetationFeature = feature;
        this.surface = surface;
        this.depth = depth;
        this.extraBottomBlockChance = extraBottomChance;
        this.verticalRange = verticalRange;
        this.vegetationChance = vegetationChance;
        this.xzRadius = xzRadius;
        this.extraEdgeColumnChance = extraEdgeColumnChance;
    }
}