package de.lucalabs.vibrantjourneys.mixin;

import net.minecraft.world.ChunkRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChunkRegion.class)
public interface ChunkRegionAccessor {
    @Accessor
    int getPlacementRadius();
}
