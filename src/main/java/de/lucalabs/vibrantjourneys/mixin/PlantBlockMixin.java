package de.lucalabs.vibrantjourneys.mixin;

import de.lucalabs.vibrantjourneys.blocks.PlantDecider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public abstract class PlantBlockMixin extends Block {

    public PlantBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    private void checkGroundBlock(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        Block below = world.getBlockState(pos.down()).getBlock();
        if (below instanceof PlantDecider decider) {
            if (decider.acceptsPlant(state, world, pos, Direction.UP, getDefaultState())) {
                cir.setReturnValue(true);
            }
        }
    }
}
