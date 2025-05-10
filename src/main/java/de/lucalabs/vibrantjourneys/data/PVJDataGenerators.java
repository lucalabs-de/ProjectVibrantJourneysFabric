package de.lucalabs.vibrantjourneys.data;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ProjectVibrantJourneys.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PVJDataGenerators {

  private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
    .add(Registries.CONFIGURED_FEATURE, PVJConfiguredFeatures::bootstrap)
    .add(Registries.PLACED_FEATURE, PVJPlacements::bootstrap)
    .add(ForgeRegistries.Keys.BIOME_MODIFIERS, PVJBiomeModifiers::bootstrap);

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    PVJBlockTags blockTags = new PVJBlockTags(packOutput, lookupProvider, existingFileHelper);
    generator.addProvider(event.includeServer(), blockTags);
    generator.addProvider(event.includeServer(), new PVJItemTags(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
    generator.addProvider(event.includeServer(), new PVJRecipes(packOutput));
    generator.addProvider(event.includeServer(), new PVJBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper));

    generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, BUILDER, Set.of(ProjectVibrantJourneys.MOD_ID)));
    generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(PVJBlockLootProvider::new, LootContextParamSets.BLOCK))));
  }
}
