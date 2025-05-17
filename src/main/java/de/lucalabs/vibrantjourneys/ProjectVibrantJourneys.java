package de.lucalabs.vibrantjourneys;

import de.lucalabs.vibrantjourneys.config.ChanceTreeFeatureConfig;
import de.lucalabs.vibrantjourneys.config.PVJConfig;
import de.lucalabs.vibrantjourneys.recipes.PVJCompostables;
import de.lucalabs.vibrantjourneys.recipes.PVJPotionRecipes;
import de.lucalabs.vibrantjourneys.registry.PVJBiomeModifiers;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.registry.PVJItems;
import de.lucalabs.vibrantjourneys.registry.PVJPotions;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectVibrantJourneys implements ModInitializer {
	public static final String MOD_ID = "projectvibrantjourneys";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MidnightConfig.init("modid", PVJConfig.class);

		PVJBlocks.initialize();
		PVJItems.initialize();
		PVJBiomeModifiers.initialize();
		PVJPotions.initialize();
		PVJPotionRecipes.initialize();
		PVJCompostables.inititalize();
		ChanceTreeFeatureConfig.initialize();
	}
}