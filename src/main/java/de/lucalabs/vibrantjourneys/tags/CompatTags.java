package de.lucalabs.vibrantjourneys.tags;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class CompatTags {

    public static final TagKey<Biome> IS_CONIFEROUS = createBiomeTag("is_coniferous");

    private CompatTags() {
    }

    private static TagKey<Biome> createBiomeTag(String location) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(ProjectVibrantJourneys.MOD_ID, location));
    }
}
