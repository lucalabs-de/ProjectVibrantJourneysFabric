package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FloatingPinkLotusFeature extends Feature<ProbabilityConfig> {

    public FloatingPinkLotusFeature(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<ProbabilityConfig> context) {
        ProbabilityConfig config = context.getConfig();
        StructureWorldAccess level = context.getWorld();
        BlockPos origin = context.getOrigin();
        BlockState blockstate = PVJBlocks.PINK_LOTUS.getDefaultState();

        if (context.getRandom().nextFloat() < config.probability && level.getBlockState(origin.down()).isOf(Blocks.WATER)) {
            int surfaceY = level.getTopY(Heightmap.Type.WORLD_SURFACE, origin.getX(), origin.getZ());
            int oceanFloorY = level.getTopY(Heightmap.Type.OCEAN_FLOOR, origin.getX(), origin.getZ());
            int waterDepth = surfaceY - oceanFloorY;

            if (waterDepth <= 3) {
                return level.setBlockState(origin, blockstate, 2);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}