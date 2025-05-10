package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PricklyBushBlock extends PlantBlock {

    public PricklyBushBlock(AbstractBlock.Settings props) {
        super(props);
        //AbstractBlock.Settings.of(Material.PLANT, MaterialColor.COLOR_BROWN).noCollission().sound(SoundType.SWEET_BERRY_BUSH)
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.slowMovement(state, new Vec3d(0.8F, 0.75D, 0.8F));
            if (!level.isClient && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.lastRenderX);
                double d1 = Math.abs(entity.getZ() - entity.lastRenderZ);
                if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
                    entity.damage(level.getDamageSources().sweetBerryBush(), 1.0F);
                }
            }
        }
    }
}