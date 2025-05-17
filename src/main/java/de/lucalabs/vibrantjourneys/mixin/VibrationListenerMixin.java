package de.lucalabs.vibrantjourneys.mixin;

import de.lucalabs.vibrantjourneys.registry.PVJStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.tag.GameEventTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class VibrationListenerMixin {
    @Inject(
            method = "emitGameEvent",
            at = @At("HEAD"),
            cancellable = true)
    private void onTryVibration(
            GameEvent event, Vec3d emitterPos, GameEvent.Emitter emitter, CallbackInfo ci) {
        if (event.isIn(GameEventTags.VIBRATIONS)) {
            if (emitter.sourceEntity() instanceof LivingEntity entity) {
                StatusEffectInstance statusEffect = entity.getStatusEffect(PVJStatusEffects.SPORADIC_SILENCE);
                if (statusEffect != null) {
                    Random random = ((ServerWorld) (Object) this).getRandom();
                    float threshold = ((statusEffect.getAmplifier() + 1) * 0.25F) + ((statusEffect.getAmplifier() + 1) * 0.05F * random.nextFloat());
                    if (random.nextFloat() < threshold) {
                        ci.cancel();
                    }
                }
            }
        }
    }
}
