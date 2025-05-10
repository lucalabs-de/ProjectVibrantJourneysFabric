package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class EpiphyteBlock extends HorizontalFacingBlock {
  public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

  public EpiphyteBlock(AbstractBlock.Settings props) {
    super(props);
    this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
  }

  @Override
  @SuppressWarnings("deprecation")
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult brt) {
    if (!player.getAbilities().allowModifyWorld) {
      return ActionResult.PASS;
    } else {
      if (!player.isCreative())
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this));
      world.removeBlock(pos, false);
      return ActionResult.SUCCESS;
    }
  }

  public boolean canAttachTo(BlockView world, BlockPos pos, Direction direction) {
    BlockState blockstate = world.getBlockState(pos);
    return blockstate.isIn(BlockTags.LOGS) && Block.isFaceFullSquare(world.getBlockState(pos).getCollisionShape(world, pos), direction);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
    Direction direction = state.get(FACING);
    return canAttachTo(worldIn, pos.offset(direction.getOpposite()), direction);
  }

  @Override
  @SuppressWarnings("deprecation")
  public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
    if (facing.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, currentPos)) {
      return Blocks.AIR.getDefaultState();
    } else {
      return super.getStateForNeighborUpdate(state, facing, facingState, world, currentPos, facingPos);
    }
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    if (!context.canReplaceExisting()) {
      BlockState blockstate = context.getWorld().getBlockState(context.getBlockPos().offset(context.getSide().getOpposite()));
      if (blockstate.getBlock() == this && blockstate.get(FACING) == context.getSide()) {
        return null;
      }
    }

    BlockState blockstate1 = this.getDefaultState();
    WorldView WorldView = context.getWorld();
    BlockPos blockpos = context.getBlockPos();

    for (Direction direction : context.getPlacementDirections()) {
      if (direction.getAxis().isHorizontal()) {
        blockstate1 = blockstate1.with(FACING, direction.getOpposite());
        if (blockstate1.canPlaceAt(WorldView, blockpos)) {
          return blockstate1;
        }
      }
    }

    return null;
  }

  @Override
  public BlockState rotate(BlockState state, BlockRotation rot) {
    return state.with(FACING, rot.rotate(state.get(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
    return state.rotate(mirrorIn.getRotation(state.get(FACING)));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}
