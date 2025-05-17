package de.lucalabs.vibrantjourneys.config;

import static eu.midnightdust.lib.config.MidnightConfig.Entry;


import java.util.HashMap;

public class PVJConfig {
    public static HashMap<String, Boolean> configOptions = new HashMap<>();

    @Entry(name = "enableRocks")
    public static boolean enableRocks;
    @Entry(name = "enableTwigs")
    public static boolean enableTwigs;
    @Entry(name = "enableFallenLeaves")
    public static boolean enableFallenLeaves;
    @Entry(name = "enableBones")
    public static boolean enableBones;
    @Entry(name = "enableCharredBones")
    public static boolean enableCharredBones;
    @Entry(name = "enableIceChunks")
    public static boolean enableIceChunks;
    @Entry(name = "enablePinecones")
    public static boolean enablePinecones;
    @Entry(name = "enableSeashells")
    public static boolean enableSeashells;

    @Entry(name = "enableFallenTrees")
    public static boolean enableFallenTrees;

    @Entry(name = "enableSeaOats")
    public static boolean enableSeaOats;
    @Entry(name = "enableCattails")
    public static boolean enableCattails;
    @Entry(name = "enableBeachGrass")
    public static boolean enableBeachGrass;
    @Entry(name = "enableBarkMushrooms")
    public static boolean enableBarkMushrooms;
    @Entry(name = "enableGlowcap")
    public static boolean enableGlowcap;
    @Entry(name = "enableCindercane")
    public static boolean enableCindercane;
    @Entry(name = "enableNetherNettles")
    public static boolean enableNetherNettles;
    @Entry(name = "enableShortGrass")
    public static boolean enableShortGrass;
    @Entry(name = "enableNaturalCobwebs")
    public static boolean enableNaturalCobwebs;
    @Entry(name = "enableSmallCacti")
    public static boolean enableSmallCacti;
    @Entry(name = "enablePricklyBush")
    public static boolean enablePricklyBush;
    @Entry(name = "enableReeds")
    public static boolean enableReeds;
    @Entry(name = "enableIcicles")
    public static boolean enableIcicles;
    @Entry(name = "enableSandySprouts")
    public static boolean enableSandySprouts;

    @Entry(name = "enableExtraLilypads")
    public static boolean enableExtraLilypads;
    @Entry(name = "enableExtraSeagrass")
    public static boolean enableExtraSeagrass;
    @Entry(name = "enableExtraRiverGrass")
    public static boolean enableExtraRiverGrass;
    @Entry(name = "enableMossCarpets")
    public static boolean enableMossCarpets;
    @Entry(name = "enableTidePools")
    public static boolean enableTidePools;
    @Entry(name = "enableCaveRoots")
    public static boolean enableCaveRoots;
    @Entry(name = "enableBetterRuinedNetherPortals")
    public static boolean enableBetterRuinedNetherPortals;

    @Entry(name = "enableWatergrass")
    public static boolean enableWatergrass;
    @Entry(name = "enableGravelPits")
    public static boolean enableGravelPits;
    @Entry(name = "enableGoldPits")
    public static boolean enableGoldPits;
    @Entry(name = "enableBeachedKelp")
    public static boolean enableBeachedKelp;
    @Entry(name = "enableDriedBeachedKelp")
    public static boolean enableDriedBeachedKelp;
    @Entry(name = "enableGlowingBlueFungus")
    public static boolean enableGlowingBlueFungus;
    @Entry(name = "enableMuddyBones")
    public static boolean enableMuddyBones;
    @Entry(name = "enableLotusPonds")
    public static boolean enableLotusPonds;
    @Entry(name = "enableFloatingPinkLotus")
    public static boolean enableFloatingPinkLotus;
    @Entry(name = "enableHotSprings")
    public static boolean enableHotSprings;
    @Entry(name = "enableBushes")
    public static boolean enableBushes;
    @Entry(name = "enableWildflowers")
    public static boolean enableWildflowers;
    @Entry(name = "enableSlimeNodules")
    public static boolean enableSlimeNodules;
    @Entry(name = "enablePinkVines")
    public static boolean enablePinkVines;

    @Entry(name = "enableJungleTropicalFish")
    public static boolean enableJungleTropicalFish;

    @Entry(name = "replaceableGroundcover")
    public static boolean replaceableGroundcover;

    static {
        config("enableRocks", enableRocks);
        config("enableTwigs", enableTwigs);
        config("enableFallenLeaves", enableFallenLeaves);
        config("enableBones", enableBones);
        config("enableCharredBones", enableCharredBones);
        config("enableIceChunks", enableIceChunks);
        config("enablePinecones", enablePinecones);
        config("enableSeashells", enableSeashells);

        config("enableFallenTrees", enableFallenTrees);

        config("enableSeaOats", enableSeaOats);
        config("enableCattails", enableCattails);
        config("enableBeachGrass", enableBeachGrass);
        config("enableBarkMushrooms", enableBarkMushrooms);
        config("enableGlowcap", enableGlowcap);
        config("enableCindercane", enableCindercane);
        config("enableNetherNettles", enableNetherNettles);
        config("enableShortGrass", enableShortGrass);
        config("enableNaturalCobwebs", enableNaturalCobwebs);
        config("enableSmallCacti", enableSmallCacti);
        config("enablePricklyBush", enablePricklyBush);
        config("enableReeds", enableReeds);
        config("enableIcicles", enableIcicles);
        config("enableSandySprouts", enableSandySprouts);
        config("enableDriedBeachedKelp", enableDriedBeachedKelp);
        config("enableGlowingBlueFungus", enableGlowingBlueFungus);
        config("enableMuddyBones", enableMuddyBones);
        config("enableLotusPonds", enableLotusPonds);
        config("enableFloatingPinkLotus", enableFloatingPinkLotus);
        config("enableHotSprings", enableHotSprings);
        config("enableBushes", enableBushes);
        config("enableWildflowers", enableWildflowers);
        config("enableSlimeNodules", enableSlimeNodules);
        config("enablePinkVines", enablePinkVines);
        config("enableWatergrass", enableWatergrass);
        config("enableGravelPits", enableGravelPits);
        config("enableGoldPits", enableGoldPits);
        config("enableBeachedKelp", enableBeachedKelp);

        config("enableExtraLilypads", enableExtraLilypads);
        config("enableExtraSeagrass", enableExtraSeagrass);
        config("enableExtraRiverGrass", enableExtraRiverGrass);
        config("enableMossCarpets", enableMossCarpets);
        config("enableTidePools", enableTidePools);
        config("enableCaveRoots", enableCaveRoots);
        config("enableBetterRuinedNetherPortals", enableBetterRuinedNetherPortals);

        config("enableJungleTropicalFish", enableJungleTropicalFish);

        config("replaceableGroundcover", replaceableGroundcover);
    }

    private static void config(String name, boolean spec) {
        configOptions.put(name, spec);
    }
}
