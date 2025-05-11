package de.lucalabs.vibrantjourneys.config.compatibility;

import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeConfiguredFeatures;

import java.util.Set;

public class WWEE {

    public static final String MOD_ID = "expanded_ecosphere";

    // TODO
    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> OAK_DEFAULT = Set.of(

    );

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> BIRCH_DEFAULT = Set.of(

    );

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> SPRUCE_DEFAULT = Set.of(

    );

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> JUNGLE_DEFAULT = Set.of(

    );

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> ACACIA_DEFAULT = Set.of(

    );

    public static final Set<TreeConfiguredFeatures.ChanceBiomeEntry> DARK_OAK_DEFAULT = Set.of(

    );

    public static void init() {
        TreeConfiguredFeatures.serializeAndLoad("oak_trees", MOD_ID, OAK_DEFAULT, PVJFeatureVars.OAK);
        TreeConfiguredFeatures.serializeAndLoad("birch_trees", MOD_ID, BIRCH_DEFAULT, PVJFeatureVars.BIRCH);
        TreeConfiguredFeatures.serializeAndLoad("spruce_trees", MOD_ID, SPRUCE_DEFAULT, PVJFeatureVars.SPRUCE);
        TreeConfiguredFeatures.serializeAndLoad("jungle_trees", MOD_ID, JUNGLE_DEFAULT, PVJFeatureVars.JUNGLE);
        TreeConfiguredFeatures.serializeAndLoad("acacia_trees", MOD_ID, ACACIA_DEFAULT, PVJFeatureVars.ACACIA);
        TreeConfiguredFeatures.serializeAndLoad("dark_oak_trees", MOD_ID, DARK_OAK_DEFAULT, PVJFeatureVars.DARK_OAK);
    }
}
