package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.blocks.MuddyBonesBlock;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class MuddyBonesFeature extends Feature<DefaultFeatureConfig> {
    public MuddyBonesFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random randomSource = context.getRandom();

        Direction dir = Direction.Type.HORIZONTAL.random(randomSource);
        int model = randomSource.nextInt(3);

        if (world.getBlockState(origin).isOf(Blocks.MUD)) {
            BlockState state = PVJBlocks.MUDDY_BONES.getDefaultState().with(MuddyBonesBlock.FACING, dir).with(MuddyBonesBlock.MODEL, model);
            if (world.setBlockState(origin, state, 2)) {
                ProjectVibrantJourneys.LOGGER.info(origin.toString());
            }
        }

        return true;
    }


}