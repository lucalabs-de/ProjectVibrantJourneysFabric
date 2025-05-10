package de.lucalabs.vibrantjourneys.blocks;

public class PricklyBushBlock extends PlantBlock {

  public PricklyBushBlock(AbstractBlock.Settings props) {
    super(props);
    //AbstractBlock.Settings.of(Material.PLANT, MaterialColor.COLOR_BROWN).noCollission().sound(SoundType.SWEET_BERRY_BUSH)
  }

  public void entityInside(BlockState state, World level, BlockPos pos, Entity entity) {
    if (entity instanceof LivingEntity) {
      entity.makeStuckInBlock(state, new Vec3d((double) 0.8F, 0.75D, (double) 0.8F));
      if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
        double d0 = Math.abs(entity.getX() - entity.xOld);
        double d1 = Math.abs(entity.getZ() - entity.zOld);
        if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
          entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
        }
      }

    }
  }
}