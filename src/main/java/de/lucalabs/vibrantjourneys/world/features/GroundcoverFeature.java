package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.tags.PVJTags;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GroundcoverFeature extends Feature<RandomPatchFeatureConfig> {

    public GroundcoverFeature(Codec<RandomPatchFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<RandomPatchFeatureConfig> context) {
        RandomPatchFeatureConfig config = context.getConfig();
        Random random = context.getRandom();
        BlockPos blockpos = context.getOrigin();
        StructureWorldAccess world = context.getWorld();

        int i = 0;
        BlockPos.Mutable pos = new BlockPos.Mutable();
        int j = config.xzSpread() + 1;
        int k = config.ySpread() + 1;

        for (int l = 0; l < config.tries(); ++l) {
            pos.set(
                    blockpos,
                    random.nextInt(j) - random.nextInt(j),
                    random.nextInt(k) - random.nextInt(k),
                    random.nextInt(j) - random.nextInt(j));
            if (!world.getBlockState(pos.down()).isIn(PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)) {
                if (config.feature().value().generate(world, context.getGenerator(), random, pos)) {
                    if (world.getBlockState(pos.up()).contains(Properties.DOUBLE_BLOCK_HALF)
                            && world.getBlockState(pos.up()).get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
                        world.removeBlock(pos.up(), false);
                    }
                    ++i;
                }
            }
        }

        return i > 0;
    }
}