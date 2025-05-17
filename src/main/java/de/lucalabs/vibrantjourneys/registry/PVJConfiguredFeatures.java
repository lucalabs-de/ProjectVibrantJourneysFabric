package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.blocks.GroundcoverBlock;
import de.lucalabs.vibrantjourneys.blocks.ShortGrassBlock;
import de.lucalabs.vibrantjourneys.tags.PVJTags;
import de.lucalabs.vibrantjourneys.world.features.configurations.BushConfiguration;
import de.lucalabs.vibrantjourneys.world.features.configurations.FallenTreeConfiguration;
import de.lucalabs.vibrantjourneys.world.features.configurations.MultipleVegetationPatchConfiguration;
import de.lucalabs.vibrantjourneys.world.features.stateproviders.DirectionalStateProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static de.lucalabs.vibrantjourneys.world.features.configurations.FallenTreeConfiguration.FallenTreeVegetation;

public class PVJConfiguredFeatures extends FabricDynamicRegistryProvider {

    public static final RegistryKey<ConfiguredFeature<?, ?>> MOSS_CARPETS = createKey("moss_carpet");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LILYPADS = createKey("lilypads");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CAVE_ROOTS = createKey("cave_roots");

    public static final RegistryKey<ConfiguredFeature<?, ?>> BEACH_GRASS = createKey("beach_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SEA_OATS = createKey("sea_oats");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CATTAILS = createKey("cattails");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BARK_MUSHROOM = createKey("bark_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NATURAL_COBWEB = createKey("natural_cobweb");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHORT_GRASS = createKey("short_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_CACTUS = createKey("small_cactus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ICICLE = createKey("icicle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> REEDS = createKey("reeds");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PRICKLY_BUSH = createKey("prickly_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SANDY_SPROUTS = createKey("sandy_sprouts");

    public static final RegistryKey<ConfiguredFeature<?, ?>> TWIGS = createKey("twigs");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_LEAVES = createKey("fallen_leaves");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_FALLEN_LEAVES = createKey("dead_fallen_leaves");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PINECONES = createKey("pinecones");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SEASHELLS = createKey("seashells");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ROCKS = createKey("rocks");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ICE_CHUNKS = createKey("ice_chunks");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BONES = createKey("bones");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHARRED_BONES = createKey("charred_bones");

    public static final RegistryKey<ConfiguredFeature<?, ?>> WARPED_NETTLE = createKey("warped_nettle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRIMSON_NETTLE = createKey("crimson_nettle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CINDERCANE = createKey("cindercane");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWCAP = createKey("glowcap");

    public static final RegistryKey<ConfiguredFeature<?, ?>> OAK_FALLEN_TREE = createKey("oak_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIRCH_FALLEN_TREE = createKey("birch_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_FALLEN_TREE = createKey("spruce_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> JUNGLE_FALLEN_TREE = createKey("jungle_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ACACIA_FALLEN_TREE = createKey("acacia_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_OAK_FALLEN_TREE = createKey("dark_oak_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHERRY_FALLEN_TREE = createKey("cherry_fallen_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MANGROVE_FALLEN_TREE = createKey("mangrove_fallen_tree");

    public static final RegistryKey<ConfiguredFeature<?, ?>> SEA_PICKLE = createKey("sea_pickle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIDE_POOL = createKey("tide_pool");

    public static final RegistryKey<ConfiguredFeature<?, ?>> WATERGRASS = createKey("watergrass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BEACHED_KELP = createKey("beached_kelp");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DRIED_BEACHED_KELP = createKey("dried_beached_kelp");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_BLUE_FUNGUS = createKey("glowing_blue_fungus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOATING_PINK_LOTUS = createKey("floating_pink_lotus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OAK_BUSH = createKey("oak_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_WILDFLOWERS = createKey("yellow_wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORANGE_WILDFLOWERS = createKey("orange_wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_WILDFLOWERS = createKey("blue_wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_WILDFLOWERS = createKey("purple_wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WHITE_WILDFLOWERS = createKey("white_wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIXED_WILDFLOWERS = createKey("mixed_wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SLIME_NODULE = createKey("slime_nodule");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_VINES = createKey("pink_vines");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GRAVEL_PIT = createKey("gravel_pit");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLD_PIT = createKey("gold_pit");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MUDDY_BONES = createKey("muddy_bones");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LOTUS_POND = createKey("lotus_pond");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HOT_SPRINGS = createKey("hot_springs");

    public PVJConfiguredFeatures(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Entries entries, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        entries.add(key, new ConfiguredFeature<>(feature, config));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ProjectVibrantJourneys.MOD_ID, name));
    }

    private static RandomPatchFeatureConfig randomPatchConfig(int tries, int xzSpread, int ySpread, BlockState block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(block)), BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA, Blocks.WATER)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Fluids.LAVA, Fluids.WATER))
                )
        ));
    }

    private static RandomPatchFeatureConfig wildflower(FlowerbedBlock block, int tries) {
        DataPool.Builder<BlockState> builder = DataPool.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                builder.add(
                        block.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, i).with(FlowerbedBlock.FACING, direction), 1
                );
            }
        }

        return new RandomPatchFeatureConfig(
                tries, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(builder)))
        );
    }

    private static RandomPatchFeatureConfig smallCactusConfig(int tries, int xzSpread, int ySpread, BlockState block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(block)), BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.SAND)
                )
        ));
    }

    private static RandomPatchFeatureConfig deadFallenLeavesConfig(int tries, int xzSpread, int ySpread, BlockState block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(block)), BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW, Blocks.RED_SAND)),
                        BlockPredicate.not(BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.TERRACOTTA)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA, Blocks.WATER)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Fluids.LAVA, Fluids.WATER))
                )
        ));
    }

    private static RandomPatchFeatureConfig cattailConfig(int tries, int xzSpread, int ySpread, BlockState block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(block)), BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Fluids.LAVA))
                )
        ));
    }

    private static RandomPatchFeatureConfig groundcoverConfig(int tries, int xzSpread, int ySpread, Block block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(new RandomizedIntBlockStateProvider(new DirectionalStateProvider(block), GroundcoverBlock.MODEL, UniformIntProvider.create(0, 4))),
                BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.not(BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Fluids.LAVA))
                )
        ));
    }

    private static RandomPatchFeatureConfig iceChunksConfig(int tries, int xzSpread, int ySpread, Block block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(new RandomizedIntBlockStateProvider(new DirectionalStateProvider(block), GroundcoverBlock.MODEL, UniformIntProvider.create(0, 4))),
                BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.not(BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.SNOW)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Fluids.LAVA))
                )
        ));
    }

    private static RandomPatchFeatureConfig mossCarpetConfig(int tries, int xzSpread, int ySpread, Block block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(
                PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(block)),
                BlockPredicate.allOf(
                        BlockPredicate.replaceable(),
                        BlockPredicate.not(BlockPredicate.replaceable(Direction.DOWN.getVector())),
                        BlockPredicate.hasSturdyFace(Direction.DOWN.getVector(), Direction.UP),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW, Blocks.WATER)),
                        BlockPredicate.not(BlockPredicate.matchingBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Direction.DOWN.getVector(), Fluids.WATER)),
                        BlockPredicate.not(BlockPredicate.matchingFluids(Fluids.LAVA))
                )
        ));
    }

    private static FallenTreeConfiguration fallenTreeConfig(Block hollowLog, Block baseLog, List<FallenTreeVegetation> vegetationProviders, boolean canBeMossy) {
        return new FallenTreeConfiguration(hollowLog.getDefaultState(), baseLog.getDefaultState(), vegetationProviders, canBeMossy);
    }

    private static RandomPatchFeatureConfig ofRandomPatch(BlockState blockstate) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(blockstate)));
    }

    public static RandomPatchFeatureConfig ofRandomPatch(BlockStateProvider provider) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(provider));
    }

    private static RandomPatchFeatureConfig columnPlantWithFluid(int tries, int xzspread, int yspread, Block block, Fluid... fluids) {
        return new RandomPatchFeatureConfig(tries, xzspread, yspread, PlacedFeatures.createEntry(
                Feature.BLOCK_COLUMN,
                BlockColumnFeatureConfig.create(BiasedToBottomIntProvider.create(2, 4), BlockStateProvider.of(block)),
                BlockPredicate.allOf(
                        BlockPredicate.matchingBlocks(BlockPos.ZERO, Blocks.AIR),
                        BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ZERO),
                        BlockPredicate.anyOf(
                                BlockPredicate.matchingFluids(new BlockPos(1, -1, 0), List.of(fluids)),
                                BlockPredicate.matchingFluids(new BlockPos(1, -1, 0), List.of(fluids)),
                                BlockPredicate.matchingFluids(new BlockPos(-1, -1, 0), List.of(fluids)),
                                BlockPredicate.matchingFluids(new BlockPos(0, -1, 1), List.of(fluids)),
                                BlockPredicate.matchingFluids(new BlockPos(0, -1, -1), List.of(fluids))
                        )
                )
        ));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        final RandomizedIntBlockStateProvider SHORT_GRASS_STATE_PROVIDER = new RandomizedIntBlockStateProvider(
                BlockStateProvider.of(PVJBlocks.SHORT_GRASS),
                ShortGrassBlock.MODEL,
                UniformIntProvider.create(0, 6)
        );
        final RandomizedIntBlockStateProvider PINECONES_STATE_PROVIDER = new RandomizedIntBlockStateProvider(
                BlockStateProvider.of(PVJBlocks.SHORT_GRASS),
                GroundcoverBlock.MODEL,
                UniformIntProvider.create(0, 4)
        );
        final RandomizedIntBlockStateProvider PINK_PETALS_STATE_PROVIDER = new RandomizedIntBlockStateProvider(
                BlockStateProvider.of(Blocks.PINK_PETALS),
                FlowerbedBlock.FLOWER_AMOUNT,
                UniformIntProvider.create(FlowerbedBlock.field_42762, FlowerbedBlock.field_42763)
        );
        final List<FallenTreeConfiguration.FallenTreeVegetation> BASIC_FALLEN_TREE_VEGETATION = List.of(
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.GRASS), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.RED_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.BROWN_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.MOSS_CARPET), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
                new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
        );
        final List<FallenTreeVegetation> SPRUCE_FALLEN_TREE_VEGETATION = List.of(
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.GRASS), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.FERN), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.RED_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.BROWN_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.MOSS_CARPET), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
                new FallenTreeVegetation(PINECONES_STATE_PROVIDER, Optional.of("enablePinecones")),
                new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
        );
        final List<FallenTreeVegetation> ACACIA_FALLEN_TREE_VEGETATION = List.of(
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.GRASS), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.RED_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.BROWN_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
                new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
        );
        final List<FallenTreeVegetation> DARK_OAK_FALLEN_TREE_VEGETATION = List.of(
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.GRASS), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.FERN), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.RED_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.BROWN_MUSHROOM), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.MOSS_CARPET), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
                new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
        );
        final List<FallenTreeVegetation> CHERRY_FALLEN_TREE_VEGETATION = List.of(
                new FallenTreeVegetation(BlockStateProvider.of(Blocks.GRASS), Optional.empty()),
                new FallenTreeVegetation(BlockStateProvider.of(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
                new FallenTreeVegetation(PINK_PETALS_STATE_PROVIDER, Optional.empty()),
                new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
        );

        RegistryEntryLookup<ConfiguredFeature<?, ?>> holderGetter = wrapperLookup.createRegistryLookup().getOrThrow(RegistryKeys.CONFIGURED_FEATURE);

        register(
                entries,
                MOSS_CARPETS,
                Feature.RANDOM_PATCH,
                mossCarpetConfig(10, 7, 2, Blocks.MOSS_CARPET));

        register(
                entries,
                LILYPADS,
                Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        24,
                        7,
                        3,
                        PlacedFeatures.createEntry(PVJFeatures.LILYPAD, new ProbabilityConfig(0.75F))));
        register(
                entries,
                CAVE_ROOTS,
                Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        2,
                        2,
                        3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.HANGING_ROOTS)),
                                BlockPredicate.matchingBlockTag(new BlockPos(0, -1, 0), BlockTags.DIRT))));

        register(entries, BEACH_GRASS, Feature.RANDOM_PATCH, ofRandomPatch(PVJBlocks.BEACH_GRASS.getDefaultState()));
        register(entries, SEA_OATS, Feature.RANDOM_PATCH, ofRandomPatch(PVJBlocks.SEA_OATS.getDefaultState()));
        register(entries, CATTAILS, Feature.RANDOM_PATCH, cattailConfig(128, 7, 2, PVJBlocks.CATTAIL.getDefaultState()));
        register(entries, BARK_MUSHROOM, PVJFeatures.BARK_MUSHROOM, DefaultFeatureConfig.INSTANCE);
        register(entries, NATURAL_COBWEB, PVJFeatures.NATURAL_COBWEB, new ProbabilityConfig(0.1F));
        register(
                entries,
                SHORT_GRASS,
                Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                new RandomizedIntBlockStateProvider(
                                        BlockStateProvider.of(PVJBlocks.SHORT_GRASS),
                                        ShortGrassBlock.MODEL,
                                        UniformIntProvider.create(0, 6)))));
        register(entries, SMALL_CACTUS, Feature.RANDOM_PATCH, smallCactusConfig(8, 7, 3, PVJBlocks.SMALL_CACTUS.getDefaultState()));
        register(entries, ICICLE, PVJFeatures.ICICLE, DefaultFeatureConfig.INSTANCE);
        register(entries, REEDS, Feature.RANDOM_PATCH, cattailConfig(250, 12, 2, PVJBlocks.REEDS.getDefaultState()));
        register(entries, PRICKLY_BUSH, Feature.RANDOM_PATCH, randomPatchConfig(10, 7, 3, PVJBlocks.PRICKLY_BUSH.getDefaultState()));
        register(entries, SANDY_SPROUTS, Feature.RANDOM_PATCH, ofRandomPatch(PVJBlocks.SANDY_SPROUTS.getDefaultState()));

        register(entries, TWIGS, PVJFeatures.GROUNDCOVER, groundcoverConfig(4, 7, 3, PVJBlocks.TWIGS));
        register(entries, FALLEN_LEAVES, Feature.RANDOM_PATCH, randomPatchConfig(4, 7, 3, PVJBlocks.FALLEN_LEAVES.getDefaultState()));
        register(entries, DEAD_FALLEN_LEAVES, Feature.RANDOM_PATCH, deadFallenLeavesConfig(3, 7, 3, PVJBlocks.DEAD_FALLEN_LEAVES.getDefaultState()));
        register(entries, PINECONES, PVJFeatures.GROUNDCOVER, groundcoverConfig(4, 7, 3, PVJBlocks.PINECONES));
        register(entries, SEASHELLS, PVJFeatures.GROUNDCOVER, groundcoverConfig(4, 7, 3, PVJBlocks.SEASHELLS));
        register(entries, ROCKS, PVJFeatures.ROCKS, groundcoverConfig(4, 7, 3, PVJBlocks.ROCKS));
        register(entries, ICE_CHUNKS, PVJFeatures.GROUNDCOVER, iceChunksConfig(4, 7, 3, PVJBlocks.ICE_CHUNKS));
        register(entries, BONES, PVJFeatures.GROUNDCOVER, groundcoverConfig(1, 7, 3, PVJBlocks.BONES));
        register(entries, CHARRED_BONES, PVJFeatures.GROUNDCOVER, groundcoverConfig(50, 7, 3, PVJBlocks.CHARRED_BONES));

        register(entries, WARPED_NETTLE, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationFeatureConfig(BlockStateProvider.of(PVJBlocks.WARPED_NETTLE), 8, 4));
        register(entries, CRIMSON_NETTLE, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationFeatureConfig(BlockStateProvider.of(PVJBlocks.CRIMSON_NETTLE), 8, 4));
        register(entries, CINDERCANE, Feature.RANDOM_PATCH, columnPlantWithFluid(256, 7, 3, PVJBlocks.CINDERCANE, Fluids.LAVA, Fluids.FLOWING_LAVA));
        register(entries, GLOWCAP, Feature.RANDOM_PATCH, ofRandomPatch(PVJBlocks.GLOWCAP.getDefaultState()));

        register(entries, OAK_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.OAK_HOLLOW_LOG, Blocks.OAK_LOG, BASIC_FALLEN_TREE_VEGETATION, true));
        register(entries, BIRCH_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.BIRCH_HOLLOW_LOG, Blocks.BIRCH_LOG, BASIC_FALLEN_TREE_VEGETATION, true));
        register(entries, SPRUCE_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.SPRUCE_HOLLOW_LOG, Blocks.SPRUCE_LOG, SPRUCE_FALLEN_TREE_VEGETATION, true));
        register(entries, JUNGLE_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.JUNGLE_HOLLOW_LOG, Blocks.JUNGLE_LOG, BASIC_FALLEN_TREE_VEGETATION, true));
        register(entries, ACACIA_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.ACACIA_HOLLOW_LOG, Blocks.ACACIA_LOG, ACACIA_FALLEN_TREE_VEGETATION, false));
        register(entries, DARK_OAK_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.DARK_OAK_HOLLOW_LOG, Blocks.DARK_OAK_LOG, DARK_OAK_FALLEN_TREE_VEGETATION, true));
        register(entries, CHERRY_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.CHERRY_HOLLOW_LOG, Blocks.CHERRY_LOG, CHERRY_FALLEN_TREE_VEGETATION, false));
        register(entries, MANGROVE_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.MANGROVE_HOLLOW_LOG, Blocks.MANGROVE_LOG, BASIC_FALLEN_TREE_VEGETATION, true));

        register(entries, WATERGRASS, Feature.RANDOM_PATCH, cattailConfig(250, 12, 2, PVJBlocks.WATERGRASS.getDefaultState()));
        register(entries, BEACHED_KELP, PVJFeatures.BEACHED_KELP, new SingleStateFeatureConfig(PVJBlocks.BEACHED_KELP.getDefaultState()));
        register(entries, DRIED_BEACHED_KELP, PVJFeatures.BEACHED_KELP, new SingleStateFeatureConfig(PVJBlocks.DRIED_BEACHED_KELP.getDefaultState()));
        register(entries, GLOWING_BLUE_FUNGUS, PVJFeatures.GLOWING_BLUE_FUNGUS, DefaultFeatureConfig.INSTANCE);
        register(entries, FLOATING_PINK_LOTUS, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(24, 7, 3, PlacedFeatures.createEntry(PVJFeatures.FLOATING_PINK_LOTUS, new ProbabilityConfig(0.5F))));
        register(entries, OAK_BUSH, PVJFeatures.BUSH, new BushConfiguration(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()));
        register(entries, YELLOW_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.YELLOW_WILDFLOWERS, 40));
        register(entries, ORANGE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.ORANGE_WILDFLOWERS, 40));
        register(entries, BLUE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.BLUE_WILDFLOWERS, 40));
        register(entries, PURPLE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.PURPLE_WILDFLOWERS, 40));
        register(entries, WHITE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.WHITE_WILDFLOWERS, 40));
        register(entries, MIXED_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.MIXED_WILDFLOWERS, 40));
        register(entries, SLIME_NODULE, PVJFeatures.SLIME_NODULE, DefaultFeatureConfig.INSTANCE);
        register(entries, PINK_VINES, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(20, 4, 1, PlacedFeatures.createEntry(Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(
                List.of(
                        BlockColumnFeatureConfig.createLayer(
                                new WeightedListIntProvider(
                                        DataPool.<IntProvider>builder()
                                                .add(UniformIntProvider.create(0, 1), 1)
                                                .add(UniformIntProvider.create(0, 2), 1)
                                                .add(UniformIntProvider.create(0, 3), 1)
                                                .add(UniformIntProvider.create(0, 4), 1)
                                                .build()
                                ),
                                BlockStateProvider.of(PVJBlocks.PINK_VINES_PLANT)
                        ),
                        BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), BlockStateProvider.of(PVJBlocks.PINK_VINES))
                ),
                Direction.DOWN,
                BlockPredicate.IS_AIR,
                true
        ), BlockPredicate.matchingBlocks(Direction.UP.getVector(), Blocks.CHERRY_LEAVES))));

        register(entries, SEA_PICKLE, Feature.SEA_PICKLE, new CountConfig(1));

        register(entries, TIDE_POOL, PVJFeatures.POOL, new MultipleVegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                BlockStateProvider.of(Blocks.STONE),
                0.15F,
                List.of(PlacedFeatures.createEntry(
                                holderGetter.getOrThrow(SEA_PICKLE)),
                        PlacedFeatures.createEntry(holderGetter.getOrThrow(OceanConfiguredFeatures.SEAGRASS_TALL)),
                        PlacedFeatures.createEntry(holderGetter.getOrThrow(OceanConfiguredFeatures.KELP)),
                        PlacedFeatures.createEntry(holderGetter.getOrThrow(OceanConfiguredFeatures.KELP))),
                VerticalSurfaceType.FLOOR,
                ConstantIntProvider.create(3),
                1F,
                5,
                0.3F,
                UniformIntProvider.create(2, 3), 0.7F));
        register(entries, GRAVEL_PIT, PVJFeatures.GRAVEL_PIT, DefaultFeatureConfig.INSTANCE);
        register(entries, GOLD_PIT, PVJFeatures.GOLD_PIT, DefaultFeatureConfig.INSTANCE);
        register(entries, MUDDY_BONES, PVJFeatures.MUDDY_BONES, DefaultFeatureConfig.INSTANCE);
        register(entries, LOTUS_POND, PVJFeatures.LOTUS_POND, DefaultFeatureConfig.INSTANCE);
        register(entries, HOT_SPRINGS, PVJFeatures.HOT_SPRINGS, DefaultFeatureConfig.INSTANCE);
    }

    @Override
    public String getName() {
        return "PVJ Dynamic Registries";
    }
}