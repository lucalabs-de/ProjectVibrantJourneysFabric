package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class PVJStatusEffects {

    public static final StatusEffect SPORADIC_SILENCE = register(
            "sporadic_silence",
            () -> new BaseMobEffect(StatusEffectCategory.BENEFICIAL, 0x052a32));

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("initializing status effects");
    }

    private static StatusEffect register(String name, Supplier<StatusEffect> supplier) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(ProjectVibrantJourneys.MOD_ID, name), supplier.get());
    }

    private static class BaseMobEffect extends StatusEffect {
        protected BaseMobEffect(StatusEffectCategory pCategory, int pColor) {
            super(pCategory, pColor);
        }
    }
}