package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.registry.PVJConfiguredFeatures;
import de.lucalabs.vibrantjourneys.registry.PVJPlacements;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PVJDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(PVJBlockLootProvider::new);
        pack.addProvider(PVJRecipes::new);
        pack.addProvider(PVJBiomeTagsProvider::new);
        pack.addProvider(PVJConfiguredFeatures::new);
        pack.addProvider(PVJPlacements::new);

        PVJBlockTagProvider blockTags = pack.addProvider(PVJBlockTagProvider::new);

        pack.addProvider((output, registries) ->
                new PVJItemTagProvider(
                        output,
                        registries,
                        blockTags.getTagLookupFuture()));

    }
}
