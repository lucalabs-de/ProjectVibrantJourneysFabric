package de.lucalabs.vibrantjourneys;

import de.lucalabs.vibrantjourneys.colors.PVJBlockColorRegistry;
import de.lucalabs.vibrantjourneys.render.PVJRendering;
import net.fabricmc.api.ClientModInitializer;

public class ProjectVibrantJourneysClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PVJBlockColorRegistry.initialize();
		PVJRendering.initialize();
	}
}