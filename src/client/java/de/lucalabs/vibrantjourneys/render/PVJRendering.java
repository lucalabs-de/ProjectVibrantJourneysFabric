package de.lucalabs.vibrantjourneys.render;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public final class PVJRendering {
    private PVJRendering() {
    }

    public static void initialize() {
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.BEACH_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.REEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.BEACHED_KELP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.DRIED_BEACHED_KELP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.WHITE_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.BLUE_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.MIXED_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.PURPLE_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.ORANGE_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.YELLOW_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.BONES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.CHARRED_BONES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.CINDERCANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.CRIMSON_NETTLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.DEAD_FALLEN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.FALLEN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.GLOWCAP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.GLOWING_BLUE_FUNGUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.ICICLE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.NATURAL_COBWEB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.PINK_LOTUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.PINK_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.PRICKLY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.SANDY_SPROUTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.SEA_OATS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.SHORT_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.WARPED_NETTLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.WATERGRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PVJBlocks.CATTAIL, RenderLayer.getCutout());
    }
}
