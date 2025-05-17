package de.lucalabs.vibrantjourneys.config;

import static eu.midnightdust.lib.config.MidnightConfig.Entry;


import java.util.HashMap;

public class PVJConfig {
    public static HashMap<String, Boolean> configOptions = new HashMap<>();

    @Entry(name = "enableRocks")
    public static boolean enableRocks = true;
    @Entry(name = "enableTwigs")
    public static boolean enableTwigs = true;
    @Entry(name = "enableFallenLeaves")
    public static boolean enableFallenLeaves = true;
    @Entry(name = "enableBones")
    public static boolean enableBones = true;
    @Entry(name = "enableCharredBones")
    public static boolean enableCharredBones = true;
    @Entry(name = "enableIceChunks")
    public static boolean enableIceChunks = true;
    @Entry(name = "enablePinecones")
    public static boolean enablePinecones = true;
    @Entry(name = "enableSeashells")
    public static boolean enableSeashells = true;

    @Entry(name = "enableFallenTrees")
    public static boolean enableFallenTrees = true;

    @Entry(name = "enableSeaOats")
    public static boolean enableSeaOats = true;
    @Entry(name = "enableCattails")
    public static boolean enableCattails = true;
    @Entry(name = "enableBeachGrass")
    public static boolean enableBeachGrass = true;
    @Entry(name = "enableBarkMushrooms")
    public static boolean enableBarkMushrooms = true;
    @Entry(name = "enableGlowcap")
    public static boolean enableGlowcap = true;
    @Entry(name = "enableCindercane")
    public static boolean enableCindercane = true;
    @Entry(name = "enableNetherNettles")
    public static boolean enableNetherNettles = true;
    @Entry(name = "enableShortGrass")
    public static boolean enableShortGrass = true;
    @Entry(name = "enableNaturalCobwebs")
    public static boolean enableNaturalCobwebs = true;
    @Entry(name = "enableSmallCacti")
    public static boolean enableSmallCacti = true;
    @Entry(name = "enablePricklyBush")
    public static boolean enablePricklyBush = true;
    @Entry(name = "enableReeds")
    public static boolean enableReeds = true;
    @Entry(name = "enableIcicles")
    public static boolean enableIcicles = true;
    @Entry(name = "enableSandySprouts")
    public static boolean enableSandySprouts = true;

    @Entry(name = "enableExtraLilypads")
    public static boolean enableExtraLilypads = true;
    @Entry(name = "enableExtraSeagrass")
    public static boolean enableExtraSeagrass = true;
    @Entry(name = "enableExtraRiverGrass")
    public static boolean enableExtraRiverGrass = true;
    @Entry(name = "enableMossCarpets")
    public static boolean enableMossCarpets = true;
    @Entry(name = "enableTidePools")
    public static boolean enableTidePools = true;
    @Entry(name = "enableCaveRoots")
    public static boolean enableCaveRoots = true;
    @Entry(name = "enableBetterRuinedNetherPortals")
    public static boolean enableBetterRuinedNetherPortals = true;

    @Entry(name = "enableWatergrass")
    public static boolean enableWatergrass = true;
    @Entry(name = "enableGravelPits")
    public static boolean enableGravelPits = true;
    @Entry(name = "enableGoldPits")
    public static boolean enableGoldPits = true;
    @Entry(name = "enableBeachedKelp")
    public static boolean enableBeachedKelp = true;
    @Entry(name = "enableDriedBeachedKelp")
    public static boolean enableDriedBeachedKelp = true;
    @Entry(name = "enableGlowingBlueFungus")
    public static boolean enableGlowingBlueFungus = true;
    @Entry(name = "enableMuddyBones")
    public static boolean enableMuddyBones = true;
    @Entry(name = "enableLotusPonds")
    public static boolean enableLotusPonds = true;
    @Entry(name = "enableFloatingPinkLotus")
    public static boolean enableFloatingPinkLotus = true;
    @Entry(name = "enableHotSprings")
    public static boolean enableHotSprings = true;
    @Entry(name = "enableBushes")
    public static boolean enableBushes = true;
    @Entry(name = "enableWildflowers")
    public static boolean enableWildflowers = true;
    @Entry(name = "enableSlimeNodules")
    public static boolean enableSlimeNodules = true;
    @Entry(name = "enablePinkVines")
    public static boolean enablePinkVines = true;

    @Entry(name = "enableJungleTropicalFish")
    public static boolean enableJungleTropicalFish = true;

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
