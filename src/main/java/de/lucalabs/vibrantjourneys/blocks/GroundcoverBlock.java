package de.lucalabs.vibrantjourneys.blocks;

public class GroundcoverBlock extends HorizontalFacingBlock implements Waterloggable {

  public static final IntegerProperty MODEL = IntegerProperty.create("model", 0, 4);
  public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
  protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);

  public GroundcoverBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(MODEL, 0).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
  }

  @Override
  public boolean canBeReplaced(BlockState state, ItemPlacementContext context) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return context.getItemInHand().isEmpty() || !context.getItemInHand().is(this.asItem());
    }
    return super.canBeReplaced(state, context);
  }

  @Override
  public boolean canBeReplaced(BlockState state, Fluid fluid) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return true;
    }
    return super.canBeReplaced(state, fluid);
  }

  @Override
  public BlockState getStateForPlacement(ItemPlacementContext context) {
    int model = context.getWorld().getRandom().nextInt(5);
    Direction facing = Direction.Plane.HORIZONTAL.getRandomDirection(context.getWorld().getRandom());
    FluidState ifluidstate = context.getWorld().getFluidState(context.getClickedPos());
    return this.getDefaultState()
      .with(MODEL, model)
      .with(FACING, facing)
      .with(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
  }

  @Override
  public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
    }
    return state;
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return Block.hasTopRim(world, pos.down());
  }

  @Override
  public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    if (!canSurvive(state, world, pos)) {
      world.destroyBlock(pos, false);
    }
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }

  @Override
  public ActionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, InteractionHand hand, BlockHitResult brt) {
    if (!player.getItemInHand(hand).isEmpty()) {
      return super.use(state, world, pos, player, hand, brt);
    }

    if (!player.isCreative() && player.mayBuild()) {
      ItemStack loot = null;

      if (!world.isClientSide()) {
        ItemStack tool = new ItemStack(Items.APPLE);
        tool.enchant(Enchantments.SILK_TOUCH, 1); // right click mimics silk touch
        LootTable lootTable = world.getServer().getLootData().getLootTable(this.getLootTable());
        LootParams lootParams = new LootParams.Builder((ServerWorld) world)
          .withParameter(LootContextParams.BLOCK_STATE, state)
          .withParameter(LootContextParams.ORIGIN, pos.getCenter())
          .withParameter(LootContextParams.TOOL, tool)
          .create(LootContextParamSets.BLOCK);
        ObjectArrayList<ItemStack> randomItems = lootTable.getRandomItems(lootParams);

        if (randomItems.size() > 0) {
          loot = randomItems.get(0);
        }
      }

      if (loot == null) {
        loot = new ItemStack(this);
      }

      ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), loot);
    }

    world.removeBlock(pos, true);

    return ActionResult.SUCCESS;
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  @SuppressWarnings("deprecation")
  public FluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(MODEL, FACING, WATERLOGGED);
  }
}
