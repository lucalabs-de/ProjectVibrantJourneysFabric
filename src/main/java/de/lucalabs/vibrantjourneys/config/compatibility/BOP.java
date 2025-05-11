package de.lucalabs.vibrantjourneys.config.compatibility;

import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeConfiguredFeatures;

import java.util.Set;

import static de.lucalabs.vibrantjourneys.util.TreeConfiguredFeatures.entry;

public class BOP {

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> OAK_DEFAULT = Set.of(entry("biomesoplenty:bamboo_grove", 10),
            entry("biomesoplenty:dead_forest", 2),
            entry("biomesoplenty:floodplain", 2),
            entry("biomesoplenty:fungal_jungle", 5),
            entry("biomesoplenty:lavender_field", 1),
            entry("biomesoplenty:lavender_forest", 5),
            entry("biomesoplenty:maple_woods", 20),
            entry("biomesoplenty:mystic_grove", 5),
            entry("biomesoplenty:old_growth_dead_forest", 2),
            entry("biomesoplenty:old_growth_woodland", 20),
            entry("biomesoplenty:orchard", 5),
            entry("biomesoplenty:prairie", 10),
            entry("biomesoplenty:seasonal_forest", 10),
            entry("biomesoplenty:snowy_maple_woods", 5),
            entry("biomesoplenty:wetland", 5),
            entry("biomesoplenty:woodland", 5));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> BIRCH_DEFAULT = Set.of(entry("biomesoplenty:boreal_forest", 20),
            entry("biomesoplenty:rainbow_hills", 10),
            entry("biomesoplenty:seasonal_forest", 10));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> SPRUCE_DEFAULT = Set.of(entry("biomesoplenty:dead_forest", 2),
            entry("biomesoplenty:forested_field", 10),
            entry("biomesoplenty:jade_cliffs", 5),
            entry("biomesoplenty:mediterranean_forest", 5),
            entry("biomesoplenty:old_growth_dead_forest", 2),
            entry("biomesoplenty:wetland", 5));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> JUNGLE_DEFAULT = Set.of();

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> ACACIA_DEFAULT = Set.of(entry("biomesoplenty:lush_desert", 5));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> DARK_OAK_DEFAULT = Set.of(entry("biomesoplenty:pumpkin_patch", 5),
            entry("biomesoplenty:seasonal_forest", 10));

    public static void init() {
        TreeConfiguredFeatures.serializeAndLoad("oak_trees", "biomesoplenty", OAK_DEFAULT, PVJFeatureVars.OAK);
        TreeConfiguredFeatures.serializeAndLoad("birch_trees", "biomesoplenty", BIRCH_DEFAULT, PVJFeatureVars.BIRCH);
        TreeConfiguredFeatures.serializeAndLoad("spruce_trees", "biomesoplenty", SPRUCE_DEFAULT, PVJFeatureVars.SPRUCE);
        TreeConfiguredFeatures.serializeAndLoad("jungle_trees", "biomesoplenty", JUNGLE_DEFAULT, PVJFeatureVars.JUNGLE);
        TreeConfiguredFeatures.serializeAndLoad("acacia_trees", "biomesoplenty", ACACIA_DEFAULT, PVJFeatureVars.ACACIA);
        TreeConfiguredFeatures.serializeAndLoad("dark_oak_trees", "biomesoplenty", DARK_OAK_DEFAULT, PVJFeatureVars.DARK_OAK);
    }
}
