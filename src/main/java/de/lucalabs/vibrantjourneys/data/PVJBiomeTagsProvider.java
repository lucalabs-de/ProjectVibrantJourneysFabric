package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.tags.PVJTags;
import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeFeatureUtils;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.vanilla.VanillaBiomeTagProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PVJBiomeTagsProvider extends VanillaBiomeTagProvider {
    public PVJBiomeTagsProvider(DataOutput packOutput, CompletableFuture<RegistryWrapper.WrapperLookup> provider, @Nullable ExistingFileHelper helper) {
        super(packOutput, provider); //, ProjectVibrantJourneys.MOD_ID, helper);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
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
            getOrCreateTagBuilder(tagKey).add(RegistryKey.of(RegistryKeys.BIOME, location));
        } else {
            getOrCreateTagBuilder(tagKey).addOptional(location);
        }
    }
}