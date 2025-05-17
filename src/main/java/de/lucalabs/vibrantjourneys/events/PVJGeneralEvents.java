package de.lucalabs.vibrantjourneys.events;

public class PVJGeneralEvents {

  // We need this because the SWORD_EFFICIENT block tag only sets the speed to 1.5
  @SubscribeEvent
  public void harvestCobweb(PlayerEvent.BreakSpeed event) {
    if (event.getState().getBlock() == PVJBlocks.NATURAL_COBWEB) {
      Item item = event.getEntity().getMainHandItem().getItem();
      if (item instanceof SwordItem || item instanceof ShearsItem) {
        event.setNewSpeed(15.0F);
      }
    }
  }

  @SubscribeEvent
  public void harvestCobweb(PlayerEvent.HarvestCheck event) {
    if (event.getTargetBlock().getBlock() == PVJBlocks.NATURAL_COBWEB) {
      Item item = event.getEntity().getMainHandItem().getItem();
      if (item instanceof SwordItem || item instanceof ShearsItem) {
        event.setCanHarvest(true);
      }
    }
  }


  @SubscribeEvent
  public void checkVibrationEvent(VanillaGameEvent event) {
    if (event.getVanillaEvent().is(GameEventTags.VIBRATIONS)) {
      if (event.getCause() instanceof LivingEntity entity) {
        StatusEffectInstance mobEffectInstance = entity.getEffect(PVJMobEffects.SPORADIC_SILENCE);
        if (mobEffectInstance != null) {
          Random random = event.getWorld().getRandom();
          float threshold = ((mobEffectInstance.getAmplifier() + 1) * 0.25F) + ((mobEffectInstance.getAmplifier() + 1) * 0.05F * random.nextFloat());
          if (random.nextFloat() < threshold) {
            event.setCanceled(true);
          }
        }
      }
    }
  }
}
