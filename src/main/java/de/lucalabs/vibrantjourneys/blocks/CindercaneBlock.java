package de.lucalabs.vibrantjourneys.blocks;

public class CindercaneBlock extends Block implements IPlantable {

  public static final IntegerProperty AGE = Properties.AGE_15;
  protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
  public static final int MAX_HEIGHT = 7;

  public CindercaneBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(AGE, Integer.valueOf(0)));
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  public BlockState getStateForAge(int age) {
    return this.getDefaultState().with(AGE, Integer.valueOf(age));
  }

  @Override
  public void tick(BlockState state, ServerWorld level, BlockPos pos, Random rand) {
    if (!state.canSurvive(level, pos)) {
      level.destroyBlock(pos, true);
    } else if (level.getDayTime() % 300 == 0) {
      this.randomTick(state, level, pos, rand);
    }
  }

  @Override
  public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random rand) {
    if (level.isEmptyBlock(pos.up())) {
      int i;
      for (i = 1; level.getBlockState(pos.down(i)).is(this); ++i) {
      }

      if (i < MAX_HEIGHT && rand.nextInt(3) == 0) {
        int j = state.get(AGE);
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
          if (j == 15) {
            level.setBlockState(pos.up(), this.getDefaultState());
            level.setBlockState(pos, this.getStateForAge(0), 4);
          } else {
            level.setBlockState(pos, this.getStateForAge(j + 1), 4);
          }
        }
      }
    }
  }

  @Override
  public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, WorldAccess world, BlockPos pos, BlockPos pos2) {
    if (!state.canSurvive(world, pos)) {
      world.scheduleTick(pos, this, 1);
    }

    return super.getStateForNeighborUpdate(state, direction, state2, world, pos, pos2);
  }

  @Override
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    BlockState blockstate = world.getBlockState(pos.down());
    if (blockstate.getBlock() == this) {
      return true;
    } else {
      if (blockstate.is(Blocks.NETHERRACK) || blockstate.is(Blocks.CRIMSON_NYLIUM)
        || blockstate.is(Blocks.WARPED_NYLIUM) || blockstate.is(Blocks.SOUL_SAND)
        || blockstate.is(Blocks.SOUL_SOIL) || blockstate.is(Blocks.BASALT)
        || blockstate.is(Blocks.BLACKSTONE)) {
        BlockPos blockpos = pos.down();

        for (Direction direction : Direction.Plane.HORIZONTAL) {
          BlockState blockstate1 = world.getBlockState(blockpos.relative(direction));
          FluidState fluidstate = world.getFluidState(blockpos.relative(direction));
          if (fluidstate.is(FluidTags.LAVA) || blockstate1.is(Blocks.MAGMA_BLOCK)) {
            return true;
          }
        }
      }

      return false;
    }
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> container) {
    container.add(AGE);
  }

  @Override
  public net.minecraftforge.common.PlantType getPlantType(BlockView world, BlockPos pos) {
    return PlantType.NETHER;
  }

  @Override
  public BlockState getPlant(BlockView world, BlockPos pos) {
    return getDefaultState();
  }
}
