package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.items.FuelBlockItem;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class PVJItems {

    public static final Item NETTLE_SOUP = registerItem("nettle_soup", () -> new StewItem(new Item.Settings().maxCount(1).food(Foods.NETTLE_SOUP)));

    public static final Item BEACH_GRASS = registerItem("beach_grass",
            () -> new BlockItem(PVJBlocks.BEACH_GRASS, basicItem()));
    public static final Item SEA_OATS = registerItem("sea_oats",
            () -> new BlockItem(PVJBlocks.SEA_OATS, basicItem()));
    public static final Item CATTAIL = registerItem("cattail",
            () -> new BlockItem(PVJBlocks.CATTAIL, basicItem()));
    public static final Item BARK_MUSHROOM = registerBurnable("bark_mushroom",
            () -> new FuelBlockItem(PVJBlocks.BARK_MUSHROOM, basicItem(), 100));
    public static final Item LIGHT_BROWN_BARK_MUSHROOM = registerBurnable("light_brown_bark_mushroom",
            () -> new FuelBlockItem(PVJBlocks.LIGHT_BROWN_BARK_MUSHROOM, basicItem(), 100));
    public static final Item ORANGE_BARK_MUSHROOM = registerBurnable("orange_bark_mushroom",
            () -> new FuelBlockItem(PVJBlocks.ORANGE_BARK_MUSHROOM, basicItem(), 100));
    public static final Item GLOWING_BLUE_FUNGUS = registerItem("glowing_blue_fungus",
            () -> new BlockItem(PVJBlocks.GLOWING_BLUE_FUNGUS, basicItem()));
    public static final Item SHORT_GRASS = registerItem("short_grass",
            () -> new BlockItem(PVJBlocks.SHORT_GRASS, basicItem()));
    public static final Item SMALL_CACTUS = registerItem("small_cactus",
            () -> new BlockItem(PVJBlocks.SMALL_CACTUS, basicItem()));
    public static final Item PRICKLY_BUSH = registerItem("prickly_bush",
            () -> new BlockItem(PVJBlocks.PRICKLY_BUSH, basicItem()));
    public static final Item REEDS = registerItem("reeds",
            () -> new BlockItem(PVJBlocks.REEDS, basicItem()));
    public static final Item ICICLE = registerItem("icicle",
            () -> new BlockItem(PVJBlocks.ICICLE, basicItem()));
    public static final Item SANDY_SPROUTS = registerItem("sandy_sprouts",
            () -> new BlockItem(PVJBlocks.SANDY_SPROUTS, basicItem()));
    public static final Item WATERGRASS = registerItem("watergrass",
            () -> new BlockItem(PVJBlocks.WATERGRASS, basicItem()));
    public static final Item PINK_LOTUS = registerItem("pink_lotus",
            () -> new PlaceableOnWaterItem(PVJBlocks.PINK_LOTUS, basicItem()));

    public static final Item SLIME_NODULE = registerItem("slime_nodule",
            () -> new BlockItem(PVJBlocks.SLIME_NODULE, basicItem()));
    public static final Item PINK_VINES = registerItem("pink_vines",
            () -> new BlockItem(PVJBlocks.PINK_VINES, basicItem()));
    public static final Item YELLOW_WILDFLOWERS = registerItem("yellow_wildflowers",
            () -> new BlockItem(PVJBlocks.YELLOW_WILDFLOWERS, basicItem()));
    public static final Item ORANGE_WILDFLOWERS = registerItem("orange_wildflowers",
            () -> new BlockItem(PVJBlocks.ORANGE_WILDFLOWERS, basicItem()));
    public static final Item BLUE_WILDFLOWERS = registerItem("blue_wildflowers",
            () -> new BlockItem(PVJBlocks.BLUE_WILDFLOWERS, basicItem()));
    public static final Item PURPLE_WILDFLOWERS = registerItem("purple_wildflowers",
            () -> new BlockItem(PVJBlocks.PURPLE_WILDFLOWERS, basicItem()));
    public static final Item WHITE_WILDFLOWERS = registerItem("white_wildflowers",
            () -> new BlockItem(PVJBlocks.WHITE_WILDFLOWERS, basicItem()));
    public static final Item MIXED_WILDFLOWERS = registerItem("mixed_wildflowers",
            () -> new BlockItem(PVJBlocks.MIXED_WILDFLOWERS, basicItem()));

    public static final Item CRIMSON_NETTLE = registerItem("crimson_nettle",
            () -> new BlockItem(PVJBlocks.CRIMSON_NETTLE, basicItem()));
    public static final Item WARPED_NETTLE = registerItem("warped_nettle",
            () -> new BlockItem(PVJBlocks.WARPED_NETTLE, basicItem()));
    public static final Item CINDERCANE = registerBurnable("cindercane",
            () -> new FuelBlockItem(PVJBlocks.CINDERCANE, basicItem(), 800));
    public static final Item GLOWCAP = registerItem("glowcap",
            () -> new BlockItem(PVJBlocks.GLOWCAP, basicItem()));

    public static final Item FALLEN_LEAVES = registerItem("fallen_leaves",
            () -> new BlockItem(PVJBlocks.FALLEN_LEAVES, basicItem()));
    public static final Item DEAD_FALLEN_LEAVES = registerItem("dead_fallen_leaves",
            () -> new BlockItem(PVJBlocks.DEAD_FALLEN_LEAVES, basicItem()));
    public static final Item TWIGS = registerBurnable("twigs",
            () -> new FuelBlockItem(PVJBlocks.TWIGS, basicItem(), 100));
    public static final Item ROCKS = registerItem("rocks",
            () -> new BlockItem(PVJBlocks.ROCKS, basicItem()));
    public static final Item MOSSY_ROCKS = registerItem("mossy_rocks",
            () -> new BlockItem(PVJBlocks.MOSSY_ROCKS, basicItem()));
    public static final Item SANDSTONE_ROCKS = registerItem("sandstone_rocks",
            () -> new BlockItem(PVJBlocks.SANDSTONE_ROCKS, basicItem()));
    public static final Item RED_SANDSTONE_ROCKS = registerItem("red_sandstone_rocks",
            () -> new BlockItem(PVJBlocks.RED_SANDSTONE_ROCKS, basicItem()));
    public static final Item ICE_CHUNKS = registerItem("ice_chunks",
            () -> new BlockItem(PVJBlocks.ICE_CHUNKS, basicItem()));
    public static final Item BONES = registerItem("bones",
            () -> new BlockItem(PVJBlocks.BONES, basicItem()));
    public static final Item CHARRED_BONES = registerItem("charred_bones",
            () -> new BlockItem(PVJBlocks.CHARRED_BONES, basicItem()));
    public static final Item PINECONES = registerBurnable("pinecones",
            () -> new FuelBlockItem(PVJBlocks.PINECONES, basicItem(), 100));
    public static final Item SEASHELLS = registerItem("seashells",
            () -> new BlockItem(PVJBlocks.SEASHELLS, basicItem()));

    public static final Item OAK_HOLLOW_LOG = registerBurnable("oak_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.OAK_HOLLOW_LOG, basicItem(), 300));
    public static final Item BIRCH_HOLLOW_LOG = registerBurnable("birch_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.BIRCH_HOLLOW_LOG, basicItem(), 300));
    public static final Item SPRUCE_HOLLOW_LOG = registerBurnable("spruce_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.SPRUCE_HOLLOW_LOG, basicItem(), 300));
    public static final Item JUNGLE_HOLLOW_LOG = registerBurnable("jungle_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.JUNGLE_HOLLOW_LOG, basicItem(), 300));
    public static final Item ACACIA_HOLLOW_LOG = registerBurnable("acacia_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.ACACIA_HOLLOW_LOG, basicItem(), 300));
    public static final Item DARK_OAK_HOLLOW_LOG = registerBurnable("dark_oak_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.DARK_OAK_HOLLOW_LOG, basicItem(), 300));
    public static final Item CHERRY_HOLLOW_LOG = registerBurnable("cherry_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.CHERRY_HOLLOW_LOG, basicItem(), 300));
    public static final Item MANGROVE_HOLLOW_LOG = registerBurnable("mangrove_hollow_log",
            () -> new FuelBlockItem(PVJBlocks.MANGROVE_HOLLOW_LOG, basicItem(), 300));

    public static final Item FERROUS_GRAVEL = registerItem("ferrous_gravel",
            () -> new BlockItem(PVJBlocks.FERROUS_GRAVEL, basicItem()));
    public static final Item GILDED_GRAVEL = registerItem("gilded_gravel",
            () -> new BlockItem(PVJBlocks.GILDED_GRAVEL, basicItem()));
    public static final Item GILDED_RED_SAND = registerItem("gilded_red_sand",
            () -> new BlockItem(PVJBlocks.GILDED_RED_SAND, basicItem()));

    public static final Item MUDDY_BONES = registerItem("muddy_bones",
            () -> new BlockItem(PVJBlocks.MUDDY_BONES, basicItem()));

    private static Item.Settings basicItem() {
        return new Item.Settings();
    }

    private static Item registerBurnable(String name, Supplier<FuelBlockItem> item) {
        Item i = registerItem(name, item);
        FuelRegistry.INSTANCE.add(i, ((FuelBlockItem) i).getBurnTime());
        return i;
    }

    private static Item registerItem(String name, Supplier<? extends Item> item) {
        Item i = Registry.register(Registries.ITEM, new Identifier(ProjectVibrantJourneys.MOD_ID, name), item.get());
        PVJItemGroup.TAB_ITEMS.add(i);
        return i;
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("Initializing items");
    }

    public static class Foods {
        public static final FoodComponent NETTLE_SOUP = new FoodComponent.Builder()
                .hunger(3)
                .saturationModifier(0.3F)
                .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200), 1.0F)
                .alwaysEdible()
                .build();
    }
}
