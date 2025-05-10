package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;

public class BeachedKelpFeature extends Feature<BlockStateConfiguration> {

    public BeachedKelpFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        BlockStateConfiguration config = context.config();
        Random random = context.random();
        BlockPos origin = context.origin();
        StructureWorldAccess level = context.level();
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
        BlockPos.MutableBlockPos pos = origin.mutable();
        level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.TOP), 2);
        for (int i = 0; i < length; i++) {
            pos.move(direction.getOpposite());
            level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.STRAIGHT), 2);
        }
        if (random.nextFloat() < 0.2F) {
            pos.move(direction.getOpposite());
            level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.CURVED), 2);
            pos.move(direction.getCounterClockWise());
            level.setBlockState(pos, state.with(BeachedKelpBlock.KELP_SHAPE, BeachedKelpShape.END), 2);
        }

        return true;
    }

    private boolean checkHasSpace(StructureWorldAccess level, BlockPos origin, Direction direction, int length) {
        int totalLength = length + 2;
        BlockPos.MutableBlockPos pos = origin.mutable();
        for (int i = 0; i < totalLength; i++) {
            pos = pos.move(direction.getOpposite());
            if (!level.isAir(pos) || !Block.hasTopRim(level, pos.down())) {
                return false;
            }
        }
        pos.move(direction.getCounterClockWise());
        if (!level.isAir(pos) || !Block.hasTopRim(level, pos.down())) {
            return false;
        }
        return true;
    }
}
