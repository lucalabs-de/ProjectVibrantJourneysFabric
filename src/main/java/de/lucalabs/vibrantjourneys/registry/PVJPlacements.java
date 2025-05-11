package de.lucalabs.vibrantjourneys.registry;

import com.google.common.collect.ImmutableList;
import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiers;

public class PVJPlacements extends FabricDynamicRegistryProvider {

    public static final RegistryKey<PlacedFeature> MOSS_CARPET = createKey("moss_carpet");

    /* OVERWORLD PLANTS */
    public static final RegistryKey<PlacedFeature> SEA_OATS = createKey("sea_oats");
    public static final RegistryKey<PlacedFeature> CATTAILS = createKey("cattails");
    public static final RegistryKey<PlacedFeature> BEACH_GRASS = createKey("beach_grass");
    public static final RegistryKey<PlacedFeature> BARK_MUSHROOM = createKey("bark_mushroom");
    public static final RegistryKey<PlacedFeature> SHORT_GRASS = createKey("short_grass");
    public static final RegistryKey<PlacedFeature> SMALL_CACTUS = createKey("small_cactus");
    public static final RegistryKey<PlacedFeature> ICICLE = createKey("icicle");
    public static final RegistryKey<PlacedFeature> CAVE_ROOTS = createKey("cave_roots");
    public static final RegistryKey<PlacedFeature> REEDS = createKey("reeds");
    public static final RegistryKey<PlacedFeature> PRICKLY_BUSH = createKey("prickly_bush");
    public static final RegistryKey<PlacedFeature> SANDY_SPROUTS = createKey("sandy_sprouts");
    public static final RegistryKey<PlacedFeature> WATERGRASS = createKey("watergrass");
    public static final RegistryKey<PlacedFeature> BEACHED_KELP = createKey("beached_kelp");
    public static final RegistryKey<PlacedFeature> DRIED_BEACHED_KELP = createKey("dried_beached_kelp");
    public static final RegistryKey<PlacedFeature> GLOWING_BLUE_FUNGUS = createKey("glowing_blue_fungus");
    public static final RegistryKey<PlacedFeature> FLOATING_PINK_LOTUS = createKey("floating_pink_lotus");
    public static final RegistryKey<PlacedFeature> OAK_BUSH = createKey("oak_bush");
    public static final RegistryKey<PlacedFeature> YELLOW_WILDFLOWERS = createKey("yellow_wildflowers");
    public static final RegistryKey<PlacedFeature> ORANGE_WILDFLOWERS = createKey("orange_wildflowers");
    public static final RegistryKey<PlacedFeature> BLUE_WILDFLOWERS = createKey("blue_wildflowers");
    public static final RegistryKey<PlacedFeature> PURPLE_WILDFLOWERS = createKey("purple_wildflowers");
    public static final RegistryKey<PlacedFeature> WHITE_WILDFLOWERS = createKey("white_wildflowers");
    public static final RegistryKey<PlacedFeature> MIXED_WILDFLOWERS = createKey("mixed_wildflowers");
    public static final RegistryKey<PlacedFeature> MANY_YELLOW_WILDFLOWERS = createKey("many_yellow_wildflowers");
    public static final RegistryKey<PlacedFeature> MANY_ORANGE_WILDFLOWERS = createKey("many_orange_wildflowers");
    public static final RegistryKey<PlacedFeature> MANY_BLUE_WILDFLOWERS = createKey("many_blue_wildflowers");
    public static final RegistryKey<PlacedFeature> MANY_PURPLE_WILDFLOWERS = createKey("many_purple_wildflowers");
    public static final RegistryKey<PlacedFeature> MANY_WHITE_WILDFLOWERS = createKey("many_white_wildflowers");
    public static final RegistryKey<PlacedFeature> MANY_MIXED_WILDFLOWERS = createKey("many_mixed_wildflowers");
    public static final RegistryKey<PlacedFeature> SLIME_NODULE = createKey("slime_nodule");
    public static final RegistryKey<PlacedFeature> PINK_VINES = createKey("pink_vines");

    /* GROUNDCOVER */
    public static final RegistryKey<PlacedFeature> TWIGS = createKey("twigs");
    public static final RegistryKey<PlacedFeature> FALLEN_LEAVES = createKey("fallen_leaves");
    public static final RegistryKey<PlacedFeature> DEAD_FALLEN_LEAVES = createKey("dead_fallen_leaves");
    public static final RegistryKey<PlacedFeature> DENSE_DEAD_FALLEN_LEAVES = createKey("dense_dead_fallen_leaves");
    public static final RegistryKey<PlacedFeature> PINECONES = createKey("pinecones");
    public static final RegistryKey<PlacedFeature> SEASHELLS = createKey("seashells");
    public static final RegistryKey<PlacedFeature> OCEAN_FLOOR_SEASHELLS = createKey("ocean_floor_seashells");
    public static final RegistryKey<PlacedFeature> EXTRA_OCEAN_FLOOR_SEASHELLS = createKey("extra_ocean_floor_seashells");
    public static final RegistryKey<PlacedFeature> ROCKS = createKey("rocks");
    public static final RegistryKey<PlacedFeature> ICE_CHUNKS = createKey("ice_chunks");
    public static final RegistryKey<PlacedFeature> BONES = createKey("bones");
    public static final RegistryKey<PlacedFeature> CHARRED_BONES = createKey("charred_bones");
    public static final RegistryKey<PlacedFeature> CAVE_BONES = createKey("cave_bones");
    public static final RegistryKey<PlacedFeature> CAVE_ROCKS = createKey("cave_rocks");

    /* NETHER PLANTS */
    public static final RegistryKey<PlacedFeature> WARPED_NETTLE = createKey("warped_nettle");
    public static final RegistryKey<PlacedFeature> CRIMSON_NETTLE = createKey("crimson_nettle");
    public static final RegistryKey<PlacedFeature> GLOWCAP = createKey("glowcap");
    public static final RegistryKey<PlacedFeature> CINDERCANE = createKey("cindercane");

    /* FALLEN TREES */
    public static final RegistryKey<PlacedFeature> OAK_FALLEN_TREE = createKey("oak_fallen_tree");
    public static final RegistryKey<PlacedFeature> BIRCH_FALLEN_TREE = createKey("birch_fallen_tree");
    public static final RegistryKey<PlacedFeature> SPRUCE_FALLEN_TREE = createKey("spruce_fallen_tree");
    public static final RegistryKey<PlacedFeature> JUNGLE_FALLEN_TREE = createKey("jungle_fallen_tree");
    public static final RegistryKey<PlacedFeature> ACACIA_FALLEN_TREE = createKey("acacia_fallen_tree");
    public static final RegistryKey<PlacedFeature> DARK_OAK_FALLEN_TREE = createKey("dark_oak_fallen_tree");
    public static final RegistryKey<PlacedFeature> CHERRY_FALLEN_TREE = createKey("cherry_fallen_tree");
    public static final RegistryKey<PlacedFeature> MANGROVE_FALLEN_TREE = createKey("mangrove_fallen_tree");

    /* EXTRAS */
    public static final RegistryKey<PlacedFeature> NATURAL_COBWEB = createKey("natural_cobweb");
    public static final RegistryKey<PlacedFeature> EXTRA_SEAGRASS = createKey("extra_seagrass");
    public static final RegistryKey<PlacedFeature> EXTRA_LILYPADS = createKey("extra_lilypads");
    public static final RegistryKey<PlacedFeature> EXTRA_GRASS = createKey("extra_grass");
    public static final RegistryKey<PlacedFeature> TIDE_POOL = createKey("tide_pool");
    public static final RegistryKey<PlacedFeature> GRAVEL_PIT = createKey("gravel_pit");
    public static final RegistryKey<PlacedFeature> GOLD_PIT = createKey("gold_pit");
    public static final RegistryKey<PlacedFeature> MUDDY_BONES = createKey("muddy_bones");
    public static final RegistryKey<PlacedFeature> LOTUS_POND = createKey("lotus_pond");
    public static final RegistryKey<PlacedFeature> HOT_SPRINGS = createKey("hot_springs");

    public PVJPlacements(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void bootstrap(Registerable<PlacedFeature> context) {
    }

    private static RegistryKey<PlacedFeature> createKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(ProjectVibrantJourneys.MOD_ID, name));
    }

    public static void register(Entries entries, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        entries.add(key, new PlacedFeature(feature, modifiers));
    }

    public static void register(Entries entries, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers, PlacementModifier... extraModifiers) {
        List<PlacementModifier> list = new ArrayList<>();
        list.addAll(modifiers);
        list.addAll(Arrays.asList(extraModifiers));

        entries.add(key, new PlacedFeature(feature, list));
    }

    public static void register(Entries entries, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        entries.add(key, new PlacedFeature(feature, List.of(modifiers)));
    }

    public static List<PlacementModifier> onceEvery(int distance) {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        builder.add(RarityFilterPlacementModifier.of(distance));

        builder.add(SquarePlacementModifier.of());
        builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
        builder.add(BiomePlacementModifier.of());
        return builder.build();
    }

    private static List<PlacementModifier> seagrassPlacement(int count) {
        return List.of(SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, CountPlacementModifier.of(count), BiomePlacementModifier.of());
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> holderGetter = wrapperLookup.createRegistryLookup().getOrThrow(RegistryKeys.CONFIGURED_FEATURE);

        register(entries, MOSS_CARPET, holderGetter.getOrThrow(PVJConfiguredFeatures.MOSS_CARPETS), modifiers(3));
        register(entries, SEA_OATS, holderGetter.getOrThrow(PVJConfiguredFeatures.SEA_OATS), onceEvery(5));
        register(entries, CATTAILS, holderGetter.getOrThrow(PVJConfiguredFeatures.CATTAILS), onceEvery(1));
        register(entries, BEACH_GRASS, holderGetter.getOrThrow(PVJConfiguredFeatures.BEACH_GRASS), onceEvery(5));
        register(entries, BARK_MUSHROOM, holderGetter.getOrThrow(PVJConfiguredFeatures.BARK_MUSHROOM), modifiers(30));
        register(entries, SHORT_GRASS, holderGetter.getOrThrow(PVJConfiguredFeatures.SHORT_GRASS), modifiers(3));
        register(
                entries,
                SMALL_CACTUS,
                holderGetter.getOrThrow(PVJConfiguredFeatures.SMALL_CACTUS),
                RarityFilterPlacementModifier.of(8),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());
        register(
                entries,
                ICICLE,
                holderGetter.getOrThrow(PVJConfiguredFeatures.ICICLE),
                CountPlacementModifier.of(150),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(50), YOffset.fixed(256)),
                EnvironmentScanPlacementModifier.of(
                        Direction.UP,
                        BlockPredicate.hasSturdyFace(Direction.DOWN),
                        BlockPredicate.IS_AIR,
                        12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
                BiomePlacementModifier.of());
        register(
                entries,
                CAVE_ROOTS,
                holderGetter.getOrThrow(PVJConfiguredFeatures.CAVE_ROOTS),
                CountPlacementModifier.of(188),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(256)),
                EnvironmentScanPlacementModifier.of(
                        Direction.UP,
                        BlockPredicate.hasSturdyFace(Direction.DOWN),
                        BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
                BiomePlacementModifier.of());
        register(entries, REEDS, holderGetter.getOrThrow(PVJConfiguredFeatures.REEDS), onceEvery(1));
        register(entries, PRICKLY_BUSH, holderGetter.getOrThrow(PVJConfiguredFeatures.PRICKLY_BUSH), onceEvery(2));
        register(entries, TWIGS, holderGetter.getOrThrow(PVJConfiguredFeatures.TWIGS), modifiers(3));
        register(entries, FALLEN_LEAVES, holderGetter.getOrThrow(PVJConfiguredFeatures.FALLEN_LEAVES), modifiers(3));
        register(entries, DEAD_FALLEN_LEAVES, holderGetter.getOrThrow(PVJConfiguredFeatures.DEAD_FALLEN_LEAVES), modifiers(3));
        register(entries, DENSE_DEAD_FALLEN_LEAVES, holderGetter.getOrThrow(PVJConfiguredFeatures.DEAD_FALLEN_LEAVES), modifiers(6));
        register(entries, PINECONES, holderGetter.getOrThrow(PVJConfiguredFeatures.PINECONES), modifiers(2));
        register(entries, SEASHELLS, holderGetter.getOrThrow(PVJConfiguredFeatures.SEASHELLS), modifiers(2));
        register(entries, OCEAN_FLOOR_SEASHELLS, holderGetter.getOrThrow(PVJConfiguredFeatures.SEASHELLS), seagrassPlacement(2));
        register(entries, EXTRA_OCEAN_FLOOR_SEASHELLS, holderGetter.getOrThrow(PVJConfiguredFeatures.SEASHELLS), seagrassPlacement(4));
        register(entries, ROCKS, holderGetter.getOrThrow(PVJConfiguredFeatures.ROCKS), modifiers(2));
        register(entries, ICE_CHUNKS, holderGetter.getOrThrow(PVJConfiguredFeatures.ICE_CHUNKS), modifiers(1));
        register(entries, BONES, holderGetter.getOrThrow(PVJConfiguredFeatures.BONES), modifiers(1));
        register(
                entries,
                CHARRED_BONES,
                holderGetter.getOrThrow(PVJConfiguredFeatures.CHARRED_BONES),
                RarityFilterPlacementModifier.of(5),
                CountMultilayerPlacementModifier.of(1),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_TOP_RANGE,
                BiomePlacementModifier.of());
        register(
                entries,
                CAVE_BONES,
                holderGetter.getOrThrow(PVJConfiguredFeatures.BONES),
                CountPlacementModifier.of(100),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(4), YOffset.fixed(60)),
                EnvironmentScanPlacementModifier.of(
                        Direction.DOWN,
                        BlockPredicate.solid(),
                        BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of());
        register(
                entries,
                CAVE_ROCKS,
                holderGetter.getOrThrow(PVJConfiguredFeatures.ROCKS),
                CountPlacementModifier.of(250),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(4), YOffset.fixed(60)),
                EnvironmentScanPlacementModifier.of(
                        Direction.DOWN,
                        BlockPredicate.solid(),
                        BlockPredicate.IS_AIR,
                        12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of());
        register(
                entries,
                WARPED_NETTLE,
                holderGetter.getOrThrow(PVJConfiguredFeatures.WARPED_NETTLE),
                RarityFilterPlacementModifier.of(4),
                CountMultilayerPlacementModifier.of(2),
                BiomePlacementModifier.of());
        register(
                entries,
                CRIMSON_NETTLE,
                holderGetter.getOrThrow(PVJConfiguredFeatures.CRIMSON_NETTLE),
                RarityFilterPlacementModifier.of(4),
                CountMultilayerPlacementModifier.of(2),
                BiomePlacementModifier.of());
        register(
                entries,
                GLOWCAP,
                holderGetter.getOrThrow(PVJConfiguredFeatures.GLOWCAP),
                RarityFilterPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_TOP_RANGE,
                BiomePlacementModifier.of());
        register(
                entries,
                CINDERCANE,
                holderGetter.getOrThrow(PVJConfiguredFeatures.CINDERCANE),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_TOP_RANGE,
                BiomePlacementModifier.of());
        register(entries, OAK_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.OAK_FALLEN_TREE), modifiers(1));
        register(entries, BIRCH_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.BIRCH_FALLEN_TREE), modifiers(1));
        register(entries, SPRUCE_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.SPRUCE_FALLEN_TREE), modifiers(1));
        register(entries, JUNGLE_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.JUNGLE_FALLEN_TREE), modifiers(1));
        register(entries, ACACIA_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.ACACIA_FALLEN_TREE), modifiers(1));
        register(entries, DARK_OAK_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.DARK_OAK_FALLEN_TREE), modifiers(1));
        register(entries, CHERRY_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.CHERRY_FALLEN_TREE), modifiers(1));
        register(entries, MANGROVE_FALLEN_TREE, holderGetter.getOrThrow(PVJConfiguredFeatures.MANGROVE_FALLEN_TREE), modifiers(1));
        register(entries, NATURAL_COBWEB, holderGetter.getOrThrow(PVJConfiguredFeatures.NATURAL_COBWEB), modifiers(5));
        register(entries, EXTRA_SEAGRASS, holderGetter.getOrThrow(OceanConfiguredFeatures.SEAGRASS_SLIGHTLY_LESS_SHORT), seagrassPlacement(48));
        register(entries, EXTRA_LILYPADS, holderGetter.getOrThrow(PVJConfiguredFeatures.LILYPADS), modifiers(4));
        register(
                entries,
                EXTRA_GRASS,
                holderGetter.getOrThrow(VegetationConfiguredFeatures.PATCH_GRASS),
                NoiseThresholdCountPlacementModifier.of(-0.8D, 5, 10),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
        register(
                entries,
                TIDE_POOL,
                holderGetter.getOrThrow(PVJConfiguredFeatures.TIDE_POOL),
                CountPlacementModifier.of(1),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                EnvironmentScanPlacementModifier.of(
                        Direction.DOWN,
                        BlockPredicate.solid(),
                        BlockPredicate.IS_AIR,
                        12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of());
        register(entries, SANDY_SPROUTS, holderGetter.getOrThrow(PVJConfiguredFeatures.SANDY_SPROUTS), onceEvery(5));
        register(entries, WATERGRASS, holderGetter.getOrThrow(PVJConfiguredFeatures.WATERGRASS), onceEvery(1));
        register(
                entries,
                GRAVEL_PIT,
                holderGetter.getOrThrow(PVJConfiguredFeatures.GRAVEL_PIT),
                RarityFilterPlacementModifier.of(20),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());
        register(
                entries,
                GOLD_PIT,
                holderGetter.getOrThrow(PVJConfiguredFeatures.GOLD_PIT),
                RarityFilterPlacementModifier.of(30),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(63), YOffset.fixed(73)),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());
        register(entries, BEACHED_KELP, holderGetter.getOrThrow(PVJConfiguredFeatures.BEACHED_KELP), modifiers(2));
        register(
                entries,
                DRIED_BEACHED_KELP,
                holderGetter.getOrThrow(PVJConfiguredFeatures.DRIED_BEACHED_KELP),
                modifiers(1),
                RarityFilterPlacementModifier.of(2));
        register(
                entries,
                GLOWING_BLUE_FUNGUS,
                holderGetter.getOrThrow(PVJConfiguredFeatures.GLOWING_BLUE_FUNGUS),
                CountPlacementModifier.of(30),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-64), YOffset.fixed(63)),
                BiomePlacementModifier.of());
        register(
                entries,
                MUDDY_BONES,
                holderGetter.getOrThrow(PVJConfiguredFeatures.MUDDY_BONES),
                modifiers(5),
                RandomOffsetPlacementModifier.vertically(UniformIntProvider.create(-5, 0)));
        register(
                entries,
                LOTUS_POND,
                holderGetter.getOrThrow(PVJConfiguredFeatures.LOTUS_POND),
                RarityFilterPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());
        register(entries, FLOATING_PINK_LOTUS, holderGetter.getOrThrow(PVJConfiguredFeatures.FLOATING_PINK_LOTUS), modifiers(4));
        register(
                entries,
                HOT_SPRINGS,
                holderGetter.getOrThrow(PVJConfiguredFeatures.HOT_SPRINGS),
                RarityFilterPlacementModifier.of(90),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());
        register(entries, OAK_BUSH, holderGetter.getOrThrow(PVJConfiguredFeatures.OAK_BUSH), modifiers(2), RarityFilterPlacementModifier.of(12));
        register(entries, YELLOW_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.YELLOW_WILDFLOWERS), onceEvery(10));
        register(entries, ORANGE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.ORANGE_WILDFLOWERS), onceEvery(10));
        register(entries, BLUE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.BLUE_WILDFLOWERS), onceEvery(10));
        register(entries, PURPLE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.PURPLE_WILDFLOWERS), onceEvery(10));
        register(entries, WHITE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.WHITE_WILDFLOWERS), onceEvery(10));
        register(entries, MIXED_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.MIXED_WILDFLOWERS), onceEvery(10));
        register(entries, MANY_YELLOW_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.YELLOW_WILDFLOWERS), onceEvery(3));
        register(entries, MANY_ORANGE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.ORANGE_WILDFLOWERS), onceEvery(3));
        register(entries, MANY_BLUE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.BLUE_WILDFLOWERS), onceEvery(3));
        register(entries, MANY_PURPLE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.PURPLE_WILDFLOWERS), onceEvery(3));
        register(entries, MANY_WHITE_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.WHITE_WILDFLOWERS), onceEvery(3));
        register(entries, MANY_MIXED_WILDFLOWERS, holderGetter.getOrThrow(PVJConfiguredFeatures.MIXED_WILDFLOWERS), onceEvery(3));
        register(entries, SLIME_NODULE, holderGetter.getOrThrow(PVJConfiguredFeatures.SLIME_NODULE), modifiers(5));
        register(
                entries,
                PINK_VINES,
                holderGetter.getOrThrow(PVJConfiguredFeatures.PINK_VINES),
                CountPlacementModifier.of(150),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(60), YOffset.fixed(256)),
                EnvironmentScanPlacementModifier.of(
                        Direction.UP,
                        BlockPredicate.matchingBlocks(Blocks.CHERRY_LEAVES),
                        12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
                BiomePlacementModifier.of());
    }

    @Override
    public String getName() {
        return "PVJ Placements";
    }
}