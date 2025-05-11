package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.registry.PVJBiomeModifiers;
import de.lucalabs.vibrantjourneys.registry.PVJConfiguredFeatures;
import de.lucalabs.vibrantjourneys.registry.PVJPlacements;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

public class PVJDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(PVJBlockLootProvider::new);
        pack.addProvider(PVJRecipes::new);
        pack.addProvider(PVJBiomeTagsProvider::new);
        pack.addProvider(PVJConfiguredFeatures::new);
        pack.addProvider(PVJPlacements::new);

        PVJBlockTags blockTags = pack.addProvider(PVJBlockTags::new);

        pack.addProvider((output, registries) ->
                new PVJItemTags(
                        output,
                        registries,
                        blockTags.getTagLookupFuture()));

    }

//  private static final RegistryBuilder BUILDER = new RegistryBuilder()
//    .addRegistry(RegistryKeys.CONFIGURED_FEATURE, PVJConfiguredFeatures::bootstrap)
//    .addRegistry(RegistryKeys.PLACED_FEATURE, PVJPlacements::bootstrap)
//    .addRegistry(ForgeRegistries.Keys.BIOME_MODIFIERS, PVJBiomeModifiers::bootstrap); // TODO implement this one!!

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
