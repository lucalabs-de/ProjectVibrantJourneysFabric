package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.tags.FabricTags;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DoubleHighWaterPlantBlock extends TallPlantBlock implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private final boolean allowAdjacentToWater;

    public DoubleHighWaterPlantBlock(AbstractBlock.Settings props, boolean allowAdjacentToWater) {
        super(props);
        this.setDefaultState(this.getStateManager().getDefaultState().with(HALF, DoubleBlockHalf.LOWER).with(WATERLOGGED, false));
        this.allowAdjacentToWater = allowAdjacentToWater;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER && state.get(WATERLOGGED)) {
            return false;
        }
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            BlockPos groundPos = pos.down();
            BlockState ground = level.getBlockState(groundPos);

            if (level.getFluidState(pos).getFluid() == Fluids.WATER)
                return canGrow(level, groundPos, Direction.UP, ground);

            if (this.allowAdjacentToWater) {
                for (Direction direction : Direction.Type.HORIZONTAL) {
                    if (level.getFluidState(groundPos.offset(direction)).getFluid() == Fluids.WATER) {
                        return canGrow(level, groundPos, Direction.UP, ground);
                    }
                }
            }

            return false;
        } else {
            BlockState blockstate = level.getBlockState(pos.down());
            if (state.getBlock() != this)
                return false;
            return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public boolean canGrow(WorldView level, BlockPos pos, Direction dir, BlockState ground) {
        return ground.isIn(BlockTags.DIRT)
                || ground.isIn(BlockTags.SAND)
                || ground.isOf(Blocks.GRAVEL)
                || ground.isIn(FabricTags.SAND)
                || ground.isOf(Blocks.CLAY)
                || ground.isIn(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                || this.canPlantOnTop(ground, level, pos);
    }

//  @Override
//  public PlantType getPlantType(BlockView level, BlockPos pos) {
//    return PlantType.BEACH;
//  }

    // TODO check if the down accomplishes the same as the up and if it is even desired (or a bug in the original)
//    @Override
//    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
//        Block block = floor.getBlock();
//
//        boolean validGround = block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.DIRT;
//
//        if (!validGround) return false;
//
//        for (Direction dir : Direction.Type.HORIZONTAL) {
//            BlockPos neighborPos = pos.offset(dir);
//            if (world.getBlockState(neighborPos).getFluidState().isIn(FluidTags.WATER)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!canPlaceAt(state, world, pos)) {
            if (state.get(WATERLOGGED)) {
                WorldUtils.setBlockState(world, pos, Blocks.WATER.getDefaultState(), 2);
            } else {
                world.removeBlock(pos, false);
            }
        }
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            BlockState stateUpper = world.getBlockState(pos.up());
            if (stateUpper.getBlock() instanceof DoubleHighWaterPlantBlock) {
                if (!canPlaceAt(stateUpper, world, pos.up())) {
                    world.removeBlock(pos.up(), false);
                }
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.get(HALF);
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
            if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                if (blockstate.get(WATERLOGGED)) {
                    WorldUtils.setBlockState(world, blockpos, Blocks.WATER.getDefaultState(), 2);
                } else {
                    WorldUtils.setBlockState(world, blockpos, Blocks.AIR.getDefaultState(), 35);
                }
            }

            world.syncWorldEvent(player, 2001, blockpos, Block.getRawIdFromState(blockstate));
            if (!world.isClient && !player.isCreative()) {
                dropStacks(state, world, pos, null, player, player.getMainHandStack());
            }
        }
        world.syncWorldEvent(player, 2001, pos, Block.getRawIdFromState(state));
    }

    public void placeInWater(WorldAccess worldIn, BlockPos pos, int flags) {
        WorldUtils.setBlockState(worldIn, pos, this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER).with(WATERLOGGED, true), flags);
        WorldUtils.setBlockState(worldIn, pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), flags);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext context) {
        FluidState ifluidstate = context.getWorld().getFluidState(context.getBlockPos());

        BlockState state = super.getPlacementState(context);
        if (state != null) {
            return state.with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
        } else {
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }
}