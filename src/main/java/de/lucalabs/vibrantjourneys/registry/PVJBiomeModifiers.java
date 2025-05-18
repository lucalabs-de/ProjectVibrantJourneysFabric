package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.config.PVJConfig;
import de.lucalabs.vibrantjourneys.tags.CompatTags;
import de.lucalabs.vibrantjourneys.tags.FabricTags;
import de.lucalabs.vibrantjourneys.tags.ForgeCompatTags;
import de.lucalabs.vibrantjourneys.tags.PVJTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class PVJBiomeModifiers {

//    public static final RegistryObject<Codec<PVJBiomeModifier>> BIOME_MODIFIER_SERIALIZER = BIOME_MODIFIER_SERIALIZERS.register("biome_modifier_serializer",
//            () -> RecordCodecBuilder.create(builder -> builder.group(
//                    TagKey.codec(Registries.BIOME).fieldOf("dimension").forGetter(PVJBiomeModifier::dimension),
//                    Biome.LIST_CODEC.listOf().fieldOf("biomes").forGetter(PVJBiomeModifier::biomes),
//                    Biome.LIST_CODEC.listOf().fieldOf("blacklist").forGetter(PVJBiomeModifier::blacklist),
//                    GenerationStep.Decoration.CODEC.fieldOf("decoration").forGetter(PVJBiomeModifier::decoration),
//                    PlacedFeature.CODEC.fieldOf("feature").forGetter(PVJBiomeModifier::feature),
//                    PrimitiveCodec.STRING.fieldOf("configOption").forGetter(PVJBiomeModifier::configOption)
//            ).apply(builder, PVJBiomeModifier::new)));
//
//    public static final RegistryObject<Codec<PVJSpawnModifier>> SPAWN_MODIFIER_SERIALIZER = BIOME_MODIFIER_SERIALIZERS.register("spawn_modifier_serializer",
//            () -> RecordCodecBuilder.create(builder -> builder.group(
//                    TagKey.codec(Registries.BIOME).fieldOf("dimension").forGetter(PVJSpawnModifier::dimension),
//                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(PVJSpawnModifier::biomes),
//                    MobCategory.CODEC.fieldOf("category").forGetter(PVJSpawnModifier::category),
//                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("data").forGetter(PVJSpawnModifier::data),
//                    PrimitiveCodec.STRING.fieldOf("configOption").forGetter(PVJSpawnModifier::configOption)
//            ).apply(builder, PVJSpawnModifier::new)));

    private PVJBiomeModifiers() {
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("initializing biome modifiers");

        initBiomeModifiers();
        initSpawnModifiers();
    }

    private static void initBiomeModifiers() {
        addBiomeModifier(
                PVJPlacements.TWIGS,
                overworld()
                        .and(blacklist(BiomeType.DESERT, BiomeType.OCEAN, BiomeType.BEACH, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> PVJConfig.enableTwigs); // TODO use proper config conditions
        addBiomeModifier(
                PVJPlacements.FALLEN_LEAVES,
                overworld()
                        .and(blacklist(BiomeType.DESERT, BiomeType.OCEAN, BiomeType.BEACH, BiomeType.MUSHROOM, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> PVJConfig.enableFallenLeaves);
        addBiomeModifier(
                PVJPlacements.DEAD_FALLEN_LEAVES,
                biomes(BiomeKeys.WOODED_BADLANDS),
                () -> PVJConfig.enableFallenLeaves);

        addBiomeModifier(
                PVJPlacements.DENSE_DEAD_FALLEN_LEAVES,
                biomes(BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
                () -> PVJConfig.enableFallenLeaves);
        addBiomeModifier(
                PVJPlacements.PINECONES,
                biomes(BiomeType.CONIFEROUS),
                () -> PVJConfig.enablePinecones);
        addBiomeModifier(
                PVJPlacements.SEASHELLS,
                biomes(BiomeType.OCEAN, BiomeType.BEACH),
                () -> PVJConfig.enableSeashells);
        addBiomeModifier(
                PVJPlacements.OCEAN_FLOOR_SEASHELLS,
                biomes(BiomeType.OCEAN, BiomeType.BEACH),
                () -> PVJConfig.enableSeashells);
        addBiomeModifier(
                PVJPlacements.ROCKS,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> PVJConfig.enableRocks);
        addBiomeModifier(
                PVJPlacements.BONES,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> PVJConfig.enableBones);
        addBiomeModifier(
                PVJPlacements.CAVE_ROCKS,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> PVJConfig.enableRocks);
        addBiomeModifier(
                PVJPlacements.CAVE_BONES,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> PVJConfig.enableBones);
        addBiomeModifier(
                PVJPlacements.ICE_CHUNKS,
                biomes(BiomeType.SNOWY).and(blacklist(BiomeKeys.SNOWY_BEACH)),
                () -> PVJConfig.enableIceChunks);
        addBiomeModifier(
                PVJPlacements.MOSS_CARPET,
                biomes(BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
                () -> PVJConfig.enableMossCarpets);
        addBiomeModifier(PVJPlacements.BARK_MUSHROOM, overworld(), () -> PVJConfig.enableBarkMushrooms);
        addBiomeModifier(
                PVJPlacements.SEA_OATS,
                biomes(BiomeType.BEACH).and(blacklist(BiomeType.VERY_COLD)),
                () -> PVJConfig.enableSeaOats);
        addBiomeModifier(
                PVJPlacements.BEACH_GRASS,
                biomes(BiomeType.BEACH).and(blacklist(BiomeType.VERY_COLD)),
                () -> PVJConfig.enableBeachGrass);
        addBiomeModifier(
                PVJPlacements.CATTAILS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> PVJConfig.enableCattails);
        addBiomeModifier(
                PVJPlacements.SHORT_GRASS,
                overworld()
                        .and(blacklist(BiomeType.MUSHROOM))
                        .and(blacklist(BiomeKeys.SNOWY_PLAINS)),
                () -> PVJConfig.enableShortGrass);
        addBiomeModifier(
                PVJPlacements.NATURAL_COBWEB,
                overworld()
                        .and(blacklist(BiomeType.MUSHROOM))
                        .and(blacklist(BiomeKeys.SNOWY_PLAINS)),
                () -> PVJConfig.enableNaturalCobwebs);
        addBiomeModifier(PVJPlacements.SMALL_CACTUS, biomes(BiomeType.DESERT), () -> true);
        addBiomeModifier(
                PVJPlacements.EXTRA_SEAGRASS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.DESERT, BiomeType.BADLANDS))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> PVJConfig.enableExtraSeagrass);
        addBiomeModifier(
                PVJPlacements.EXTRA_LILYPADS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.DESERT, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> PVJConfig.enableExtraLilypads);
        addBiomeModifier(PVJPlacements.EXTRA_GRASS, biomes(BiomeType.RIVER), () -> PVJConfig.enableExtraRiverGrass);
        addBiomeModifier(PVJPlacements.TIDE_POOL, biomes(BiomeKeys.STONY_SHORE), () -> PVJConfig.enableTidePools);
        addBiomeModifier(PVJPlacements.CAVE_ROOTS, overworld(), () -> PVJConfig.enableCaveRoots);
        addBiomeModifier(
                PVJPlacements.REEDS,
                biomes(BiomeType.PLAINS).and(blacklist(BiomeKeys.SNOWY_PLAINS)),
                () -> PVJConfig.enableReeds);
        addBiomeModifier(PVJPlacements.PRICKLY_BUSH, biomes(BiomeKeys.WOODED_BADLANDS), () -> PVJConfig.enablePricklyBush);
        addBiomeModifier(PVJPlacements.ICICLE, biomes(BiomeType.SNOWY), () -> PVJConfig.enableIcicles);
        addBiomeModifier(
                PVJPlacements.SANDY_SPROUTS,
                biomes(BiomeType.BEACH).and(blacklist(BiomeType.VERY_COLD)),
                () -> PVJConfig.enableSandySprouts);
        addBiomeModifier(
                PVJPlacements.WATERGRASS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> PVJConfig.enableWatergrass);
        addBiomeModifier(
                PVJPlacements.GRAVEL_PIT,
                biomes(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.LAKES,
                () -> PVJConfig.enableGravelPits);
        addBiomeModifier(
                PVJPlacements.GOLD_PIT,
                biomes(BiomeType.BADLANDS),
                GenerationStep.Feature.LAKES,
                () -> PVJConfig.enableGoldPits);
        addBiomeModifier(PVJPlacements.BEACHED_KELP, biomes(BiomeKeys.BEACH), () -> PVJConfig.enableBeachedKelp);
        addBiomeModifier(PVJPlacements.DRIED_BEACHED_KELP, biomes(BiomeKeys.BEACH), () -> PVJConfig.enableDriedBeachedKelp);
        addBiomeModifier(PVJPlacements.GLOWING_BLUE_FUNGUS, biomes(BiomeKeys.DEEP_DARK), () -> PVJConfig.enableGlowingBlueFungus);
        addBiomeModifier(PVJPlacements.MUDDY_BONES, biomes(BiomeKeys.MANGROVE_SWAMP), () -> PVJConfig.enableMuddyBones);
        addBiomeModifier(
                PVJPlacements.LOTUS_POND,
                biomes(BiomeKeys.CHERRY_GROVE),
                GenerationStep.Feature.LAKES,
                () -> PVJConfig.enableLotusPonds);
        addBiomeModifier(PVJPlacements.FLOATING_PINK_LOTUS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableFloatingPinkLotus);
        addBiomeModifier(PVJPlacements.HOT_SPRINGS, c -> c.hasTag(BiomeTags.IS_TAIGA), () -> PVJConfig.enableHotSprings);
        addBiomeModifier(PVJPlacements.OAK_BUSH, biomes(BiomeType.PLAINS), () -> true);
        addBiomeModifier(PVJPlacements.YELLOW_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.ORANGE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.BLUE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.PURPLE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.WHITE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MIXED_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MANY_YELLOW_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MANY_ORANGE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MANY_BLUE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MANY_PURPLE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MANY_WHITE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.MANY_MIXED_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> PVJConfig.enableWildflowers);
        addBiomeModifier(PVJPlacements.SLIME_NODULE, biomes(BiomeType.SWAMP), () -> PVJConfig.enableSlimeNodules);
        addBiomeModifier(PVJPlacements.PINK_VINES, biomes(BiomeKeys.CHERRY_GROVE), () -> PVJConfig.enablePinkVines);

        addBiomeModifier(PVJPlacements.OAK_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_OAK_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.BIRCH_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_BIRCH_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.SPRUCE_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_SPRUCE_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.JUNGLE_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_JUNGLE_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.ACACIA_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_ACACIA_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.DARK_OAK_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_DARK_OAK_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.CHERRY_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_CHERRY_LOGS), () -> PVJConfig.enableFallenTrees);
        addBiomeModifier(PVJPlacements.MANGROVE_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_MANGROVE_LOGS), () -> PVJConfig.enableFallenTrees);

        addBiomeModifier(PVJPlacements.CHARRED_BONES, nether(), () -> PVJConfig.enableCharredBones);
        addBiomeModifier(PVJPlacements.GLOWCAP, nether(), () -> PVJConfig.enableGlowcap);
        addBiomeModifier(PVJPlacements.CINDERCANE, nether(), () -> PVJConfig.enableCindercane);
        addBiomeModifier(PVJPlacements.WARPED_NETTLE, biomes(BiomeKeys.WARPED_FOREST), () -> PVJConfig.enableNetherNettles);
        addBiomeModifier(PVJPlacements.CRIMSON_NETTLE, biomes(BiomeKeys.CRIMSON_FOREST), () -> PVJConfig.enableNetherNettles);
    }

    private static void initSpawnModifiers() {
        addSpawnModifier(
                "tropical_fish_in_jungles",
                overworld().and(biomes(BiomeType.JUNGLE)),
                SpawnGroup.WATER_AMBIENT,
                EntityType.TROPICAL_FISH,
                25,
                5,
                5,
                () -> PVJConfig.enableJungleTropicalFish);
    }

    private static void addBiomeModifier(
            RegistryKey<PlacedFeature> feature,
            Predicate<BiomeSelectionContext> condition,
            Supplier<Boolean> configCondition) {
        addBiomeModifier(feature, condition, GenerationStep.Feature.VEGETAL_DECORATION, configCondition);
    }

    private static void addBiomeModifier(
            RegistryKey<PlacedFeature> feature,
            Predicate<BiomeSelectionContext> condition,
            GenerationStep.Feature featureType,
            Supplier<Boolean> configCondition) {
        if (configCondition.get()) {
            BiomeModifications.addFeature(condition, featureType, feature);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void addSpawnModifier(
            String spawnName,
            Predicate<BiomeSelectionContext> condition,
            SpawnGroup group,
            EntityType<?> entityType,
            int weight,
            int minGroupSize,
            int maxGroupSize,
            Supplier<Boolean> configCondition
    ) {
        if (configCondition.get()) {
            BiomeModifications.addSpawn(condition, group, entityType, weight, minGroupSize, maxGroupSize);
        }
    }

    private static List<RegistryKey<Biome>> veryCold() {
        return List.of(
                BiomeKeys.FROZEN_RIVER,
                BiomeKeys.SNOWY_PLAINS,
                BiomeKeys.SNOWY_BEACH,
                BiomeKeys.SNOWY_SLOPES,
                BiomeKeys.ICE_SPIKES,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.DEEP_FROZEN_OCEAN,
                BiomeKeys.JAGGED_PEAKS);
    }

    private static Predicate<BiomeSelectionContext> overworld() {
        return c -> c.hasTag(BiomeTags.IS_OVERWORLD);
    }

    private static Predicate<BiomeSelectionContext> nether() {
        return c -> c.hasTag(BiomeTags.IS_NETHER);
    }

    @SafeVarargs
    private static Predicate<BiomeSelectionContext> biomes(RegistryKey<Biome>... biomes) {
        Predicate<BiomeSelectionContext> pred = (c -> true);
        for (RegistryKey<Biome> biome : biomes) {
            pred = pred.and(c -> c.getBiomeKey().equals(biome));
        }

        return pred;
    }

    private static Predicate<BiomeSelectionContext> biomes(BiomeType... biomes) {
        Predicate<BiomeSelectionContext> pred = (c -> true);
        for (BiomeType t : biomes) {
            pred = pred.or(fromBiomeType(t));
        }

        return pred;
    }

    @SafeVarargs
    private static Predicate<BiomeSelectionContext> blacklist(RegistryKey<Biome>... biomes) {
        Predicate<BiomeSelectionContext> pred = (c -> true);
        for (RegistryKey<Biome> biome : biomes) {
            pred = pred.and(c -> !c.getBiomeKey().equals(biome));
        }

        return pred;
    }

    private static Predicate<BiomeSelectionContext> blacklist(BiomeType... biomes) {
        Predicate<BiomeSelectionContext> pred = (c -> true);

        for (BiomeType t : biomes) {
            pred = pred.and(fromBiomeType(t).negate());
        }

        return pred;
    }

    private static Predicate<BiomeSelectionContext> fromBiomeType(BiomeType t) {
        return switch (t) {
            case FOREST -> c -> c.hasTag(BiomeTags.IS_FOREST);
            case PLAINS -> c -> c.hasTag(FabricTags.IS_PLAINS) || c.hasTag(FabricTags.PLAINS);
            case OCEAN -> c -> c.hasTag(BiomeTags.IS_OCEAN);
            case BEACH -> c -> c.hasTag(BiomeTags.IS_BEACH);
            case BADLANDS -> c -> c.hasTag(BiomeTags.IS_BADLANDS);
            case RIVER -> c -> c.hasTag(BiomeTags.IS_RIVER);
            case CONIFEROUS -> c -> c.hasTag(ForgeCompatTags.IS_CONIFEROUS) || c.hasTag(CompatTags.IS_CONIFEROUS);
            case SNOWY ->
                    c -> c.hasTag(ForgeCompatTags.IS_SNOWY) || c.hasTag(FabricTags.IS_SNOWY) || c.hasTag(FabricTags.SNOWY);
            case MUSHROOM -> c -> c.hasTag(FabricTags.IS_MUSHROOM) || c.hasTag(FabricTags.MUSHROOM);
            case DESERT -> c -> c.hasTag(FabricTags.IS_DESERT) || c.hasTag(FabricTags.DESERT);
            case SWAMP -> c -> c.hasTag(FabricTags.IS_SWAMP) || c.hasTag(FabricTags.SWAMP);
            case TAIGA -> c -> c.hasTag(BiomeTags.IS_TAIGA);
            case JUNGLE -> c -> c.hasTag(BiomeTags.IS_JUNGLE);
            case VERY_COLD -> c -> veryCold().contains(c.getBiomeKey());
        };
    }

    private enum BiomeType {
        FOREST, PLAINS, OCEAN, DESERT, BEACH, BADLANDS, RIVER, CONIFEROUS, SNOWY, MUSHROOM, SWAMP, TAIGA, JUNGLE, VERY_COLD
    }

}