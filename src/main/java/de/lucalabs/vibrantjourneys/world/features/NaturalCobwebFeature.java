package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class NaturalCobwebFeature extends Feature<ProbabilityConfig> {
    public NaturalCobwebFeature(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<ProbabilityConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random randomSource = context.getRandom();
        BlockPos.Mutable blockpos = new BlockPos.Mutable(origin.getX(), origin.getY(), origin.getZ());

        for (int i = 64; i < origin.getY() + 50; i++) {
            blockpos.set(origin);
            blockpos.move(
                    randomSource.nextInt(4) - randomSource.nextInt(4),
                    0,
                    randomSource.nextInt(4) - randomSource.nextInt(4));
            blockpos.setY(i);

            if (world.getBlockState(blockpos).getBlock() instanceof LeavesBlock) {
                if (world.isAir(blockpos.down())) {
                    if (randomSource.nextFloat() < context.getConfig().probability) {
                        return WorldUtils.setBlockState(world, blockpos.down(), PVJBlocks.NATURAL_COBWEB.getDefaultState(), 2);
                    }
                }
            }
        }

        return true;
    }
}