package de.lucalabs.vibrantjourneys;

import de.lucalabs.vibrantjourneys.config.ChanceTreeFeatureConfig;
import de.lucalabs.vibrantjourneys.config.PVJConfig;
import de.lucalabs.vibrantjourneys.recipes.PVJCompostables;
import de.lucalabs.vibrantjourneys.recipes.PVJPotionRecipes;
import de.lucalabs.vibrantjourneys.registry.*;
import de.lucalabs.vibrantjourneys.world.features.ruinednetherportals.RuinedPortalDecoratorBase;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectVibrantJourneys implements ModInitializer {
	public static final String MOD_ID = "projectvibrantjourneys";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MidnightConfig.init(ProjectVibrantJourneys.MOD_ID, PVJConfig.class);

		PVJFeatures.initialize();
		PVJBlocks.initialize();
		PVJItemGroup.initialize();
		PVJItems.initialize();
		PVJBiomeModifiers.initialize();
		PVJPotions.initialize();
		PVJPotionRecipes.initialize();
		PVJStatusEffects.initialize();
		PVJCompostables.inititalize();
		ChanceTreeFeatureConfig.initialize();

		RuinedPortalDecoratorBase.registerPortalDecorators();
	}
}