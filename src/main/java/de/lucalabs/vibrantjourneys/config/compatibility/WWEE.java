package de.lucalabs.vibrantjourneys.config.compatibility;

import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeFeatureUtils;

import java.util.Set;

import static de.lucalabs.vibrantjourneys.util.TreeFeatureUtils.entry;

public class WWEE {

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> OAK_DEFAULT = Set.of(
            entry("wythers:autumnal_flower_forest", 5),
            entry("wythers:spring_flower_forest", 5),
            entry("wythers:huangshan_highlands", 5)
    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> BIRCH_DEFAULT = Set.of(
            entry("wythers:eucalyptus_jungle", 10),
            entry("wythers:eucalyptus_jungle_canyon", 5)
    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> SPRUCE_DEFAULT = Set.of(
            entry("wythers:birch_taiga", 5),
            entry("wythers:boreal_forest_red", 5),
            entry("wythers:boreal_forest_yellow", 5),
            entry("wythers:cold_island", 5),
            entry("wythers:deep_snowy_taiga", 5),
            entry("wythers:fen", 5),
            entry("wythers:forested_highlands", 10),
            entry("wythers:jade_highlands", 5),
            entry("wythers:larch_taiga", 5),
            entry("wythers:maple_mountains", 5),
            entry("wythers:flooded_temperate_rainforest", 5),
            entry("wythers:old_growth_taiga_crags", 20),
            entry("wythers:old_growth_taiga_swamp", 10),
            entry("wythers:pine_barrens", 5),
            entry("wythers:taiga_crags", 5),
            entry("wythers:temperate_rainforest", 5),
            entry("wythers:temperate_rainforest_crags", 5),
            entry("wythers:thermal_taiga", 10),
            entry("wythers:thermal_taiga_crags", 10)
    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> JUNGLE_DEFAULT = Set.of(
            entry("wythers:tropical_rainforest", 5),
            entry("wythers:sparse_bamboo_jungle", 5),
            entry("wythers:jungle_island", 5),
            entry("wythers:bamboo_swamp", 5),
            entry("wythers:bamboo_jungle_swamp", 5),
            entry("wythers:bamboo_jungle_canyon", 5),
            entry("wythers:bamboo_jungle_highlands", 5),
            entry("wythers:dry_tropical_grassland", 5),
            entry("wythers:highland_tropical_rainforest", 5),
            entry("wythers:flooded_jungle", 2),
            entry("wythers:flooded_rainforest", 2),
            entry("wythers:pantanal", 5),
            entry("wythers:sandy_jungle", 5),
            entry("wythers:sparse_eucalyptus_jungle", 5),
            entry("wythers:sparse_eucalyptus_woodland", 5),
            entry("wythers:windswept_jungle", 5)
    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> ACACIA_DEFAULT = Set.of(
            entry("wythers:chaparral", 5),
            entry("wythers:dry_tropical_grassland", 5),
            entry("wythers:flooded_savanna", 5),
            entry("wythers:wooded_savanna", 5),
            entry("wythers:dry_savanna", 5),
            entry("wythers:savanna_badlands", 5),
            entry("wythers:dry_tropical_forest", 5),
            entry("wythers:eucalyptus_salubris_woodland", 5),
            entry("wythers:eucalyptus_woodland", 5),
            entry("wythers:giant_sequoia_forest", 5),
            entry("wythers:humid_tropical_grassland", 5),
            entry("wythers:jacaranda_savanna", 5),
            entry("wythers:savanna_basaltic_incursions", 5),
            entry("wythers:scrubland", 5),
            entry("wythers:scrub_forest", 5),
            entry("wythers:tsingy_forest", 5),
            entry("wythers:tropical_forest", 5),
            entry("wythers:tropical_grassland", 5)
    );

    public static final Set<TreeFeatureUtils.ChanceBiomeEntry> DARK_OAK_DEFAULT = Set.of(

    );

    public static void init() {
        TreeFeatureUtils.serializeAndLoad("oak_trees", "wythers", OAK_DEFAULT, PVJFeatureVars.OAK);
        TreeFeatureUtils.serializeAndLoad("birch_trees", "wythers", BIRCH_DEFAULT, PVJFeatureVars.BIRCH);
        TreeFeatureUtils.serializeAndLoad("spruce_trees", "wythers", SPRUCE_DEFAULT, PVJFeatureVars.SPRUCE);
        TreeFeatureUtils.serializeAndLoad("jungle_trees", "wythers", JUNGLE_DEFAULT, PVJFeatureVars.JUNGLE);
        TreeFeatureUtils.serializeAndLoad("acacia_trees", "wythers", ACACIA_DEFAULT, PVJFeatureVars.ACACIA);
        TreeFeatureUtils.serializeAndLoad("dark_oak_trees", "wythers", DARK_OAK_DEFAULT, PVJFeatureVars.DARK_OAK);
    }
}
