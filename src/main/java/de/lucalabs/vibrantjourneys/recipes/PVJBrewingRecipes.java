package de.lucalabs.vibrantjourneys.recipes;

public class PVJBrewingRecipes {

  private static final Ingredient AWKWARD_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD));
  private static final Ingredient GLOWING_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), PVJPotions.GLOWING));
  private static final Ingredient GLOWING_SPLASH_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), PVJPotions.GLOWING));
  private static final Ingredient GLOWING_LINGERING_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), PVJPotions.GLOWING));

  private static final Ingredient SPORADIC_SILENCE_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), PVJPotions.GLOWING));
  private static final Ingredient SPORADIC_SILENCE_SPLASH_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), PVJPotions.GLOWING));
  private static final Ingredient SPORADIC_SILENCE_LINGERING_POTION = StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), PVJPotions.GLOWING));


  public static void init() {
    recipe(AWKWARD_POTION, PVJItems.GLOWCAP, customPotion(PVJPotions.GLOWING));
    recipe(GLOWING_POTION, Items.REDSTONE, customPotion(PVJPotions.LONG_GLOWING));
    recipe(GLOWING_SPLASH_POTION, Items.REDSTONE, customSplashPotion(PVJPotions.LONG_GLOWING));
    recipe(GLOWING_LINGERING_POTION, Items.REDSTONE, customLingeringPotion(PVJPotions.LONG_GLOWING));

    recipe(AWKWARD_POTION, PVJBlocks.GLOWING_BLUE_FUNGUS, customPotion(PVJPotions.SPORADIC_SILENCE));
    recipe(SPORADIC_SILENCE_POTION, Items.REDSTONE, customPotion(PVJPotions.LONG_SPORADIC_SILENCE));
    recipe(SPORADIC_SILENCE_SPLASH_POTION, Items.REDSTONE, customSplashPotion(PVJPotions.LONG_SPORADIC_SILENCE));
    recipe(SPORADIC_SILENCE_LINGERING_POTION, Items.REDSTONE, customLingeringPotion(PVJPotions.LONG_SPORADIC_SILENCE));
  }

  private static void recipe(Ingredient input, ItemLike ingredient, ItemStack output) {
    BrewingRecipeRegistry.addRecipe(input, Ingredient.of(ingredient), output);
  }

  private static ItemStack customPotion(Potion potion) {
    return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
  }

  private static ItemStack customSplashPotion(Potion potion) {
    return PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion);
  }

  private static ItemStack customLingeringPotion(Potion potion) {
    return PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion);
  }
}
