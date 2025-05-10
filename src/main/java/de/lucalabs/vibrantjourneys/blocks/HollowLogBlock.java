package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.tags.PVJTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class HollowLogBlock extends PillarBlock implements Waterloggable, PlantDecider {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty MOSSY = BooleanProperty.of("mossy");
    protected static final VoxelShape BOTTOM = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape TOP = Block.createCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEFT_X = Block.createCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape RIGHT_X = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape LEFT_Z = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape RIGHT_Z = Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_X = VoxelShapes.union(BOTTOM, TOP, LEFT_X, RIGHT_X);
    protected static final VoxelShape SHAPE_Z = VoxelShapes.union(BOTTOM, TOP, LEFT_Z, RIGHT_Z);
    protected static final VoxelShape SHAPE_Y = VoxelShapes.union(LEFT_X, RIGHT_X, LEFT_Z, RIGHT_Z);

    public HollowLogBlock(AbstractBlock.Settings props) {
        super(props);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(WATERLOGGED, false).with(MOSSY, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return switch (state.get(Properties.AXIS)) {
            case Z -> SHAPE_Z;
            case Y -> SHAPE_Y;
            default -> SHAPE_X;
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, WorldAccess level, BlockPos pos, BlockPos pos2) {
        if (state.get(WATERLOGGED)) {
            level.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(level));
        }

        return state;
    }

    @Override
    public boolean acceptsPlant(BlockState state, BlockView world, BlockPos pos, Direction facing, BlockState plant) {
        if (state.get(AXIS) == Direction.Axis.Y && facing == Direction.UP) {
            return false;
        }

        return plant.isIn(PVJTags.GROWS_ON_HOLLOW_LOG);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult brt) {
        ItemStack stack = player.getStackInHand(hand);
        if (state.get(AXIS) == Direction.Axis.Y) {
            return ActionResult.PASS;
        } else if (stack.isOf(Items.MOSS_CARPET) && player.canModifyBlocks()) {
            if (!state.get(MOSSY)) {
                level.setBlockState(pos, state.with(MOSSY, true), 2);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                level.playSound(player, pos, SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            }
        } else if (state.get(MOSSY) && stack.isIn(PVJTags.HARVESTS_MOSSY_HOLLOW_LOGS)) {
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
            }
            stack.damage(1, player, e -> e.sendToolBreakStatus(hand));
            level.setBlockState(pos, state.with(MOSSY, false), 2);
            level.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ItemScatterer.spawn(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.MOSS_CARPET));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED, MOSSY);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
