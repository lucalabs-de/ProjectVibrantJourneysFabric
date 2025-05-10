package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MudBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.Direction;

public class MuddyBonesBlock extends MudBlock {

    public static final IntProperty MODEL = IntProperty.of("model", 0, 2);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public MuddyBonesBlock(Settings props) {
        super(props);
        this.setDefaultState(this.getStateManager().getDefaultState().with(MODEL, 0).with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        int model = context.getWorld().getRandom().nextInt(3);
        Direction facing = Direction.Type.HORIZONTAL.random(context.getWorld().getRandom());

        return this.getDefaultState()
                .with(MODEL, model)
                .with(FACING, facing);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MODEL, FACING);
    }
}