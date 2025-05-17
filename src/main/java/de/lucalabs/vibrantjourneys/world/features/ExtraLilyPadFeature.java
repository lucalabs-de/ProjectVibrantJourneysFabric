package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ExtraLilyPadFeature extends Feature<ProbabilityConfig> {

    public ExtraLilyPadFeature(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<ProbabilityConfig> context) {
        ProbabilityConfig config = context.getConfig();
        StructureWorldAccess level = context.getWorld();
        BlockPos origin = context.getOrigin();
        BlockState blockstate = Blocks.LILY_PAD.getDefaultState();

        if (level.getBlockState(origin.down()).isOf(Blocks.ICE)) {
            return false;
        }

        if (context.getRandom().nextFloat() < config.probability && level.getBlockState(origin.down()).isOf(Blocks.WATER)) {
            int surfaceY = level.getTopY(Heightmap.Type.WORLD_SURFACE, origin.getX(), origin.getZ());
            int oceanFloorY = level.getTopY(Heightmap.Type.OCEAN_FLOOR, origin.getX(), origin.getZ());
            int waterDepth = surfaceY - oceanFloorY;

            if (waterDepth <= 3) {
                return WorldUtils.setBlockState(level, origin, blockstate, 2);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}