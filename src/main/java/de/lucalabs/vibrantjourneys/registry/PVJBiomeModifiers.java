package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
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
                () -> true); // TODO use proper config conditions
        addBiomeModifier(
                PVJPlacements.FALLEN_LEAVES,
                overworld()
                        .and(blacklist(BiomeType.DESERT, BiomeType.OCEAN, BiomeType.BEACH, BiomeType.MUSHROOM, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.DEAD_FALLEN_LEAVES,
                biomes(BiomeKeys.WOODED_BADLANDS),
                () -> true);

        addBiomeModifier(
                PVJPlacements.DENSE_DEAD_FALLEN_LEAVES,
                biomes(BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
                () -> true);
        addBiomeModifier(
                PVJPlacements.PINECONES,
                biomes(BiomeType.CONIFEROUS),
                () -> true);
        addBiomeModifier(
                PVJPlacements.SEASHELLS,
                biomes(BiomeType.OCEAN, BiomeType.BEACH),
                () -> true);
        addBiomeModifier(
                PVJPlacements.OCEAN_FLOOR_SEASHELLS,
                biomes(BiomeType.OCEAN, BiomeType.BEACH),
                () -> true);
        addBiomeModifier(
                PVJPlacements.ROCKS,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.BONES,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.CAVE_ROCKS,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.CAVE_BONES,
                overworld().and(blacklist(BiomeType.MUSHROOM, BiomeType.VERY_COLD)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.ICE_CHUNKS,
                biomes(BiomeType.SNOWY).and(blacklist(BiomeKeys.SNOWY_BEACH)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.MOSS_CARPET,
                biomes(BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
                () -> true);
        addBiomeModifier(PVJPlacements.BARK_MUSHROOM, overworld(), () -> true);
        addBiomeModifier(PVJPlacements.SEA_OATS, biomes(BiomeType.BEACH).and(blacklist(BiomeType.VERY_COLD)), () -> true);
        addBiomeModifier(PVJPlacements.BEACH_GRASS, biomes(BiomeType.BEACH).and(blacklist(BiomeType.VERY_COLD)), () -> true);
        addBiomeModifier(
                PVJPlacements.CATTAILS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.SHORT_GRASS,
                overworld()
                        .and(blacklist(BiomeType.MUSHROOM))
                        .and(blacklist(BiomeKeys.SNOWY_PLAINS)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.NATURAL_COBWEB,
                overworld()
                        .and(blacklist(BiomeType.MUSHROOM))
                        .and(blacklist(BiomeKeys.SNOWY_PLAINS)),
                () -> true);
        addBiomeModifier(PVJPlacements.SMALL_CACTUS, biomes(BiomeType.DESERT), () -> true);
        addBiomeModifier(
                PVJPlacements.EXTRA_SEAGRASS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.DESERT, BiomeType.BADLANDS))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.EXTRA_LILYPADS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.DESERT, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> true);
        addBiomeModifier(PVJPlacements.EXTRA_GRASS, biomes(BiomeType.RIVER), () -> true);
        addBiomeModifier(PVJPlacements.TIDE_POOL, biomes(BiomeKeys.STONY_SHORE), () -> true);
        addBiomeModifier(PVJPlacements.CAVE_ROOTS, overworld(), () -> true);
        addBiomeModifier(PVJPlacements.REEDS, biomes(BiomeType.PLAINS).and(blacklist(BiomeKeys.SNOWY_PLAINS)), () -> true);
        addBiomeModifier(PVJPlacements.PRICKLY_BUSH, biomes(BiomeKeys.WOODED_BADLANDS), () -> true);
        addBiomeModifier(PVJPlacements.ICICLE, biomes(BiomeType.SNOWY), () -> true);
        addBiomeModifier(PVJPlacements.SANDY_SPROUTS, biomes(BiomeType.BEACH).and(blacklist(BiomeType.VERY_COLD)), () -> true);
        addBiomeModifier(
                PVJPlacements.WATERGRASS,
                overworld()
                        .and(blacklist(BiomeType.OCEAN, BiomeType.BEACH, BiomeType.BADLANDS, BiomeType.VERY_COLD))
                        .and(blacklist(BiomeKeys.STONY_SHORE)),
                () -> true);
        addBiomeModifier(
                PVJPlacements.GRAVEL_PIT,
                biomes(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.LAKES,
                () -> true);
        addBiomeModifier(PVJPlacements.GOLD_PIT, biomes(BiomeType.BADLANDS), GenerationStep.Feature.LAKES, () -> true);
        addBiomeModifier(PVJPlacements.BEACHED_KELP, biomes(BiomeKeys.BEACH), () -> true);
        addBiomeModifier(PVJPlacements.DRIED_BEACHED_KELP, biomes(BiomeKeys.BEACH), () -> true);
        addBiomeModifier(PVJPlacements.GLOWING_BLUE_FUNGUS, biomes(BiomeKeys.DEEP_DARK), () -> true);
        addBiomeModifier(PVJPlacements.MUDDY_BONES, biomes(BiomeKeys.MANGROVE_SWAMP), () -> true);
        addBiomeModifier(PVJPlacements.LOTUS_POND, biomes(BiomeKeys.CHERRY_GROVE), GenerationStep.Feature.LAKES, () -> true);
        addBiomeModifier(PVJPlacements.FLOATING_PINK_LOTUS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.HOT_SPRINGS, c -> c.hasTag(BiomeTags.IS_TAIGA), () -> true);
        addBiomeModifier(PVJPlacements.OAK_BUSH, biomes(BiomeType.PLAINS), () -> true);
        addBiomeModifier(PVJPlacements.YELLOW_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> true);
        addBiomeModifier(PVJPlacements.ORANGE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> true);
        addBiomeModifier(PVJPlacements.BLUE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> true);
        addBiomeModifier(PVJPlacements.PURPLE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> true);
        addBiomeModifier(PVJPlacements.WHITE_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> true);
        addBiomeModifier(PVJPlacements.MIXED_WILDFLOWERS, biomes(BiomeKeys.MEADOW), () -> true);
        addBiomeModifier(PVJPlacements.MANY_YELLOW_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.MANY_ORANGE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.MANY_BLUE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.MANY_PURPLE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.MANY_WHITE_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.MANY_MIXED_WILDFLOWERS, biomes(BiomeKeys.FLOWER_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.SLIME_NODULE, biomes(BiomeType.SWAMP), () -> true);
        addBiomeModifier(PVJPlacements.PINK_VINES, biomes(BiomeKeys.CHERRY_GROVE), () -> true);

        addBiomeModifier(PVJPlacements.OAK_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_OAK_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.BIRCH_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_BIRCH_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.SPRUCE_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_SPRUCE_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.JUNGLE_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_JUNGLE_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.ACACIA_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_ACACIA_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.DARK_OAK_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_DARK_OAK_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.CHERRY_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_CHERRY_LOGS), () -> true);
        addBiomeModifier(PVJPlacements.MANGROVE_FALLEN_TREE, c -> c.hasTag(PVJTags.HAS_MANGROVE_LOGS), () -> true);

        addBiomeModifier(PVJPlacements.CHARRED_BONES, nether(), () -> true);
        addBiomeModifier(PVJPlacements.GLOWCAP, nether(), () -> true);
        addBiomeModifier(PVJPlacements.CINDERCANE, nether(), () -> true);
        addBiomeModifier(PVJPlacements.WARPED_NETTLE, biomes(BiomeKeys.WARPED_FOREST), () -> true);
        addBiomeModifier(PVJPlacements.CRIMSON_NETTLE, biomes(BiomeKeys.CRIMSON_FOREST), () -> true);
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
                () -> true);
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