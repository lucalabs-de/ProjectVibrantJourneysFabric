package de.lucalabs.vibrantjourneys.colors;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.registry.PVJItems;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

public final class PVJBlockColorRegistry {
    private PVJBlockColorRegistry() {
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("initializing block colors");

        ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> (world != null && pos != null)
                        ? BiomeColors.getFoliageColor(world, pos)
                        : FoliageColors.getDefaultColor()),
                PVJBlocks.TWIGS,
                PVJBlocks.FALLEN_LEAVES);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> (world != null && pos != null)
                        ? BiomeColors.getGrassColor(world, pos)
                        : GrassColors.getColor(0.5, 1.0),
                PVJBlocks.SHORT_GRASS,
                PVJBlocks.SANDY_SPROUTS,
                PVJBlocks.WATERGRASS,
                PVJBlocks.BLUE_WILDFLOWERS,
                PVJBlocks.ORANGE_WILDFLOWERS,
                PVJBlocks.PURPLE_WILDFLOWERS,
                PVJBlocks.YELLOW_WILDFLOWERS,
                PVJBlocks.WHITE_WILDFLOWERS,
                PVJBlocks.MIXED_WILDFLOWERS);

        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> {
            BlockState state = Blocks.OAK_LEAVES.getDefaultState();
            var blockColorProvider = ColorProviderRegistry.BLOCK.get(Blocks.OAK_LEAVES);
            return blockColorProvider.getColor(state, null, null, tintIndex);
        }), PVJItems.FALLEN_LEAVES, PVJItems.SHORT_GRASS);
    }

}
