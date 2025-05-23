package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.blocks.SlimeNoduleBlock;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SlimeNoduleFeature extends Feature<DefaultFeatureConfig> {

    public SlimeNoduleFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        Random randomSource = context.getRandom();
        BlockPos origin = context.getOrigin();
        StructureWorldAccess level = context.getWorld();
        BlockPos.Mutable mutable = origin.mutableCopy();
        SlimeNoduleBlock blockToPlace = (SlimeNoduleBlock) PVJBlocks.SLIME_NODULE;
        int count = 0;

        for (int i = 0; i < origin.getY() + 50; i++) {
            mutable.set(origin);
            mutable.move(
                    randomSource.nextInt(4) - randomSource.nextInt(4),
                    0,
                    randomSource.nextInt(4) - randomSource.nextInt(4));
            mutable.setY(i);
            if (blockToPlace.canAttachTo(level, mutable, Direction.DOWN)) {
                boolean flag = false;
                while (!flag) {
                    Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
                    BlockState state = level.getBlockState(mutable);
                    if (level.isAir(mutable.offset(dir)) && state.isFullCube(level, mutable) && state.isIn(BlockTags.LOGS)) {
                        if (level.setBlockState(mutable.offset(dir), blockToPlace.getDefaultState().with(HorizontalFacingBlock.FACING, dir), 2)) {
                            count++;
                        }
                    }

                    if (randomSource.nextFloat() < 0.8F) {
                        flag = true;
                    }
                }
            }
        }

        return count > 0;
    }
}
