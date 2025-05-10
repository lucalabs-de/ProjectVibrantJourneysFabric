package de.lucalabs.vibrantjourneys;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.registry.PVJItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectVibrantJourneys implements ModInitializer {
	public static final String MOD_ID = "projectvibrantjourneys";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PVJBlocks.initialize();
		PVJItems.initialize();
	}
}