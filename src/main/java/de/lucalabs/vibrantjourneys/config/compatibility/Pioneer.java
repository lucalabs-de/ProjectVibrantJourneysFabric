package de.lucalabs.vibrantjourneys.config.compatibility;

import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeConfiguredFeatures;

import java.util.Set;

import static de.lucalabs.vibrantjourneys.util.TreeConfiguredFeatures.entry;

public class Pioneer {

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> OAK_DEFAULT = Set.of(entry("pioneer:verdant_sands", 5),
            entry("pioneer:pine_meadows", 10),
            entry("pioneer:aspen_grove", 10),
            entry("pioneer:overgrown_spires", 10),
            entry("pioneer:flooded_forest", 20),
            entry("pioneer:windswept_cliffs", 5));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> BIRCH_DEFAULT = Set.of(entry("pioneer:flooded_forest", 10));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> SPRUCE_DEFAULT = Set.of(entry("pioneer:pine_meadows", 3),
            entry("pioneer:autumnal_coniferous_forest", 3));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> JUNGLE_DEFAULT = Set.of(entry("pioneer:verdant_sands", 15),
            entry("pioneer:overgrown_spires", 15));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> ACACIA_DEFAULT = Set.of(entry("pioneer:verdant_sands", 15),
            entry("pioneer:baobab_fields", 10));

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> DARK_OAK_DEFAULT = Set.of(entry("pioneer:overgrown_spires", 10));

    public static void init() {
        TreeConfiguredFeatures.serializeAndLoad("oak_trees", "pioneer", OAK_DEFAULT, PVJFeatureVars.OAK);
        TreeConfiguredFeatures.serializeAndLoad("birch_trees", "pioneer", BIRCH_DEFAULT, PVJFeatureVars.BIRCH);
        TreeConfiguredFeatures.serializeAndLoad("spruce_trees", "pioneer", SPRUCE_DEFAULT, PVJFeatureVars.SPRUCE);
        TreeConfiguredFeatures.serializeAndLoad("jungle_trees", "pioneer", JUNGLE_DEFAULT, PVJFeatureVars.JUNGLE);
        TreeConfiguredFeatures.serializeAndLoad("acacia_trees", "pioneer", ACACIA_DEFAULT, PVJFeatureVars.ACACIA);
        TreeConfiguredFeatures.serializeAndLoad("dark_oak_trees", "pioneer", DARK_OAK_DEFAULT, PVJFeatureVars.DARK_OAK);
    }
}
