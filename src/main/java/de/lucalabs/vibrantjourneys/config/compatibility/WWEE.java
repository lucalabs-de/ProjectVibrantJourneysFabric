package de.lucalabs.vibrantjourneys.config.compatibility;

import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeFeatureUtils;

import java.util.Set;

public class WWEE {

    public static final String MOD_ID = "expanded_ecosphere";

    // TODO
    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> OAK_DEFAULT = Set.of(

    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> BIRCH_DEFAULT = Set.of(

    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> SPRUCE_DEFAULT = Set.of(

    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> JUNGLE_DEFAULT = Set.of(

    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> ACACIA_DEFAULT = Set.of(

    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> DARK_OAK_DEFAULT = Set.of(

    );

    public static void init() {
        TreeFeatureUtils.serializeAndLoad("oak_trees", MOD_ID, OAK_DEFAULT, PVJFeatureVars.OAK);
        TreeFeatureUtils.serializeAndLoad("birch_trees", MOD_ID, BIRCH_DEFAULT, PVJFeatureVars.BIRCH);
        TreeFeatureUtils.serializeAndLoad("spruce_trees", MOD_ID, SPRUCE_DEFAULT, PVJFeatureVars.SPRUCE);
        TreeFeatureUtils.serializeAndLoad("jungle_trees", MOD_ID, JUNGLE_DEFAULT, PVJFeatureVars.JUNGLE);
        TreeFeatureUtils.serializeAndLoad("acacia_trees", MOD_ID, ACACIA_DEFAULT, PVJFeatureVars.ACACIA);
        TreeFeatureUtils.serializeAndLoad("dark_oak_trees", MOD_ID, DARK_OAK_DEFAULT, PVJFeatureVars.DARK_OAK);
    }
}
