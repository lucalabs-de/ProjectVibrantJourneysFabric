package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ShortGrassBlock extends PlantBlock {

    public static final IntProperty MODEL = IntProperty.of("model", 0, 6);

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public ShortGrassBlock(AbstractBlock.Settings props) {
        super(props);
        this.setDefaultState(this.getStateManager().getDefaultState().with(MODEL, 0));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        int model = context.getWorld().getRandom().nextInt(7);
        return this.getDefaultState().with(MODEL, model);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MODEL);
    }
}