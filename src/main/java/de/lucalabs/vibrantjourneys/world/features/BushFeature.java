package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.world.features.configurations.BushConfiguration;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BushFeature extends Feature<BushConfiguration> {
    public BushFeature(Codec<BushConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<BushConfiguration> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        if (world.isAir(origin)) {
            if (world.getBlockState(origin.down()).isOf(Blocks.GRASS_BLOCK) || world.getBlockState(origin.down()).isIn(BlockTags.DIRT)) {
                for (Direction dir : Direction.Type.HORIZONTAL) {
                    if (!world.getBlockState(origin.offset(dir)).isReplaceable()) {
                        return false;
                    }
                }
                world.setBlockState(origin, context.getConfig().log(), 2);
                for (Direction dir : Direction.Type.HORIZONTAL) {
                    world.setBlockState(origin.offset(dir), context.getConfig().leaves().with(LeavesBlock.DISTANCE, 1), 2);
                }
                world.setBlockState(origin.up(), context.getConfig().leaves().with(LeavesBlock.DISTANCE, 1), 2);
            }
        }
        return true;
    }


}