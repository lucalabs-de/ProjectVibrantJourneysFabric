package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.blocks.BeachedKelpBlock;
import de.lucalabs.vibrantjourneys.blocks.properties.BeachedKelpShape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BeachedKelpFeature extends Feature<SingleStateFeatureConfig> {

    public BeachedKelpFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        SingleStateFeatureConfig config = context.getConfig();
        Random random = context.getRandom();
        BlockPos origin = context.getOrigin();
        StructureWorldAccess level = context.getWorld();
        BlockState state = config.state;

        if (!(config.state.getBlock() instanceof BeachedKelpBlock)) {
            // silently fail if not beached kelp block
            return false;
        }

        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int length = random.nextInt(4);

        boolean hasSpace = checkHasSpace(level, origin, direction, length);
        if (!hasSpace) {
            return false;
        }

        state = state.with(BeachedKelpBlock.FACING, direction);
        BlockPos.Mutable pos = origin.mutableCopy();
        level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.TOP), 2);
        for (int i = 0; i < length; i++) {
            pos.move(direction.getOpposite());
            level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.STRAIGHT), 2);
        }
        if (random.nextFloat() < 0.2F) {
            pos.move(direction.getOpposite());
            level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.CURVED), 2);
            pos.move(direction.rotateYCounterclockwise());
            level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.END), 2);
        }

        return true;
    }

    private boolean checkHasSpace(StructureWorldAccess level, BlockPos origin, Direction direction, int length) {
        int totalLength = length + 2;
        BlockPos.Mutable pos = origin.mutableCopy();
        for (int i = 0; i < totalLength; i++) {
            pos = pos.move(direction.getOpposite());
            if (!level.isAir(pos) || !Block.hasTopRim(level, pos.down())) {
                return false;
            }
        }
        pos.move(direction.rotateYCounterclockwise());
        if (!level.isAir(pos) || !Block.hasTopRim(level, pos.down())) {
            return false;
        }
        return true;
    }
}
