package de.lucalabs.vibrantjourneys.registry;

import java.util.List;
import java.util.Optional;

public class PVJConfiguredFeatures {
  public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, ProjectVibrantJourneys.MOD_ID);

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

  public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

    final RandomizedIntStateProvider SHORT_GRASS_STATE_PROVIDER = new RandomizedIntStateProvider(
      BlockStateProvider.simple(PVJBlocks.SHORT_GRASS),
      ShortGrassBlock.MODEL,
      UniformInt.of(0, 6)
    );
    final RandomizedIntStateProvider PINECONES_STATE_PROVIDER = new RandomizedIntStateProvider(
      BlockStateProvider.simple(PVJBlocks.SHORT_GRASS),
      GroundcoverBlock.MODEL,
      UniformInt.of(0, 4)
    );
    final RandomizedIntStateProvider PINK_PETALS_STATE_PROVIDER = new RandomizedIntStateProvider(
      BlockStateProvider.simple(Blocks.PINK_PETALS),
      FlowerbedBlock.AMOUNT,
      UniformInt.of(FlowerbedBlock.MIN_FLOWERS, FlowerbedBlock.MAX_FLOWERS)
    );
    final List<FallenTreeVegetation> BASIC_FALLEN_TREE_VEGETATION = List.of(
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.GRASS), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.RED_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.BROWN_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.MOSS_CARPET), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
      new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
    );
    final List<FallenTreeVegetation> SPRUCE_FALLEN_TREE_VEGETATION = List.of(
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.GRASS), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.FERN), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.RED_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.BROWN_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.MOSS_CARPET), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
      new FallenTreeVegetation(PINECONES_STATE_PROVIDER, Optional.of("enablePinecones")),
      new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
    );
    final List<FallenTreeVegetation> ACACIA_FALLEN_TREE_VEGETATION = List.of(
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.GRASS), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.RED_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.BROWN_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
      new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
    );
    final List<FallenTreeVegetation> DARK_OAK_FALLEN_TREE_VEGETATION = List.of(
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.GRASS), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.FERN), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.RED_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.BROWN_MUSHROOM), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.MOSS_CARPET), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
      new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
    );
    final List<FallenTreeVegetation> CHERRY_FALLEN_TREE_VEGETATION = List.of(
      new FallenTreeVegetation(BlockStateProvider.simple(Blocks.GRASS), Optional.empty()),
      new FallenTreeVegetation(BlockStateProvider.simple(PVJBlocks.FALLEN_LEAVES), Optional.of("enableFallenLeaves")),
      new FallenTreeVegetation(PINK_PETALS_STATE_PROVIDER, Optional.empty()),
      new FallenTreeVegetation(SHORT_GRASS_STATE_PROVIDER, Optional.of("enableShortGrass"))
    );

    HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
    register(context, MOSS_CARPETS, Feature.RANDOM_PATCH, mossCarpetConfig(10, 7, 2, Blocks.MOSS_CARPET));
    register(context, LILYPADS, Feature.RANDOM_PATCH, new RandomPatchConfiguration(24, 7, 3, PlacementUtils.onlyWhenEmpty(PVJFeatures.LILYPAD, new ProbabilityFeatureConfiguration(0.75F))));
    register(context, CAVE_ROOTS, Feature.RANDOM_PATCH, new RandomPatchConfiguration(2, 2, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.HANGING_ROOTS)), BlockPredicate.matchesTag(new BlockPos(0, -1, 0), BlockTags.DIRT))));

    register(context, BEACH_GRASS, Feature.RANDOM_PATCH, simpleRandomPatch(PVJBlocks.BEACH_GRASS.getDefaultState()));
    register(context, SEA_OATS, Feature.RANDOM_PATCH, simpleRandomPatch(PVJBlocks.SEA_OATS.getDefaultState()));
    register(context, CATTAILS, Feature.RANDOM_PATCH, cattailConfig(128, 7, 2, PVJBlocks.CATTAIL.getDefaultState()));
    register(context, BARK_MUSHROOM, PVJFeatures.BARK_MUSHROOM, NoneFeatureConfiguration.INSTANCE);
    register(context, NATURAL_COBWEB, PVJFeatures.NATURAL_COBWEB, new ProbabilityFeatureConfiguration(0.1F));
    register(context, SHORT_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new RandomizedIntStateProvider(BlockStateProvider.simple(PVJBlocks.SHORT_GRASS), ShortGrassBlock.MODEL, UniformInt.of(0, 6)))));
    register(context, SMALL_CACTUS, Feature.RANDOM_PATCH, smallCactusConfig(8, 7, 3, PVJBlocks.SMALL_CACTUS.getDefaultState()));
    register(context, ICICLE, PVJFeatures.ICICLE, NoneFeatureConfiguration.INSTANCE);
    register(context, REEDS, Feature.RANDOM_PATCH, cattailConfig(250, 12, 2, PVJBlocks.REEDS.getDefaultState()));
    register(context, PRICKLY_BUSH, Feature.RANDOM_PATCH, randomPatchConfig(10, 7, 3, PVJBlocks.PRICKLY_BUSH.getDefaultState()));
    register(context, SANDY_SPROUTS, Feature.RANDOM_PATCH, simpleRandomPatch(PVJBlocks.SANDY_SPROUTS.getDefaultState()));

    register(context, TWIGS, PVJFeatures.GROUNDCOVER, groundcoverConfig(4, 7, 3, PVJBlocks.TWIGS));
    register(context, FALLEN_LEAVES, Feature.RANDOM_PATCH, randomPatchConfig(4, 7, 3, PVJBlocks.FALLEN_LEAVES.getDefaultState()));
    register(context, DEAD_FALLEN_LEAVES, Feature.RANDOM_PATCH, deadFallenLeavesConfig(3, 7, 3, PVJBlocks.DEAD_FALLEN_LEAVES.getDefaultState()));
    register(context, PINECONES, PVJFeatures.GROUNDCOVER, groundcoverConfig(4, 7, 3, PVJBlocks.PINECONES));
    register(context, SEASHELLS, PVJFeatures.GROUNDCOVER, groundcoverConfig(4, 7, 3, PVJBlocks.SEASHELLS));
    register(context, ROCKS, PVJFeatures.ROCKS, groundcoverConfig(4, 7, 3, PVJBlocks.ROCKS));
    register(context, ICE_CHUNKS, PVJFeatures.GROUNDCOVER, iceChunksConfig(4, 7, 3, PVJBlocks.ICE_CHUNKS));
    register(context, BONES, PVJFeatures.GROUNDCOVER, groundcoverConfig(1, 7, 3, PVJBlocks.BONES));
    register(context, CHARRED_BONES, PVJFeatures.GROUNDCOVER, groundcoverConfig(50, 7, 3, PVJBlocks.CHARRED_BONES));

    register(context, WARPED_NETTLE, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationConfig(BlockStateProvider.simple(PVJBlocks.WARPED_NETTLE), 8, 4));
    register(context, CRIMSON_NETTLE, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationConfig(BlockStateProvider.simple(PVJBlocks.CRIMSON_NETTLE), 8, 4));
    register(context, CINDERCANE, Feature.RANDOM_PATCH, columnPlantWithFluid(256, 7, 3, PVJBlocks.CINDERCANE, Fluids.LAVA, Fluids.FLOWING_LAVA));
    register(context, GLOWCAP, Feature.RANDOM_PATCH, simpleRandomPatch(PVJBlocks.GLOWCAP.getDefaultState()));

    register(context, OAK_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.OAK_HOLLOW_LOG, Blocks.OAK_LOG, BASIC_FALLEN_TREE_VEGETATION, true));
    register(context, BIRCH_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.BIRCH_HOLLOW_LOG, Blocks.BIRCH_LOG, BASIC_FALLEN_TREE_VEGETATION, true));
    register(context, SPRUCE_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.SPRUCE_HOLLOW_LOG, Blocks.SPRUCE_LOG, SPRUCE_FALLEN_TREE_VEGETATION, true));
    register(context, JUNGLE_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.JUNGLE_HOLLOW_LOG, Blocks.JUNGLE_LOG, BASIC_FALLEN_TREE_VEGETATION, true));
    register(context, ACACIA_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.ACACIA_HOLLOW_LOG, Blocks.ACACIA_LOG, ACACIA_FALLEN_TREE_VEGETATION, false));
    register(context, DARK_OAK_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.DARK_OAK_HOLLOW_LOG, Blocks.DARK_OAK_LOG, DARK_OAK_FALLEN_TREE_VEGETATION, true));
    register(context, CHERRY_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.CHERRY_HOLLOW_LOG, Blocks.CHERRY_LOG, CHERRY_FALLEN_TREE_VEGETATION, false));
    register(context, MANGROVE_FALLEN_TREE, PVJFeatures.FALLEN_TREE, fallenTreeConfig(PVJBlocks.MANGROVE_HOLLOW_LOG, Blocks.MANGROVE_LOG, BASIC_FALLEN_TREE_VEGETATION, true));

    register(context, WATERGRASS, Feature.RANDOM_PATCH, cattailConfig(250, 12, 2, PVJBlocks.WATERGRASS.getDefaultState()));
    register(context, BEACHED_KELP, PVJFeatures.BEACHED_KELP, new BlockStateConfiguration(PVJBlocks.BEACHED_KELP.getDefaultState()));
    register(context, DRIED_BEACHED_KELP, PVJFeatures.BEACHED_KELP, new BlockStateConfiguration(PVJBlocks.DRIED_BEACHED_KELP.getDefaultState()));
    register(context, GLOWING_BLUE_FUNGUS, PVJFeatures.GLOWING_BLUE_FUNGUS, NoneFeatureConfiguration.INSTANCE);
    register(context, FLOATING_PINK_LOTUS, Feature.RANDOM_PATCH, new RandomPatchConfiguration(24, 7, 3, PlacementUtils.onlyWhenEmpty(PVJFeatures.FLOATING_PINK_LOTUS, new ProbabilityFeatureConfiguration(0.5F))));
    register(context, OAK_BUSH, PVJFeatures.BUSH, new BushConfiguration(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()));
    register(context, YELLOW_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.YELLOW_WILDFLOWERS,40));
    register(context, ORANGE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.ORANGE_WILDFLOWERS,40));
    register(context, BLUE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.BLUE_WILDFLOWERS,40));
    register(context, PURPLE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.PURPLE_WILDFLOWERS,40));
    register(context, WHITE_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.WHITE_WILDFLOWERS,40));
    register(context, MIXED_WILDFLOWERS, Feature.RANDOM_PATCH, wildflower((FlowerbedBlock) PVJBlocks.MIXED_WILDFLOWERS,40));
    register(context, SLIME_NODULE, PVJFeatures.SLIME_NODULE, NoneFeatureConfiguration.INSTANCE);
    register(context, PINK_VINES, Feature.RANDOM_PATCH, new RandomPatchConfiguration(20, 4, 1, PlacementUtils.filtered(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
      List.of(
        BlockColumnConfiguration.layer(
          new WeightedListInt(
            SimpleWeightedRandomList.<IntProvider>builder()
              .add(UniformInt.of(0, 1), 1)
              .add(UniformInt.of(0, 2), 1)
              .add(UniformInt.of(0, 3), 1)
              .add(UniformInt.of(0, 4), 1)
              .build()
          ),
          BlockStateProvider.simple(PVJBlocks.PINK_VINES_PLANT)
        ),
        BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(PVJBlocks.PINK_VINES))
      ),
      Direction.DOWN,
      BlockPredicate.ONLY_IN_AIR_PREDICATE,
      true
    ), BlockPredicate.matchesBlocks(Direction.UP.getNormal(), Blocks.CHERRY_LEAVES))));

    register(context, SEA_PICKLE, Feature.SEA_PICKLE, new CountConfiguration(1));
    register(context, TIDE_POOL, PVJFeatures.POOL, new MultipleVegetationPatchConfiguration(
      BlockTags.LUSH_GROUND_REPLACEABLE,
      BlockStateProvider.simple(Blocks.STONE),
      0.15F,
      List.of(PlacementUtils.inlinePlaced(
          holderGetter.getOrThrow(SEA_PICKLE)),
        PlacementUtils.inlinePlaced(holderGetter.getOrThrow(AquaticFeatures.SEAGRASS_TALL)),
        PlacementUtils.inlinePlaced(holderGetter.getOrThrow(AquaticFeatures.KELP)),
        PlacementUtils.inlinePlaced(holderGetter.getOrThrow(AquaticFeatures.KELP))),
      CaveSurface.FLOOR,
      ConstantInt.of(3),
      1F,
      5,
      0.3F,
      UniformInt.of(2, 3), 0.7F));
    register(context, GRAVEL_PIT, PVJFeatures.GRAVEL_PIT, NoneFeatureConfiguration.INSTANCE);
    register(context, GOLD_PIT, PVJFeatures.GOLD_PIT, NoneFeatureConfiguration.INSTANCE);
    register(context, MUDDY_BONES, PVJFeatures.MUDDY_BONES, NoneFeatureConfiguration.INSTANCE);
    register(context, LOTUS_POND, PVJFeatures.LOTUS_POND, NoneFeatureConfiguration.INSTANCE);
    register(context, HOT_SPRINGS, PVJFeatures.HOT_SPRINGS, NoneFeatureConfiguration.INSTANCE);
  }

  private static RegistryKey<ConfiguredFeature<?, ?>> createKey(String name) {
    return RegistryKey.create(Registries.CONFIGURED_FEATURE, new Identifier(ProjectVibrantJourneys.MOD_ID, name));
  }

  private static <FC extends FeatureConfig, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
    context.register(key, new ConfiguredFeature<>(feature, config));
  }

  private static RandomPatchConfiguration randomPatchConfig(int tries, int xzSpread, int ySpread, BlockState block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(BlockStateProvider.simple(block)), BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA, Blocks.WATER)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA, Fluids.WATER))
      )
    ));
  }

  private static RandomPatchConfiguration wildflower(FlowerbedBlock block, int tries) {
    SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
    for (int i = 1; i <= 4; i++) {
      for (Direction direction : Direction.Type.HORIZONTAL) {
        builder.add(
          block.getDefaultState().with(FlowerbedBlock.AMOUNT, Integer.valueOf(i)).with(FlowerbedBlock.FACING, direction), 1
        );
      }
    }

    return new RandomPatchConfiguration(
      tries, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder)))
    );
  }

  private static RandomPatchConfiguration smallCactusConfig(int tries, int xzSpread, int ySpread, BlockState block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(BlockStateProvider.simple(block)), BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.SAND)
      )
    ));
  }

  private static RandomPatchConfiguration deadFallenLeavesConfig(int tries, int xzSpread, int ySpread, BlockState block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(BlockStateProvider.simple(block)), BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW, Blocks.RED_SAND)),
        BlockPredicate.not(BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.TERRACOTTA)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA, Blocks.WATER)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA, Fluids.WATER))
      )
    ));
  }

  private static RandomPatchConfiguration cattailConfig(int tries, int xzSpread, int ySpread, BlockState block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(BlockStateProvider.simple(block)), BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA))
      )
    ));
  }

  private static RandomPatchConfiguration groundcoverConfig(int tries, int xzSpread, int ySpread, Block block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(new RandomizedIntStateProvider(new DirectionalStateProvider(block), GroundcoverBlock.MODEL, UniformInt.of(0, 4))),
      BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.not(BlockPredicate.matchesTag(Direction.DOWN.getNormal(), PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA))
      )
    ));
  }

  private static RandomPatchConfiguration iceChunksConfig(int tries, int xzSpread, int ySpread, Block block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(new RandomizedIntStateProvider(new DirectionalStateProvider(block), GroundcoverBlock.MODEL, UniformInt.of(0, 4))),
      BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.not(BlockPredicate.matchesTag(Direction.DOWN.getNormal(), PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.SNOW)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA))
      )
    ));
  }

  private static RandomPatchConfiguration mossCarpetConfig(int tries, int xzSpread, int ySpread, Block block) {
    return new RandomPatchConfiguration(tries, xzSpread, ySpread, PlacementUtils.filtered(
      PVJFeatures.SIMPLE_BLOCK_MATCH_WATER,
      new SimpleBlockConfiguration(BlockStateProvider.simple(block)),
      BlockPredicate.allOf(
        BlockPredicate.replaceable(),
        BlockPredicate.not(BlockPredicate.replaceable(Direction.DOWN.getNormal())),
        BlockPredicate.hasSturdyFace(Direction.DOWN.getNormal(), Direction.UP),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SNOW, Blocks.WATER)),
        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.SNOW, Blocks.TALL_GRASS, Blocks.LARGE_FERN, Blocks.LAVA)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Direction.DOWN.getNormal(), Fluids.WATER)),
        BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA))
      )
    ));
  }

  private static FallenTreeConfiguration fallenTreeConfig(Block hollowLog, Block baseLog, List<FallenTreeVegetation> vegetationProviders, boolean canBeMossy) {
    return new FallenTreeConfiguration(hollowLog.getDefaultState(), baseLog.getDefaultState(), vegetationProviders, canBeMossy);
  }

  private static RandomPatchConfiguration simpleRandomPatch(BlockState blockstate) {
    return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(blockstate)));
  }

  public static RandomPatchConfiguration simpleRandomPatch(BlockStateProvider provider) {
    return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(provider));
  }

  private static RandomPatchConfiguration columnPlantWithFluid(int tries, int xzspread, int yspread, Block block, Fluid... fluids) {
    return new RandomPatchConfiguration(tries, xzspread, yspread, PlacementUtils.filtered(
      Feature.BLOCK_COLUMN,
      BlockColumnConfiguration.simple(BiasedToBottomInt.of(2, 4), BlockStateProvider.simple(block)),
      BlockPredicate.allOf(
        BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),
        BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ZERO),
        BlockPredicate.anyOf(
          BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), List.of(fluids)),
          BlockPredicate.matchesFluids(new BlockPos(1, -1, 0), List.of(fluids)),
          BlockPredicate.matchesFluids(new BlockPos(-1, -1, 0), List.of(fluids)),
          BlockPredicate.matchesFluids(new BlockPos(0, -1, 1), List.of(fluids)),
          BlockPredicate.matchesFluids(new BlockPos(0, -1, -1), List.of(fluids))
        )
      )
    ));
  }

}