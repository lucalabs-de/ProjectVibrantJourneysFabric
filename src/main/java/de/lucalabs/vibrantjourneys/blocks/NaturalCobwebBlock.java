package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

/**
 * Mimic vanilla block but make it destroy itself when not adjacent to any leaves
 * to prevent odd floating cobwebs when trees are cut down
 */
public class NaturalCobwebBlock extends CobwebBlock {

    public NaturalCobwebBlock() {
        super(AbstractBlock.Settings.copy(Blocks.COBWEB));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (Direction d : Direction.values()) {
            if (world.getBlockState(pos.offset(d)).getBlock() instanceof LeavesBlock) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!canPlaceAt(state, world, pos))
            world.removeBlock(pos, isMoving);
    }

    @Override
    @SuppressWarnings("deprecation")
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        ItemStack heldItem = player.getMainHandStack();
        if (heldItem.getItem() instanceof SwordItem) {
            return 15.0F;
        }
        return super.calcBlockBreakingDelta(state, player, world, pos);
    }
}