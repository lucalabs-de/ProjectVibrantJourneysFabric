package de.lucalabs.vibrantjourneys.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PVJDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(PVJBlockLootProvider::new);
        pack.addProvider(PVJRecipes::new);
        pack.addProvider(PVJBiomeTagsProvider::new);

        PVJBlockTags blockTags = pack.addProvider(PVJBlockTags::new);

        pack.addProvider((output, registries) ->
                new PVJItemTags(
                        output,
                        registries,
                        blockTags.getTagLookupFuture()));
    }

//  private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
//    .add(Registries.CONFIGURED_FEATURE, PVJConfiguredFeatures::bootstrap)
//    .add(Registries.PLACED_FEATURE, PVJPlacements::bootstrap)
//    .add(ForgeRegistries.Keys.BIOME_MODIFIERS, PVJBiomeModifiers::bootstrap);
//
//  @SubscribeEvent
//  public static void gatherData(GatherDataEvent event) {
//    DataGenerator generator = event.getGenerator();
//    DataOutput packOutput = generator.getPackOutput();
//    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//    CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider = event.getLookupProvider();
//
//    PVJBlockTags blockTags = new PVJBlockTags(packOutput, lookupProvider, existingFileHelper);
//    generator.addProvider(event.includeServer(), blockTags);
//    generator.addProvider(event.includeServer(), new PVJItemTags(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
//    generator.addProvider(event.includeServer(), new PVJRecipes(packOutput));
//    generator.addProvider(event.includeServer(), new PVJBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper));
//
//    generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, BUILDER, Set.of(ProjectVibrantJourneys.MOD_ID)));
//    generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(PVJBlockLootProvider::new, LootContextParamSets.BLOCK))));
//  }


}
