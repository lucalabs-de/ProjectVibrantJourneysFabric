package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.blocks.properties.BeachedKelpShape;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class BeachedKelpBlock extends HorizontalFacingBlock implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<BeachedKelpShape> KELP_SHAPE = EnumProperty.of("shape", BeachedKelpShape.class);

    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);

    public BeachedKelpBlock(AbstractBlock.Settings props) {
        super(props);
        this.setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(KELP_SHAPE, BeachedKelpShape.TOP));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.hasTopRim(world, pos.down());
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return !state.canPlaceAt(world, currentPos) ? Blocks.AIR.getDefaultState() : state;
    }

    @Override
    public boolean isTransparent(BlockState pState, BlockView pReader, BlockPos pPos) {
        return pState.getFluidState().isEmpty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPathfindThrough(BlockState pState, BlockView level, BlockPos pos, NavigationType pPathComputationType) {
        return pPathComputationType == NavigationType.AIR && !this.collidable || super.canPathfindThrough(pState, level, pos, pPathComputationType);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(KELP_SHAPE, FACING, WATERLOGGED);
    }
}