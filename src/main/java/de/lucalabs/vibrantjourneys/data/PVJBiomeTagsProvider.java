package de.lucalabs.vibrantjourneys.data;

import java.util.concurrent.CompletableFuture;

public class PVJBiomeTagsProvider extends BiomeTagsProvider {
  public PVJBiomeTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
    super(packOutput, provider, ProjectVibrantJourneys.MOD_ID, helper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.OAK) {
      addTag(PVJTags.HAS_OAK_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.BIRCH) {
      addTag(PVJTags.HAS_BIRCH_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.SPRUCE) {
      addTag(PVJTags.HAS_SPRUCE_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.JUNGLE) {
      addTag(PVJTags.HAS_JUNGLE_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.ACACIA) {
      addTag(PVJTags.HAS_ACACIA_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.DARK_OAK) {
      addTag(PVJTags.HAS_DARK_OAK_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.CHERRY) {
      addTag(PVJTags.HAS_CHERRY_LOGS, biome);
    }
    for (TreeFeatureUtils.ChanceBiomeEntry biome : PVJFeatureVars.MANGROVE) {
      addTag(PVJTags.HAS_MANGROVE_LOGS, biome);
    }
  }

  private void addTag(final TagKey<Biome> tagKey, final TreeFeatureUtils.ChanceBiomeEntry biomeEntry) {
    Identifier location = new Identifier(biomeEntry.biomeName().trim());

    if (location.getNamespace().equals("minecraft")) {
      tag(tagKey).add(ResourceKey.create(Registries.BIOME, location));
    } else {
      tag(tagKey).addOptional(location);
    }
  }
}