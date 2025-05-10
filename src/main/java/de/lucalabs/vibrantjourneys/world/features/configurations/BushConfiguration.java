package de.lucalabs.vibrantjourneys.world.features.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public record BushConfiguration(BlockState log, BlockState leaves) implements FeatureConfig {
  public static final Codec<BushConfiguration> CODEC = RecordCodecBuilder.create(builder -> builder.group(
    BlockState.CODEC.fieldOf("log").forGetter(BushConfiguration::log),
    BlockState.CODEC.fieldOf("leaves").forGetter(BushConfiguration::leaves)
  ).apply(builder, BushConfiguration::new));
}
