package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.blocks.GroundcoverBlock;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.tags.PVJTags;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class RocksGroundcoverFeature extends Feature<RandomPatchFeatureConfig> {

    public RocksGroundcoverFeature(Codec<RandomPatchFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<RandomPatchFeatureConfig> context) {
        Random randomSource = context.getRandom();
        BlockPos origin = context.getOrigin();
        StructureWorldAccess level = context.getWorld();
        BlockState originState = level.getBlockState(origin);

        if (!originState.isReplaceable()) {
            return false;
        }

        BlockState ground = level.getBlockState(origin.down());
        if (ground.isOf(Blocks.SCULK)
                || ground.isOf(Blocks.DEEPSLATE)
                || ground.isOf(Blocks.AMETHYST_BLOCK)
                || ground.isIn(PVJTags.GROUNDCOVER_CANNOT_GENERATE_ON)) {
            return false;
        }
        BlockState rocks = getRocksToPlace(randomSource, originState, origin, ground.getBlock());
        if (!rocks.canPlaceAt(level, origin)) {
            return false;
        } else {
            if (WorldUtils.setBlockState(level, origin, rocks, 2)) {
                if (level.getBlockState(origin.up()).contains(Properties.DOUBLE_BLOCK_HALF)
                        && level.getBlockState(origin.up()).get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
                    level.removeBlock(origin.up(), false);
                }
                return true;
            }
        }
        return false;
    }

    private BlockState getRocksToPlace(Random randomSource, BlockState originState, BlockPos origin, Block ground) {
        Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
        int model = randomSource.nextInt(5);

        BlockState rocks = PVJBlocks.ROCKS.getDefaultState();

        if (ground == Blocks.RED_SAND || ground == Blocks.RED_SANDSTONE) {
            rocks = PVJBlocks.RED_SANDSTONE_ROCKS.getDefaultState();
        } else if (ground == Blocks.SAND || ground == Blocks.SANDSTONE) {
            rocks = PVJBlocks.SANDSTONE_ROCKS.getDefaultState();
        } else if (randomSource.nextFloat() < 0.2F && origin.getY() > 8) {
            rocks = PVJBlocks.MOSSY_ROCKS.getDefaultState();
        }
        rocks = rocks.with(GroundcoverBlock.FACING, dir).with(GroundcoverBlock.MODEL, model);

        if (originState.getFluidState().getFluid() == Fluids.WATER) {
            rocks = rocks.with(Properties.WATERLOGGED, true);
        }

        return rocks;
    }
}
