package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class PVJPotions {

    public static Potion GLOWING = register(
            "glowing",
            () -> new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600)));
    public static Potion LONG_GLOWING = register(
            "long_glowing",
            () -> new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 9600)));

    public static Potion SPORADIC_SILENCE = register(
            "sporadic_silence",
            () -> new Potion(new StatusEffectInstance(PVJStatusEffects.SPORADIC_SILENCE, 4800)));
    public static Potion LONG_SPORADIC_SILENCE = register(
            "long_sporadic_silence",
            () -> new Potion(new StatusEffectInstance(PVJStatusEffects.SPORADIC_SILENCE, 12000)));
    public static Potion STRONG_SPORADIC_SILENCE = register(
            "strong_sporadic_silence",
            () -> new Potion(new StatusEffectInstance(PVJStatusEffects.SPORADIC_SILENCE, 4800, 1)));

    private PVJPotions() {}

    private static Potion register(String location, Supplier<Potion> p) {
        return Registry.register(Registries.POTION, new Identifier(ProjectVibrantJourneys.MOD_ID, location), p.get());
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("initializing potion recipes");
    }

}
