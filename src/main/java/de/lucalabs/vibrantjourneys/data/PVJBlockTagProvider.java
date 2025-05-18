package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.tags.FabricTags;
import de.lucalabs.vibrantjourneys.tags.PVJTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.vanilla.VanillaBlockTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class PVJBlockTagProvider extends VanillaBlockTagProvider {

    public PVJBlockTagProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
        this.addModTags(provider);
        this.addModCompatTags(provider);
    }

    private void addModTags(RegistryWrapper.WrapperLookup provider) {
        getOrCreateTagBuilder(BlockTags.OAK_LOGS).add(PVJBlocks.OAK_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.BIRCH_LOGS).add(PVJBlocks.BIRCH_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.SPRUCE_LOGS).add(PVJBlocks.SPRUCE_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.JUNGLE_LOGS).add(PVJBlocks.JUNGLE_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.DARK_OAK_LOGS).add(PVJBlocks.DARK_OAK_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.CHERRY_LOGS).add(PVJBlocks.CHERRY_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.MANGROVE_LOGS).add(PVJBlocks.MANGROVE_HOLLOW_LOG);
        getOrCreateTagBuilder(BlockTags.LOGS).add(
                PVJBlocks.OAK_HOLLOW_LOG,
                PVJBlocks.BIRCH_HOLLOW_LOG,
                PVJBlocks.SPRUCE_HOLLOW_LOG,
                PVJBlocks.JUNGLE_HOLLOW_LOG,
                PVJBlocks.DARK_OAK_HOLLOW_LOG,
                PVJBlocks.ACACIA_HOLLOW_LOG,
                PVJBlocks.CHERRY_HOLLOW_LOG,
                PVJBlocks.MANGROVE_HOLLOW_LOG
        );
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(
                PVJBlocks.OAK_HOLLOW_LOG,
                PVJBlocks.BIRCH_HOLLOW_LOG,
                PVJBlocks.SPRUCE_HOLLOW_LOG,
                PVJBlocks.JUNGLE_HOLLOW_LOG,
                PVJBlocks.DARK_OAK_HOLLOW_LOG,
                PVJBlocks.ACACIA_HOLLOW_LOG,
                PVJBlocks.CHERRY_HOLLOW_LOG,
                PVJBlocks.MANGROVE_HOLLOW_LOG
        );
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
                PVJBlocks.OAK_HOLLOW_LOG,
                PVJBlocks.BIRCH_HOLLOW_LOG,
                PVJBlocks.SPRUCE_HOLLOW_LOG,
                PVJBlocks.JUNGLE_HOLLOW_LOG,
                PVJBlocks.DARK_OAK_HOLLOW_LOG,
                PVJBlocks.ACACIA_HOLLOW_LOG,
                PVJBlocks.CHERRY_HOLLOW_LOG,
                PVJBlocks.MANGROVE_HOLLOW_LOG
        );
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                PVJBlocks.ICICLE
        );
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
                PVJBlocks.FERROUS_GRAVEL,
                PVJBlocks.GILDED_GRAVEL,
                PVJBlocks.GILDED_RED_SAND,
                PVJBlocks.MUDDY_BONES);
        getOrCreateTagBuilder(FabricTags.GRAVEL).add(PVJBlocks.FERROUS_GRAVEL, PVJBlocks.GILDED_GRAVEL);
        getOrCreateTagBuilder(BlockTags.SAND).add(PVJBlocks.GILDED_RED_SAND);
        getOrCreateTagBuilder(FabricTags.SAND).add(PVJBlocks.GILDED_RED_SAND);
        getOrCreateTagBuilder(FabricTags.SAND_RED).add(PVJBlocks.GILDED_RED_SAND);
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK).add(
                PVJBlocks.OAK_HOLLOW_LOG,
                PVJBlocks.BIRCH_HOLLOW_LOG,
                PVJBlocks.SPRUCE_HOLLOW_LOG,
                PVJBlocks.JUNGLE_HOLLOW_LOG,
                PVJBlocks.DARK_OAK_HOLLOW_LOG,
                PVJBlocks.ACACIA_HOLLOW_LOG,
                PVJBlocks.CHERRY_HOLLOW_LOG,
                PVJBlocks.MANGROVE_HOLLOW_LOG
        );
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(
                        PVJBlocks.FALLEN_LEAVES,
                        PVJBlocks.DEAD_FALLEN_LEAVES,
                        PVJBlocks.CATTAIL,
                        PVJBlocks.REEDS,
                        PVJBlocks.CINDERCANE,
                        PVJBlocks.GLOWCAP,
                        PVJBlocks.PRICKLY_BUSH,
                        PVJBlocks.BEACH_GRASS,
                        PVJBlocks.SEA_OATS,
                        PVJBlocks.SANDY_SPROUTS,
                        PVJBlocks.CRIMSON_NETTLE,
                        PVJBlocks.WARPED_NETTLE,
                        PVJBlocks.WATERGRASS,
                        PVJBlocks.YELLOW_WILDFLOWERS,
                        PVJBlocks.ORANGE_WILDFLOWERS,
                        PVJBlocks.BLUE_WILDFLOWERS,
                        PVJBlocks.PURPLE_WILDFLOWERS,
                        PVJBlocks.WHITE_WILDFLOWERS,
                        PVJBlocks.MIXED_WILDFLOWERS
                );

        getOrCreateTagBuilder(PVJTags.GROWS_ON_HOLLOW_LOG).add(
                Blocks.GRASS,
                Blocks.FERN,
                Blocks.TALL_GRASS,
                Blocks.LARGE_FERN,
                Blocks.PINK_PETALS,
                Blocks.BLUE_ORCHID,
                Blocks.PITCHER_CROP,
                Blocks.PITCHER_PLANT,
                Blocks.TORCHFLOWER,
                Blocks.MANGROVE_PROPAGULE,
                Blocks.DEAD_BUSH,
                PVJBlocks.CATTAIL,
                PVJBlocks.REEDS,
                PVJBlocks.SHORT_GRASS,
                PVJBlocks.SANDY_SPROUTS,
                PVJBlocks.BEACH_GRASS,
                PVJBlocks.SEA_OATS,
                PVJBlocks.PRICKLY_BUSH,
                PVJBlocks.WATERGRASS,
                PVJBlocks.YELLOW_WILDFLOWERS,
                PVJBlocks.ORANGE_WILDFLOWERS,
                PVJBlocks.BLUE_WILDFLOWERS,
                PVJBlocks.PURPLE_WILDFLOWERS,
                PVJBlocks.WHITE_WILDFLOWERS,
                PVJBlocks.MIXED_WILDFLOWERS
        );
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
                .add(
                        PVJBlocks.TWIGS,
                        PVJBlocks.ROCKS,
                        PVJBlocks.SEASHELLS,
                        PVJBlocks.PINECONES,
                        PVJBlocks.RED_SANDSTONE_ROCKS,
                        PVJBlocks.SANDSTONE_ROCKS,
                        PVJBlocks.MOSSY_ROCKS,
                        PVJBlocks.ICE_CHUNKS,
                        PVJBlocks.BONES,
                        PVJBlocks.CHARRED_BONES,
                        PVJBlocks.FALLEN_LEAVES,
                        PVJBlocks.DEAD_FALLEN_LEAVES,
                        PVJBlocks.SHORT_GRASS,
                        PVJBlocks.SANDY_SPROUTS,
                        PVJBlocks.NATURAL_COBWEB,
                        PVJBlocks.BARK_MUSHROOM,
                        PVJBlocks.LIGHT_BROWN_BARK_MUSHROOM,
                        PVJBlocks.ORANGE_BARK_MUSHROOM,
                        PVJBlocks.WATERGRASS,
                        PVJBlocks.YELLOW_WILDFLOWERS,
                        PVJBlocks.ORANGE_WILDFLOWERS,
                        PVJBlocks.BLUE_WILDFLOWERS,
                        PVJBlocks.PURPLE_WILDFLOWERS,
                        PVJBlocks.WHITE_WILDFLOWERS,
                        PVJBlocks.MIXED_WILDFLOWERS
                );

        getOrCreateTagBuilder(PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)
                .add(
                        Blocks.DEEPSLATE,
                        Blocks.SCULK,
                        Blocks.STONE_BRICKS,
                        Blocks.MOSSY_STONE_BRICKS,
                        Blocks.CRACKED_STONE_BRICKS,
                        Blocks.MAGMA_BLOCK,
                        Blocks.SPAWNER,
                        Blocks.FARMLAND,
                        Blocks.DIRT_PATH,
                        Blocks.MYCELIUM,
                        Blocks.AMETHYST_BLOCK,
                        Blocks.DEEPSLATE_BRICKS,
                        Blocks.DEEPSLATE_TILES,
                        Blocks.CRACKED_DEEPSLATE_TILES,
                        Blocks.CRACKED_DEEPSLATE_BRICKS,
                        Blocks.CHISELED_DEEPSLATE,
                        Blocks.POLISHED_DEEPSLATE,
                        Blocks.COBBLED_DEEPSLATE,
                        Blocks.REINFORCED_DEEPSLATE,
                        Blocks.NOTE_BLOCK,
                        Blocks.COPPER_BLOCK,
                        Blocks.CUT_COPPER,
                        Blocks.EXPOSED_COPPER,
                        Blocks.EXPOSED_CUT_COPPER,
                        Blocks.OXIDIZED_COPPER,
                        Blocks.OXIDIZED_CUT_COPPER,
                        Blocks.WEATHERED_COPPER,
                        Blocks.WEATHERED_CUT_COPPER,
                        Blocks.WAXED_COPPER_BLOCK,
                        Blocks.WAXED_CUT_COPPER,
                        Blocks.WAXED_EXPOSED_COPPER,
                        Blocks.WAXED_EXPOSED_CUT_COPPER,
                        Blocks.WAXED_OXIDIZED_COPPER,
                        Blocks.WAXED_OXIDIZED_CUT_COPPER,
                        Blocks.WAXED_WEATHERED_COPPER,
                        Blocks.WAXED_WEATHERED_CUT_COPPER,
                        Blocks.HAY_BLOCK,
                        Blocks.BARREL,
                        Blocks.BEEHIVE,
                        Blocks.BEE_NEST,
                        Blocks.CRAFTING_TABLE,
                        Blocks.FURNACE,
                        Blocks.BLAST_FURNACE,
                        Blocks.SMOKER,
                        Blocks.CARTOGRAPHY_TABLE,
                        Blocks.FLETCHING_TABLE,
                        Blocks.TNT,
                        Blocks.SMITHING_TABLE,
                        Blocks.LOOM,
                        Blocks.DECORATED_POT
                )
                .addOptionalTag(BlockTags.WOOL.id())
                .addOptionalTag(BlockTags.PLANKS.id())
                .addOptionalTag(BlockTags.LEAVES.id())
                .addOptionalTag(BlockTags.TERRACOTTA.id())
                .addOptionalTag(BlockTags.SNOW.id())
                .addOptionalTag(BlockTags.ICE.id())
                .addOptionalTag(FabricTags.ORES.id())
                .addOptionalTag(FabricTags.STORAGE_BLOCKS.id())
                .addOptionalTag(BlockTags.FENCES.id())
                .addOptionalTag(BlockTags.FENCE_GATES.id())
                .addOptionalTag(BlockTags.STAIRS.id());
    }

    private void addModCompatTags(RegistryWrapper.WrapperLookup provider) {
        getOrCreateTagBuilder(PVJTags.COMPOST_ACTIVATORS)
                .add(PVJBlocks.GLOWCAP);
    }
}
