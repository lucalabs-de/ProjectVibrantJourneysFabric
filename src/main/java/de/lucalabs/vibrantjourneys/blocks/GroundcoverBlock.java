package de.lucalabs.vibrantjourneys.blocks;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
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
import net.minecraft.world.WorldView;

public class GroundcoverBlock extends HorizontalFacingBlock implements Waterloggable {

  public static final IntProperty MODEL = IntProperty.of("model", 0, 4);
  public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

  protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);

  public GroundcoverBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(MODEL, 0).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return context.getStack().isEmpty() || !context.getStack().isOf(this.asItem());
    }
    return super.canReplace(state, context);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canBucketPlace(BlockState state, Fluid fluid) {
    if (PVJConfig.configOptions.get("replaceableGroundcover")) {
      return true;
    }
    return super.canBucketPlace(state, fluid);
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    int model = context.getWorld().getRandom().nextInt(5);
    Direction facing = Direction.Type.HORIZONTAL.random(context.getWorld().getRandom());
    FluidState ifluidstate = context.getWorld().getFluidState(context.getBlockPos());
    return this.getDefaultState()
      .with(MODEL, model)
      .with(FACING, facing)
      .with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
  }

  @Override
  @SuppressWarnings("deprecation")
  public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }
    return state;
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return Block.hasTopRim(world, pos.down());
  }

  @Override
  @SuppressWarnings("deprecation")
  public void neighborUpdate(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    if (!canPlaceAt(state, world, pos)) {
      world.removeBlock(pos, false);
    }
  }

  @Override
  public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }

  @Override
  @SuppressWarnings("deprecation")
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult brt) {
    if (!player.getStackInHand(hand).isEmpty()) {
      return super.onUse(state, world, pos, player, hand, brt);
    }

    if (!player.isCreative() && player.canModifyBlocks()) {
      ItemStack loot = null;

      if (!world.isClient()) {
        ItemStack tool = new ItemStack(Items.APPLE);
        tool.addEnchantment(Enchantments.SILK_TOUCH, 1); // right click mimics silk touch
        LootTable lootTable = world.getServer().getLootManager().getLootTable(this.getLootTableId());
        LootContextParameterSet lootParams = new LootContextParameterSet.Builder((ServerWorld) world)
          .add(LootContextParameters.BLOCK_STATE, state)
          .add(LootContextParameters.ORIGIN, pos.toCenterPos())
          .add(LootContextParameters.TOOL, tool)
          .build(LootContextTypes.BLOCK);
        ObjectArrayList<ItemStack> randomItems = lootTable.generateLoot(lootParams);

        if (!randomItems.isEmpty()) {
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
    builder.add(MODEL, FACING, WATERLOGGED);
  }
}
