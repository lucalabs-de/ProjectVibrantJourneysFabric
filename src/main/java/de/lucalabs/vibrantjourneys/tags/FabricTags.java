package de.lucalabs.vibrantjourneys.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class FabricTags {

    public static final TagKey<Block> SAND = createBlockTag("sands");
    public static final TagKey<Block> SAND_RED = createBlockTag("sands/red");
    public static final TagKey<Block> ORES = createBlockTag("ores");
    public static final TagKey<Block> GRAVEL = createBlockTag("gravels");
    public static final TagKey<Block> STORAGE_BLOCKS = createBlockTag("storage_blocks");

    public static final TagKey<Item> BONES = createItemTag("bones");
    public static final TagKey<Item> MUSHROOMS = createItemTag("mushrooms");
    public static final TagKey<Item> RODS = createItemTag("rods");
    public static final TagKey<Item> WOODEN_RODS = createItemTag("rods/wooden");

    public static final TagKey<Biome> IS_PLAINS = createBiomeTag("is_plains");
    public static final TagKey<Biome> PLAINS = createBiomeTag("plains");
    public static final TagKey<Biome> IS_SNOWY = createBiomeTag("is_snowy");
    public static final TagKey<Biome> SNOWY = createBiomeTag("snowy");
    public static final TagKey<Biome> IS_MUSHROOM = createBiomeTag("is_mushroom");
    public static final TagKey<Biome> MUSHROOM = createBiomeTag("mushroom");
    public static final TagKey<Biome> IS_DESERT = createBiomeTag("is_desert");
    public static final TagKey<Biome> DESERT = createBiomeTag("desert");
    public static final TagKey<Biome> IS_SWAMP = createBiomeTag("is_swamp");
    public static final TagKey<Biome> SWAMP = createBiomeTag("swamp");

    private FabricTags() {}

    private static TagKey<Block> createBlockTag(String location) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", location));
    }

    private static TagKey<Item> createItemTag(String location) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", location));
    }

    private static TagKey<Biome> createBiomeTag(String location) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of("c", location));
    }
}
