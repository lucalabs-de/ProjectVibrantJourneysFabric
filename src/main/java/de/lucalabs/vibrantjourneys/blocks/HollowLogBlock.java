package de.lucalabs.vibrantjourneys.blocks;

public class HollowLogBlock extends RotatedPillarBlock implements Waterloggable {

  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
  public static final BooleanProperty MOSSY = BooleanProperty.create("mossy");
  protected static final VoxelShape BOTTOM = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
  protected static final VoxelShape TOP = Block.createCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
  protected static final VoxelShape LEFT_X = Block.createCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
  protected static final VoxelShape RIGHT_X = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
  protected static final VoxelShape LEFT_Z = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
  protected static final VoxelShape RIGHT_Z = Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
  protected static final VoxelShape SHAPE_X = VoxelShapes.or(BOTTOM, TOP, LEFT_X, RIGHT_X);
  protected static final VoxelShape SHAPE_Z = VoxelShapes.or(BOTTOM, TOP, LEFT_Z, RIGHT_Z);
  protected static final VoxelShape SHAPE_Y = VoxelShapes.or(LEFT_X, RIGHT_X, LEFT_Z, RIGHT_Z);

  public HollowLogBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(WATERLOGGED, false).with(MOSSY, false));
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
    return switch (state.get(Properties.AXIS)) {
      default -> SHAPE_X;
      case Z -> SHAPE_Z;
      case Y -> SHAPE_Y;
    };
  }

  @Override
  public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, WorldAccess level, BlockPos pos, BlockPos pos2) {
    if (state.get(WATERLOGGED)) {
      level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
    }

    return state;
  }

  @Override
  public boolean canSustainPlant(BlockState state, BlockView world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
    if (state.get(AXIS) == Direction.Axis.Y && facing == Direction.UP) {
      return false;
    }

    BlockState plant = plantable.getPlant(world, pos.relative(facing));
    if (plant.is(dev.orderedchaos.projectvibrantjourneys.common.tags.PVJTags.GROWS_ON_HOLLOW_LOG)) {
      return true;
    }

    return super.canSustainPlant(state, world, pos, facing, plantable);
  }

  @Override
  public ActionResult use(BlockState state, World level, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult brt) {
    ItemStack stack = player.getItemInHand(hand);
    if (state.get(AXIS) == Direction.Axis.Y) {
      return ActionResult.PASS;
    } else if (stack.is(Items.MOSS_CARPET) && player.mayBuild()) {
      if (!state.get(MOSSY)) {
        level.setBlockState(pos, state.with(MOSSY, true), 2);
        if (!player.isCreative()) {
          stack.shrink(1);
        }
        level.playSound(player, pos, SoundEvents.MOSS_CARPET_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
      }
    } else if (state.get(MOSSY) && stack.is(dev.orderedchaos.projectvibrantjourneys.common.tags.PVJTags.HARVESTS_MOSSY_HOLLOW_LOGS)) {
      if (player instanceof ServerPlayer) {
        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
      }
      stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(hand));
      level.setBlockState(pos, state.with(MOSSY, false), 2);
      level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
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
  public FluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
  }
}
