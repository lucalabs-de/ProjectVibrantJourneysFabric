package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SimpleBlockMatchWaterFeature extends Feature<SimpleBlockFeatureConfig> {

    public SimpleBlockMatchWaterFeature(Codec<SimpleBlockFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<SimpleBlockFeatureConfig> context) {
        boolean placed = false;

        SimpleBlockFeatureConfig config = context.getConfig();
        StructureWorldAccess level = context.getWorld();
        BlockPos origin = context.getOrigin();
        BlockState state = config.toPlace().get(context.getRandom(), origin);

        if (state.canPlaceAt(level, origin)) {
            if (state.getBlock() instanceof TallPlantBlock) {
                if (!level.isAir(origin.up())) {
                    return false;
                }

                TallPlantBlock.placeAt(level, state, origin, 2);
            } else {
                if (state.getBlock() instanceof Waterloggable) {
                    if (level.testFluidState(origin, (fluidstate) -> fluidstate.getFluid() == Fluids.WATER)) {
                        placed = WorldUtils.setBlockState(level, origin, state.with(Properties.WATERLOGGED, true), 2);
                    } else {
                        placed = WorldUtils.setBlockState(level, origin, state, 2);
                    }
                } else {
                    placed = WorldUtils.setBlockState(level, origin, state, 2);
                }
            }

            return placed;
        } else {
            return false;
        }
    }
}