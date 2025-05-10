package de.lucalabs.vibrantjourneys.world.modifiers;

import com.mojang.serialization.Codec;

public record PVJSpawnModifier(TagKey<Biome> dimension, HolderSet<Biome> biomes, MobCategory category,
                               MobSpawnSettings.SpawnerData data, String configOption) implements BiomeModifier {

  @Override
  public void modify(RegistryEntry<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    if (phase == Phase.ADD) {
      if (PVJConfig.configOptions.get(configOption)) {
        if (biome.is(dimension)) {
          if (biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(category, data);
          }
        }
      }
    }
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return PVJBiomeModifiers.SPAWN_MODIFIER_SERIALIZER;
  }


}