package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.*;
import net.minecraft.block.enums.Thickness;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class IcicleBlock extends Block implements LandingBlock {
    public static final DirectionProperty TIP_DIRECTION = Properties.VERTICAL_DIRECTION;
    public static final EnumProperty<Thickness> THICKNESS = Properties.THICKNESS;
    private static final VoxelShape TIP_MERGE_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape TIP_SHAPE_UP = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
    private static final VoxelShape TIP_SHAPE_DOWN = Block.createCuboidShape(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape FRUSTUM_SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    private static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    public IcicleBlock(AbstractBlock.Settings props) {
        super(props.pistonBehavior(PistonBehavior.DESTROY));
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(TIP_DIRECTION, Direction.UP)
                .with(THICKNESS, Thickness.TIP));
    }

    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(World level, BlockPos pos) {
        if (pos == null) {
            return null;
        }
        Predicate<BlockState> predicate = (state) -> state.getBlock() == Blocks.CAULDRON || state.getBlock() == Blocks.WATER_CAULDRON;
        BiPredicate<BlockPos, BlockState> bipredicate = (blockpos, state) -> canDripThrough(level, blockpos, state);
        return findBlockVertical(level, pos, Direction.DOWN.getDirection(), bipredicate, predicate).orElse((BlockPos) null);
    }

    private static void spawnFallingStalactite(BlockState state, ServerWorld level, BlockPos pos) {
        BlockPos.Mutable posM = pos.mutableCopy();

        for (BlockState blockstate = state; isStalactite(blockstate); blockstate = level.getBlockState(posM)) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.spawnFromBlock(level, posM, blockstate);
            if (isTip(blockstate, true)) {
                int i = Math.max(1 + pos.getY() - posM.getY(), 6);
                float f = (float) i;
                fallingblockentity.setHurtEntities(f, 40);
                break;
            }

            posM.move(Direction.DOWN);
        }

    }

    private static void spawnDripParticle(World level, BlockPos pos, BlockState state) {
        Vec3d vec3 = state.getModelOffset(level, pos);
        double d1 = pos.getX() + 0.5D + vec3.x;
        double d2 = pos.getY() + 1 - 0.6875F - 0.0625D;
        double d3 = pos.getZ() + 0.5D + vec3.z;
        ParticleEffect particles = ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        level.addParticle(particles, d1, d2, d3, 0.0D, 0.0D, 0.0D);
    }

    @Nullable
    private static BlockPos findTip(BlockState state, WorldAccess level, BlockPos pos) {
        if (isTip(state, false)) {
            return pos;
        } else {
            Direction direction = state.get(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> bipredicate = (p_202023_, p_202024_) -> p_202024_.isOf(PVJBlocks.ICICLE) && p_202024_.get(TIP_DIRECTION) == direction;
            return findBlockVertical(level, pos, direction.getDirection(), bipredicate, (p_154168_) -> isTip(p_154168_, false)).orElse((BlockPos) null);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(WorldView level, BlockPos pos, Direction dir) {
        Direction direction;
        if (isValidPointedDripstonePlacement(level, pos, dir)) {
            direction = dir;
        } else {
            if (!isValidPointedDripstonePlacement(level, pos, dir.getOpposite())) {
                return null;
            }

            direction = dir.getOpposite();
        }

        return direction;
    }

    private static Thickness calculateDripstoneThickness(WorldView level, BlockPos pos, Direction dir, boolean p_154096_) {
        Direction direction = dir.getOpposite();
        BlockState blockstate = level.getBlockState(pos.offset(dir));
        if (isPointedDripstoneWithDirection(blockstate, direction)) {
            return !p_154096_ && blockstate.get(THICKNESS) != Thickness.TIP_MERGE ? Thickness.TIP : Thickness.TIP_MERGE;
        } else if (!isPointedDripstoneWithDirection(blockstate, dir)) {
            return Thickness.TIP;
        } else {
            Thickness dripstonethickness = blockstate.get(THICKNESS);
            if (dripstonethickness != Thickness.TIP && dripstonethickness != Thickness.TIP_MERGE) {
                BlockState blockstate1 = level.getBlockState(pos.offset(direction));
                return !isPointedDripstoneWithDirection(blockstate1, dir) ? Thickness.BASE : Thickness.MIDDLE;
            } else {
                return Thickness.FRUSTUM;
            }
        }
    }

    public static boolean canDrip(BlockState state) {
        return isStalactite(state) && state.get(THICKNESS) == Thickness.TIP;
    }

    private static boolean isValidPointedDripstonePlacement(WorldView level, BlockPos pos, Direction dir) {
        BlockPos blockpos = pos.offset(dir.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isSideSolidFullSquare(level, blockpos, dir) || isPointedDripstoneWithDirection(blockstate, dir);
    }

    private static boolean isTip(BlockState state, boolean p_154155_) {
        if (!state.isOf(PVJBlocks.ICICLE)) {
            return false;
        } else {
            Thickness dripstonethickness = state.get(THICKNESS);
            return dripstonethickness == Thickness.TIP || p_154155_ && dripstonethickness == Thickness.TIP_MERGE;
        }
    }

    private static boolean isStalactite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.DOWN);
    }

    private static boolean isStalagmite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.UP);
    }

    private static boolean isStalactiteStartPos(BlockState state, WorldView level, BlockPos pos) {
        return isStalactite(state) && !level.getBlockState(pos.up()).isOf(PVJBlocks.ICICLE);
    }

    private static boolean isPointedDripstoneWithDirection(BlockState state, Direction dir) {
        return state.isOf(PVJBlocks.ICICLE) && state.get(TIP_DIRECTION) == dir;
    }

    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(World level, BlockPos pos) {
        BiPredicate<BlockPos, BlockState> bipredicate = (p_202030_, p_202031_) -> canDripThrough(level, p_202030_, p_202031_);
        return findBlockVertical(level, pos, Direction.UP.getDirection(), bipredicate, IcicleBlock::canDrip).orElse(null);
    }

    private static Optional<BlockPos> findBlockVertical(WorldAccess level, BlockPos pos, Direction.AxisDirection dir, BiPredicate<BlockPos, BlockState> p_202010_, Predicate<BlockState> p_202011_) {
        Direction direction = Direction.get(dir, Direction.Axis.Y);
        BlockPos.Mutable posM = pos.mutableCopy();

        for (int i = 1; i < 11; ++i) {
            posM.move(direction);
            BlockState blockstate = level.getBlockState(posM);
            if (p_202011_.test(blockstate)) {
                return Optional.of(posM.toImmutable());
            }

            if (level.isOutOfHeightLimit(posM.getY()) || !p_202010_.test(posM, blockstate)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    private static boolean canDripThrough(BlockView level, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return true;
        } else if (state.isOpaqueFullCube(level, pos)) {
            return false;
        } else if (!state.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape voxelshape = state.getCollisionShape(level, pos);
            return !VoxelShapes.matchesAnywhere(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, voxelshape, BooleanBiFunction.AND);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TIP_DIRECTION, THICKNESS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos) {
        return isValidPointedDripstonePlacement(level, pos, state.get(TIP_DIRECTION));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState state2, WorldAccess level, BlockPos pos, BlockPos pos2) {
        if (dir != Direction.UP && dir != Direction.DOWN) {
            return state;
        } else {
            Direction direction = state.get(TIP_DIRECTION);
            if (direction == Direction.DOWN && level.getBlockTickScheduler().isQueued(pos, this)) {
                return state;
            } else if (dir == direction.getOpposite() && !this.canPlaceAt(state, level, pos)) {
                if (direction == Direction.DOWN) {
                    level.scheduleBlockTick(pos, this, 2);
                } else {
                    level.scheduleBlockTick(pos, this, 1);
                }

                return state;
            } else {
                boolean flag = state.get(THICKNESS) == Thickness.TIP_MERGE;
                Thickness dripstonethickness = calculateDripstoneThickness(level, pos, direction, flag);
                return state.with(THICKNESS, dripstonethickness);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onProjectileHit(World level, BlockState pos, BlockHitResult result, ProjectileEntity projectile) {
        BlockPos blockpos = result.getBlockPos();
        if (!level.isClient
                && projectile.canModifyAt(level, blockpos)
                && projectile instanceof TridentEntity
                && projectile.getVelocity().length() > 0.6D) {
            level.removeBlock(blockpos, true);
        }

    }

    @Override
    public void onLandedUpon(World level, BlockState state, BlockPos pos, Entity entity, float height) {
        if (state.get(TIP_DIRECTION) == Direction.UP && state.get(THICKNESS) == Thickness.TIP) {
            entity.handleFallDamage(height + 2.0F, 2.0F, level.getDamageSources().stalagmite());
        } else {
            super.onLandedUpon(level, state, pos, entity, height);
        }

    }

    @Override
    public void randomDisplayTick(BlockState state, World level, BlockPos pos, Random random) {
        if (level.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(level, pos) || level.getBiome(pos).value().doesNotSnow(pos)) {
            if (canDrip(state)) {
                spawnDripParticle(level, pos, state);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState p_154107_, ServerWorld p_154108_, BlockPos p_154109_, Random p_154110_) {
        if (isStalagmite(p_154107_) && !this.canPlaceAt(p_154107_, p_154108_, p_154109_)) {
            p_154108_.removeBlock(p_154109_, true);
        } else {
            spawnFallingStalactite(p_154107_, p_154108_, p_154109_);
        }

    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        if (level.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(level, pos) || level.getBiome(pos).value().doesNotSnow(pos)) {
            level.removeBlock(pos, true);
            for (int i = 0; i < 10; i++) {
                spawnDripParticle(level, pos, state);
            }
            BlockPos blockpos = findTip(state, level, pos);
            BlockPos cauldronPos = findFillableCauldronBelowStalactiteTip(level, blockpos);
            if (cauldronPos != null) {
                BlockState cauldron = level.getBlockState(cauldronPos);
                if (cauldron.getBlock() instanceof CauldronBlock) {
                    WorldUtils.setBlockState(level, cauldronPos, Blocks.WATER_CAULDRON.getDefaultState());
                } else if (cauldron.getBlock() == Blocks.WATER_CAULDRON) {
                    if (!((LeveledCauldronBlock) cauldron.getBlock()).isFull(cauldron)) {
                        WorldUtils.setBlockState(
                                level,
                                cauldronPos,
                                cauldron.with(LeveledCauldronBlock.LEVEL, cauldron.get(LeveledCauldronBlock.LEVEL) + 1));
                    }
                }
            }
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext context) {
        WorldAccess levelaccessor = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        Direction direction = context.getVerticalPlayerLookDirection().getOpposite();
        Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
        if (direction1 == null) {
            return null;
        } else {
            boolean flag = !context.shouldCancelInteraction();
            Thickness dripstonethickness = calculateDripstoneThickness(levelaccessor, blockpos, direction1, flag);
            return dripstonethickness == null ? null : this.getDefaultState().with(TIP_DIRECTION, direction1).with(THICKNESS, dripstonethickness);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCullingShape(BlockState state, BlockView level, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        Thickness dripstonethickness = state.get(THICKNESS);
        VoxelShape voxelshape;
        if (dripstonethickness == Thickness.TIP_MERGE) {
            voxelshape = TIP_MERGE_SHAPE;
        } else if (dripstonethickness == Thickness.TIP) {
            if (state.get(TIP_DIRECTION) == Direction.DOWN) {
                voxelshape = TIP_SHAPE_DOWN;
            } else {
                voxelshape = TIP_SHAPE_UP;
            }
        } else if (dripstonethickness == Thickness.FRUSTUM) {
            voxelshape = FRUSTUM_SHAPE;
        } else if (dripstonethickness == Thickness.MIDDLE) {
            voxelshape = MIDDLE_SHAPE;
        } else {
            voxelshape = BASE_SHAPE;
        }

        Vec3d vec3 = state.getModelOffset(level, pos);
        return voxelshape.offset(vec3.x, 0.0D, vec3.z);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isShapeFullCube(BlockState state, BlockView level, BlockPos pos) {
        return false;
    }

    @Override
    public float getMaxHorizontalModelOffset() {
        return 0.125F;
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity entity) {
        if (!entity.isSilent()) {
            world.syncWorldEvent(1045, pos, 0);
        }

    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPathfindThrough(BlockState state, BlockView level, BlockPos pos, NavigationType type) {
        return false;
    }
}