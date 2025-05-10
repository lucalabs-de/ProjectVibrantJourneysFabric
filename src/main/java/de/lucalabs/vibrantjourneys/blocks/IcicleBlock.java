package de.lucalabs.vibrantjourneys.blocks;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class IcicleBlock extends Block implements Fallable {
  public static final DirectionProperty TIP_DIRECTION = Properties.VERTICAL_DIRECTION;
  public static final EnumProperty<DripstoneThickness> THICKNESS = Properties.DRIPSTONE_THICKNESS;
  private static final VoxelShape TIP_MERGE_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
  private static final VoxelShape TIP_SHAPE_UP = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
  private static final VoxelShape TIP_SHAPE_DOWN = Block.createCuboidShape(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);
  private static final VoxelShape FRUSTUM_SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
  private static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
  private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
  private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

  public IcicleBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(TIP_DIRECTION, Direction.UP).with(THICKNESS, DripstoneThickness.TIP));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(TIP_DIRECTION, THICKNESS);
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos) {
    return isValidPointedDripstonePlacement(level, pos, state.get(TIP_DIRECTION));
  }

  @Override
  public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState state2, WorldAccess level, BlockPos pos, BlockPos pos2) {
    if (dir != Direction.UP && dir != Direction.DOWN) {
      return state;
    } else {
      Direction direction = state.get(TIP_DIRECTION);
      if (direction == Direction.DOWN && level.getBlockTicks().hasScheduledTick(pos, this)) {
        return state;
      } else if (dir == direction.getOpposite() && !this.canSurvive(state, level, pos)) {
        if (direction == Direction.DOWN) {
          level.scheduleTick(pos, this, 2);
        } else {
          level.scheduleTick(pos, this, 1);
        }

        return state;
      } else {
        boolean flag = state.get(THICKNESS) == DripstoneThickness.TIP_MERGE;
        DripstoneThickness dripstonethickness = calculateDripstoneThickness(level, pos, direction, flag);
        return state.with(THICKNESS, dripstonethickness);
      }
    }
  }

  @Override
  public void onProjectileHit(World level, BlockState pos, BlockHitResult result, Projectile projectile) {
    BlockPos blockpos = result.getBlockPos();
    if (!level.isClientSide && projectile.mayInteract(level, blockpos) && projectile instanceof ThrownTrident && projectile.getDeltaMovement().length() > 0.6D) {
      level.destroyBlock(blockpos, true);
    }

  }

  @Override
  public void fallOn(World level, BlockState state, BlockPos pos, Entity entity, float height) {
    if (state.get(TIP_DIRECTION) == Direction.UP && state.get(THICKNESS) == DripstoneThickness.TIP) {
      entity.causeFallDamage(height + 2.0F, 2.0F, level.damageSources().stalagmite());
    } else {
      super.fallOn(level, state, pos, entity, height);
    }

  }

  @Override
  public void animateTick(BlockState state, World level, BlockPos pos, Random random) {
    if (level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos) || level.getBiome(pos).value().warmEnoughToRain(pos)) {
      if (canDrip(state)) {
        spawnDripParticle(level, pos, state);
      }
    }
  }

  @Override
  public void tick(BlockState p_154107_, ServerWorld p_154108_, BlockPos p_154109_, Random p_154110_) {
    if (isStalagmite(p_154107_) && !this.canSurvive(p_154107_, p_154108_, p_154109_)) {
      p_154108_.destroyBlock(p_154109_, true);
    } else {
      spawnFallingStalactite(p_154107_, p_154108_, p_154109_);
    }

  }

  @Override
  public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
    if (level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos) || level.getBiome(pos).value().warmEnoughToRain(pos)) {
      level.destroyBlock(pos, true);
      for (int i = 0; i < 10; i++) {
        spawnDripParticle(level, pos, state);
      }
      BlockPos blockpos = findTip(state, level, pos, 11, false);
      BlockPos cauldronPos = findFillableCauldronBelowStalactiteTip(level, blockpos);
      if (cauldronPos != null) {
        BlockState cauldron = level.getBlockState(cauldronPos);
        if (cauldron.getBlock() instanceof CauldronBlock) {
          LevelUtils.setBlockState(level, cauldronPos, Blocks.WATER_CAULDRON.getDefaultState());
        } else if (cauldron.getBlock() == Blocks.WATER_CAULDRON) {
          if (!((LayeredCauldronBlock) cauldron.getBlock()).isFull(cauldron)) {
            LevelUtils.setBlockState(level, cauldronPos, cauldron.with(LayeredCauldronBlock.LEVEL, Integer.valueOf(cauldron.get(LayeredCauldronBlock.LEVEL) + 1)));
          }
        }
      }
    }
  }

  @Nullable
  private static BlockPos findFillableCauldronBelowStalactiteTip(World level, BlockPos pos) {
    if (pos == null) {
      return null;
    }
    Predicate<BlockState> predicate = (state) -> {
      return state.getBlock() == Blocks.CAULDRON || state.getBlock() == Blocks.WATER_CAULDRON;
    };
    BiPredicate<BlockPos, BlockState> bipredicate = (blockpos, state) -> {
      return canDripThrough(level, blockpos, state);
    };
    return findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse((BlockPos) null);
  }

  @Override
  public PushReaction getPistonPushReaction(BlockState state) {
    return PushReaction.DESTROY;
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(ItemPlacementContext context) {
    WorldAccess levelaccessor = context.getWorld();
    BlockPos blockpos = context.getClickedPos();
    Direction direction = context.getNearestLookingVerticalDirection().getOpposite();
    Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
    if (direction1 == null) {
      return null;
    } else {
      boolean flag = !context.isSecondaryUseActive();
      DripstoneThickness dripstonethickness = calculateDripstoneThickness(levelaccessor, blockpos, direction1, flag);
      return dripstonethickness == null ? null : this.getDefaultState().with(TIP_DIRECTION, direction1).with(THICKNESS, dripstonethickness);
    }
  }

  @Override
  public VoxelShape getOcclusionShape(BlockState state, BlockView level, BlockPos pos) {
    return VoxelShapes.empty();
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
    DripstoneThickness dripstonethickness = state.get(THICKNESS);
    VoxelShape voxelshape;
    if (dripstonethickness == DripstoneThickness.TIP_MERGE) {
      voxelshape = TIP_MERGE_SHAPE;
    } else if (dripstonethickness == DripstoneThickness.TIP) {
      if (state.get(TIP_DIRECTION) == Direction.DOWN) {
        voxelshape = TIP_SHAPE_DOWN;
      } else {
        voxelshape = TIP_SHAPE_UP;
      }
    } else if (dripstonethickness == DripstoneThickness.FRUSTUM) {
      voxelshape = FRUSTUM_SHAPE;
    } else if (dripstonethickness == DripstoneThickness.MIDDLE) {
      voxelshape = MIDDLE_SHAPE;
    } else {
      voxelshape = BASE_SHAPE;
    }

    Vec3 vec3 = state.getOffset(level, pos);
    return voxelshape.move(vec3.x, 0.0D, vec3.z);
  }

  @Override
  public boolean isCollisionShapeFullBlock(BlockState state, BlockView level, BlockPos pos) {
    return false;
  }

  @Override
  public float getMaxHorizontalOffset() {
    return 0.125F;
  }

  @Override
  public void onBrokenAfterFall(World level, BlockPos pos, FallingBlockEntity entity) {
    if (!entity.isSilent()) {
      level.levelEvent(1045, pos, 0);
    }

  }

  private static void spawnFallingStalactite(BlockState state, ServerWorld level, BlockPos pos) {
    BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

    for (BlockState blockstate = state; isStalactite(blockstate); blockstate = level.getBlockState(blockpos$mutableblockpos)) {
      FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, blockpos$mutableblockpos, blockstate);
      if (isTip(blockstate, true)) {
        int i = Math.max(1 + pos.getY() - blockpos$mutableblockpos.getY(), 6);
        float f = 1.0F * (float) i;
        fallingblockentity.setHurtsEntities(f, 40);
        break;
      }

      blockpos$mutableblockpos.move(Direction.DOWN);
    }

  }

  private static void spawnDripParticle(World level, BlockPos pos, BlockState state) {
    Vec3 vec3 = state.getOffset(level, pos);
    double d1 = (double) pos.getX() + 0.5D + vec3.x;
    double d2 = (double) ((float) (pos.getY() + 1) - 0.6875F) - 0.0625D;
    double d3 = (double) pos.getZ() + 0.5D + vec3.z;
    ParticleOptions particleoptions = ParticleTypes.DRIPPING_DRIPSTONE_WATER;
    level.addParticle(particleoptions, d1, d2, d3, 0.0D, 0.0D, 0.0D);
  }

  @Nullable
  private static BlockPos findTip(BlockState state, WorldAccess level, BlockPos pos, int p_154134_, boolean p_154135_) {
    if (isTip(state, p_154135_)) {
      return pos;
    } else {
      Direction direction = state.get(TIP_DIRECTION);
      BiPredicate<BlockPos, BlockState> bipredicate = (p_202023_, p_202024_) -> {
        return p_202024_.is(PVJBlocks.ICICLE) && p_202024_.get(TIP_DIRECTION) == direction;
      };
      return findBlockVertical(level, pos, direction.getAxisDirection(), bipredicate, (p_154168_) -> {
        return isTip(p_154168_, p_154135_);
      }, p_154134_).orElse((BlockPos) null);
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

  private static DripstoneThickness calculateDripstoneThickness(WorldView level, BlockPos pos, Direction dir, boolean p_154096_) {
    Direction direction = dir.getOpposite();
    BlockState blockstate = level.getBlockState(pos.relative(dir));
    if (isPointedDripstoneWithDirection(blockstate, direction)) {
      return !p_154096_ && blockstate.get(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
    } else if (!isPointedDripstoneWithDirection(blockstate, dir)) {
      return DripstoneThickness.TIP;
    } else {
      DripstoneThickness dripstonethickness = blockstate.get(THICKNESS);
      if (dripstonethickness != DripstoneThickness.TIP && dripstonethickness != DripstoneThickness.TIP_MERGE) {
        BlockState blockstate1 = level.getBlockState(pos.relative(direction));
        return !isPointedDripstoneWithDirection(blockstate1, dir) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
      } else {
        return DripstoneThickness.FRUSTUM;
      }
    }
  }

  public static boolean canDrip(BlockState state) {
    return isStalactite(state) && state.get(THICKNESS) == DripstoneThickness.TIP;
  }

  private static boolean isValidPointedDripstonePlacement(WorldView level, BlockPos pos, Direction dir) {
    BlockPos blockpos = pos.relative(dir.getOpposite());
    BlockState blockstate = level.getBlockState(blockpos);
    return blockstate.isFaceSturdy(level, blockpos, dir) || isPointedDripstoneWithDirection(blockstate, dir);
  }

  private static boolean isTip(BlockState state, boolean p_154155_) {
    if (!state.is(PVJBlocks.ICICLE)) {
      return false;
    } else {
      DripstoneThickness dripstonethickness = state.get(THICKNESS);
      return dripstonethickness == DripstoneThickness.TIP || p_154155_ && dripstonethickness == DripstoneThickness.TIP_MERGE;
    }
  }

  private static boolean isStalactite(BlockState state) {
    return isPointedDripstoneWithDirection(state, Direction.DOWN);
  }

  private static boolean isStalagmite(BlockState state) {
    return isPointedDripstoneWithDirection(state, Direction.UP);
  }

  private static boolean isStalactiteStartPos(BlockState state, WorldView level, BlockPos pos) {
    return isStalactite(state) && !level.getBlockState(pos.up()).is(PVJBlocks.ICICLE);
  }

  @Override
  public boolean isPathfindable(BlockState state, BlockView level, BlockPos pos, NavigationType type) {
    return false;
  }

  private static boolean isPointedDripstoneWithDirection(BlockState state, Direction dir) {
    return state.is(PVJBlocks.ICICLE) && state.get(TIP_DIRECTION) == dir;
  }

  @Nullable
  public static BlockPos findStalactiteTipAboveCauldron(World level, BlockPos pos) {
    BiPredicate<BlockPos, BlockState> bipredicate = (p_202030_, p_202031_) -> {
      return canDripThrough(level, p_202030_, p_202031_);
    };
    return findBlockVertical(level, pos, Direction.UP.getAxisDirection(), bipredicate, IcicleBlock::canDrip, 11).orElse((BlockPos) null);
  }

  private static Optional<BlockPos> findBlockVertical(WorldAccess level, BlockPos pos, Direction.AxisDirection dir, BiPredicate<BlockPos, BlockState> p_202010_, Predicate<BlockState> p_202011_, int p_202012_) {
    Direction direction = Direction.get(dir, Direction.Axis.Y);
    BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

    for (int i = 1; i < p_202012_; ++i) {
      blockpos$mutableblockpos.move(direction);
      BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
      if (p_202011_.test(blockstate)) {
        return Optional.of(blockpos$mutableblockpos.immutable());
      }

      if (level.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !p_202010_.test(blockpos$mutableblockpos, blockstate)) {
        return Optional.empty();
      }
    }

    return Optional.empty();
  }

  private static boolean canDripThrough(BlockView level, BlockPos pos, BlockState state) {
    if (state.isAir()) {
      return true;
    } else if (state.isSolidRender(level, pos)) {
      return false;
    } else if (!state.getFluidState().isEmpty()) {
      return false;
    } else {
      VoxelShape voxelshape = state.getCollisionShape(level, pos);
      return !VoxelShapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, voxelshape, BooleanOp.AND);
    }
  }
}