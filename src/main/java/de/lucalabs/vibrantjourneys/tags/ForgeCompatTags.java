package de.lucalabs.vibrantjourneys.tags;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class ForgeCompatTags {

    public static final TagKey<Biome> IS_CONIFEROUS = createBiomeTag("is_coniferous");
    public static final TagKey<Biome> IS_SNOWY = createBiomeTag("is_snowy");

    private ForgeCompatTags() {}

    private static TagKey<Biome> createBiomeTag(String location) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of("forge", location));
    }
}
