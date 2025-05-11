package de.lucalabs.vibrantjourneys.world.modifiers;

import com.mojang.serialization.Codec;

import java.util.ArrayList;
import java.util.List;

public record PVJBiomeModifier(
  TagKey<Biome> dimension,
  List<HolderSet<Biome>> biomes,
  List<HolderSet<Biome>> blacklist,
  GenerationStep.Decoration decoration,
  RegistryEntry<PlacedFeature> feature,
  String configOption
) implements BiomeModifier {

  @Override
  public void modify(RegistryEntry<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    if (phase == Phase.AFTER_EVERYTHING && PVJConfig.configOptions.get(configOption)) {
      if (biome.is(dimension)) {
        boolean flag = false;

        for (HolderSet<Biome> set : biomes) {
          if (set.contains(biome)) {
            flag = true;
            break;
          }
        }

        if (flag) {
          for (HolderSet<Biome> set : blacklist) {
            if (set.contains(biome)) {
              return;
            }
          }

          builder.getGenerationSettings().addFeature(decoration, feature);
        }
      }
    }
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return PVJBiomeModifiers.BIOME_MODIFIER_SERIALIZER;
  }

  public static class Builder {

    public enum Dimension {
      OVERWORLD,
      NETHER,
      END
    }

    private HolderGetter<PlacedFeature> placedFeatureGetter;
    private HolderGetter<Biome> biomeGetter;
    private TagKey<Biome> dimension = BiomeTags.IS_OVERWORLD;
    private HolderSet.Named<Biome> namedTags;
    private List<TagKey<Biome>> biomeTags = new ArrayList<>();
    private List<TagKey<Biome>> blacklistTags = new ArrayList<>();
    private List<RegistryEntry<Biome>> extraBiomes = new ArrayList<>();
    private List<RegistryEntry<Biome>> extraBlacklist = new ArrayList<>();
    private GenerationStep.Decoration decoration = GenerationStep.Decoration.VEGETAL_DECORATION;
    private final String configOption;
    private RegistryKey<PlacedFeature> placedFeatureKey;

    public Builder(BootstapContext<BiomeModifier> context, String configOption) {
      this.placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
      this.biomeGetter = context.lookup(Registries.BIOME);
      this.configOption = configOption;
    }

    public Builder placedFeature(RegistryKey<PlacedFeature> placedFeatureKey) {
      this.placedFeatureKey = placedFeatureKey;
      return this;
    }

    public Builder dimension(Dimension dim) {
      TagKey<Biome> tagKey = BiomeTags.IS_OVERWORLD;
      switch(dim) {
        case OVERWORLD -> tagKey = BiomeTags.IS_OVERWORLD;
        case NETHER -> tagKey = BiomeTags.IS_OVERWORLD;
        case END -> tagKey = BiomeTags.IS_END;
      }

      this.dimension = tagKey;
      return this;
    }

    public Builder decoration(GenerationStep.Decoration decoration) {
      this.decoration = decoration;
      return this;
    }

    public Builder tag(List<TagKey<Biome>> tags) {
      this.biomeTags.addAll(combine(tags));
      return this;
    }

    public Builder blacklist(List<TagKey<Biome>>... tags) {
      this.blacklistTags.addAll(combine(tags));
      return this;
    }

    public Builder extraBiomes(List<RegistryEntry<Biome>>... biomes) {
      this.extraBiomes.addAll(combine(biomes));
      return this;
    }

    public Builder extraBiomes(RegistryKey<Biome>... biomes) {
      for(RegistryKey<Biome> biome : biomes) {
        RegistryEntry<Biome> biomeHolder = biomeGetter.getOrThrow(biome);
        this.extraBiomes.add(biomeHolder);
      }

      return this;
    }

    public Builder biomes(HolderSet.Named<Biome> holders) {
      namedTags = holders;
      return this;
    }

    public Builder extraBlacklist(List<RegistryEntry<Biome>>... biomes) {
      this.extraBlacklist.addAll(combine(biomes));
      return this;
    }

    public Builder extraBlacklist(RegistryKey<Biome>... biomes) {
      for(RegistryKey<Biome> biome : biomes) {
        RegistryEntry<Biome> biomeHolder = biomeGetter.getOrThrow(biome);
        this.extraBlacklist.add(biomeHolder);
      }
      return this;
    }

    public Builder extraBlacklist(List<RegistryKey<Biome>> biomes) {
      for(RegistryKey<Biome> biome : biomes) {
        RegistryEntry<Biome> biomeHolder = biomeGetter.getOrThrow(biome);
        this.extraBlacklist.add(biomeHolder);
      }
      return this;
    }

    @SafeVarargs
    public final <T> List<T> combine(List<T>... lists) {
      List<T> list = new ArrayList<>();
      for(List<T> i : lists)
        for(T tag : i)
          list.add(tag);

      return list;
    }

    public PVJBiomeModifier build() {
      List<HolderSet<Biome>> biomesSet = new ArrayList<>(this.biomeTags.stream().map(tag -> biomeGetter.getOrThrow(tag)).toList());
      List<HolderSet<Biome>> blacklistSet = new ArrayList<>(this.blacklistTags.stream().map(tag -> biomeGetter.getOrThrow(tag)).toList());

      if (!this.extraBiomes.isEmpty()) {
        biomesSet.add(HolderSet.direct(this.extraBiomes));
      }

      if (namedTags != null) {
        biomesSet.add(this.namedTags);
      }

      if (!extraBlacklist.isEmpty()) {
        blacklistSet.add(HolderSet.direct(this.extraBlacklist));
      }
      RegistryEntry<PlacedFeature> placedFeature = placedFeatureGetter.getOrThrow(placedFeatureKey);

      return new PVJBiomeModifier(dimension, biomesSet, blacklistSet, decoration, placedFeature, configOption);
    }
  }
}