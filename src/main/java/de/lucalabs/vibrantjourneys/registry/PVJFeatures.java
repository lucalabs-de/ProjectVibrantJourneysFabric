package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.world.features.*;
import de.lucalabs.vibrantjourneys.world.features.configurations.BushConfiguration;
import de.lucalabs.vibrantjourneys.world.features.configurations.FallenTreeConfiguration;
import de.lucalabs.vibrantjourneys.world.features.configurations.MultipleVegetationPatchConfiguration;
import de.lucalabs.vibrantjourneys.world.features.stateproviders.DirectionalStateProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;

public class PVJFeatures {

    public static final Feature<RandomPatchFeatureConfig> ROCKS = registerFeature("rocks", new RocksGroundcoverFeature(RandomPatchFeatureConfig.CODEC));
    public static final Feature<RandomPatchFeatureConfig> GROUNDCOVER = registerFeature("groundcover", new GroundcoverFeature(RandomPatchFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> BARK_MUSHROOM = registerFeature("bark_mushroom", new BarkMushroomFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> GLOWING_BLUE_FUNGUS = registerFeature("glowing_blue_fungus", new GlowingBlueFungusFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<SimpleBlockFeatureConfig> SIMPLE_BLOCK_MATCH_WATER = registerFeature("simple_block_match_water", new SimpleBlockMatchWaterFeature(SimpleBlockFeatureConfig.CODEC));
    public static final Feature<ProbabilityConfig> NATURAL_COBWEB = registerFeature("natural_cobweb", new NaturalCobwebFeature(ProbabilityConfig.CODEC));
    public static final Feature<FallenTreeConfiguration> FALLEN_TREE = registerFeature("fallen_tree", new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    public static final Feature<MultipleVegetationPatchConfiguration> POOL = registerFeature("pool", new MultipleWaterloggedVegetationPatchFeature(MultipleVegetationPatchConfiguration.CODEC));
    public static final Feature<ProbabilityConfig> LILYPAD = registerFeature("lily_pad", new ExtraLilyPadFeature(ProbabilityConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> ICICLE = registerFeature("icicle", new IcicleFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> GRAVEL_PIT = registerFeature("gravel_pit", new GravelPitFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> GOLD_PIT = registerFeature("gold_pit", new GoldPitFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<SingleStateFeatureConfig> BEACHED_KELP = registerFeature("beached_kelp", new BeachedKelpFeature(SingleStateFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> MUDDY_BONES = registerFeature("muddy_bones", new MuddyBonesFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> LOTUS_POND = registerFeature("lotus_pond", new LotusPondFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<ProbabilityConfig> FLOATING_PINK_LOTUS = registerFeature("floating_pink_lotus", new FloatingPinkLotusFeature(ProbabilityConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> HOT_SPRINGS = registerFeature("hot_springs", new HotSpringsFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<BushConfiguration> BUSH = registerFeature("bush", new BushFeature(BushConfiguration.CODEC));
    public static final Feature<DefaultFeatureConfig> SLIME_NODULE = registerFeature("slime_nodule", new SlimeNoduleFeature(DefaultFeatureConfig.CODEC));

    private static <FC extends FeatureConfig> Feature<FC> registerFeature(String name, Feature<FC> feature) {
        return Registry.register(Registries.FEATURE, new Identifier(ProjectVibrantJourneys.MOD_ID, name), feature);
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("initializing features");
        StateProviders.initialize();
    }

    public static class StateProviders {
        public static final BlockStateProviderType<DirectionalStateProvider> DIRECTIONAL_STATE_PROVIDER =
                Registry.register(
                        Registries.BLOCK_STATE_PROVIDER_TYPE,
                        "directional_state_provider",
                        new BlockStateProviderType<>(DirectionalStateProvider.CODEC));

        public static void initialize() {
            ProjectVibrantJourneys.LOGGER.info("initializing state providers");
        }
    }
}