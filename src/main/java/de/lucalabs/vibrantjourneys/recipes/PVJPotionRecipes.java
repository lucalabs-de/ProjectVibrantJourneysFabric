package de.lucalabs.vibrantjourneys.recipes;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.registry.PVJItems;
import de.lucalabs.vibrantjourneys.registry.PVJPotions;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;

public class PVJPotionRecipes {

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("initializing potion recipes");

        recipe(Potions.AWKWARD, PVJItems.GLOWCAP, PVJPotions.GLOWING);
        recipe(PVJPotions.GLOWING, Items.REDSTONE, PVJPotions.LONG_GLOWING);
        // TODO check that splash and lingering potions are really added automatically
//        recipe(GLOWING_SPLASH_POTION, Items.REDSTONE, customSplashPotion(PVJPotions.LONG_GLOWING));
//        recipe(GLOWING_LINGERING_POTION, Items.REDSTONE, customLingeringPotion(PVJPotions.LONG_GLOWING));

        recipe(Potions.AWKWARD, PVJItems.GLOWING_BLUE_FUNGUS, PVJPotions.SPORADIC_SILENCE);
        recipe(PVJPotions.SPORADIC_SILENCE, Items.REDSTONE, PVJPotions.LONG_SPORADIC_SILENCE);
//        recipe(SPORADIC_SILENCE_SPLASH_POTION, Items.REDSTONE, customSplashPotion(PVJPotions.LONG_SPORADIC_SILENCE));
//        recipe(SPORADIC_SILENCE_LINGERING_POTION, Items.REDSTONE, customLingeringPotion(PVJPotions.LONG_SPORADIC_SILENCE));
    }

    private static void recipe(Potion input, Item ingredient, Potion output) {
        BrewingRecipeRegistry.registerPotionRecipe(input, ingredient, output);
    }
}
