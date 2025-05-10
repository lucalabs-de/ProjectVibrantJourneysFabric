package de.lucalabs.vibrantjourneys.data;

import java.util.function.Consumer;

public class PVJRecipes extends RecipeProvider {

  public PVJRecipes(PackOutput packOutput) {
    super(packOutput);
  }

  @Override
  protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
    this.buildCraftingRecipes(consumer);
    this.buildCookingRecipes(consumer);
  }

  private void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
    simpleDye(consumer, Items.BROWN_DYE, PVJItems.BARK_MUSHROOM, 1);
    simpleDye(consumer, Items.BROWN_DYE, PVJItems.LIGHT_BROWN_BARK_MUSHROOM, 1);
    simpleDye(consumer, Items.ORANGE_DYE, PVJItems.ORANGE_BARK_MUSHROOM, 1);
    simpleDye(consumer, Items.CYAN_DYE, PVJItems.WARPED_NETTLE, 1);
    simpleDye(consumer, Items.RED_DYE, PVJItems.CRIMSON_NETTLE, 1);

    simpleDye(consumer, Items.YELLOW_DYE, PVJItems.YELLOW_WILDFLOWERS, 1);
    simpleDye(consumer, Items.ORANGE_DYE, PVJItems.ORANGE_WILDFLOWERS, 1);
    simpleDye(consumer, Items.BLUE_DYE, PVJItems.BLUE_WILDFLOWERS, 1);
    simpleDye(consumer, Items.PURPLE_DYE, PVJItems.PURPLE_WILDFLOWERS, 1);
    simpleDye(consumer, Items.WHITE_DYE, PVJItems.WHITE_WILDFLOWERS, 1);
    simpleDye(consumer, Items.GREEN_DYE, PVJItems.MIXED_WILDFLOWERS, 1);

    simpleShapeless(consumer, Items.OAK_PLANKS, PVJItems.OAK_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.BIRCH_PLANKS, PVJItems.BIRCH_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.SPRUCE_PLANKS, PVJItems.SPRUCE_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.JUNGLE_PLANKS, PVJItems.JUNGLE_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.DARK_OAK_PLANKS, PVJItems.DARK_OAK_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.ACACIA_PLANKS, PVJItems.ACACIA_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.CHERRY_PLANKS, PVJItems.CHERRY_HOLLOW_LOG, 2);
    simpleShapeless(consumer, Items.MANGROVE_PLANKS, PVJItems.MANGROVE_HOLLOW_LOG, 2);

    simpleShapeless(consumer, Items.BONE, PVJItems.BONES, 1);
    simpleShapeless(consumer, Items.BONE, PVJItems.CHARRED_BONES, 1);
    simpleShapeless(consumer, Items.PRISMARINE_SHARD, PVJItems.SEASHELLS, 1);
    simpleShapeless(consumer, Items.STICK, PVJItems.TWIGS, 1);

    simpleTwoByTwo(consumer, Items.COBBLESTONE, PVJItems.ROCKS, 1);
    simpleTwoByTwo(consumer, Items.MOSSY_COBBLESTONE, PVJItems.MOSSY_ROCKS, 1);
    simpleTwoByTwo(consumer, Items.ICE, PVJItems.ICE_CHUNKS, 1);
    simpleTwoByTwo(consumer, Items.SANDSTONE, PVJItems.SANDSTONE_ROCKS, 1);
    simpleTwoByTwo(consumer, Items.RED_SANDSTONE, PVJItems.RED_SANDSTONE_ROCKS, 1);

    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SHROOMLIGHT)
      .pattern(" # ")
      .pattern("#N#")
      .pattern(" # ")
      .define('#', Items.GLOWSTONE)
      .define('N', PVJItems.GLOWCAP)
      .unlockedBy("has_glowcap", has(PVJItems.GLOWCAP))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "glowcap_to_shroomlight"));

    ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PVJItems.NETTLE_SOUP)
      .requires(PVJBlocks.WARPED_NETTLE)
      .requires(PVJBlocks.CRIMSON_NETTLE)
      .requires(Items.BOWL)
      .unlockedBy("has_nettle_soup", has(PVJItems.NETTLE_SOUP))
      .unlockedBy("has_bowl", has(Items.BOWL))
      .unlockedBy("has_warped_nettle", has(PVJBlocks.WARPED_NETTLE))
      .unlockedBy("has_crimson_nettle", has(PVJBlocks.CRIMSON_NETTLE))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "nettle_soup"));
  }

  private void buildCookingRecipes(Consumer<FinishedRecipe> consumer) {
    SimpleCookingRecipeBuilder.smoking(Ingredient.of(PVJItems.CINDERCANE), RecipeCategory.MISC, Items.BLAZE_POWDER, 0.3F, 800)
      .unlockedBy("has_cindercane", has(PVJItems.CINDERCANE))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "cindercane_to_blaze_powder"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(PVJItems.SMALL_CACTUS), RecipeCategory.MISC, Items.GREEN_DYE, 0.1F, 200)
      .unlockedBy("has_small_cactus", has(PVJItems.SMALL_CACTUS))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "small_cactus_to_green_dye"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(PVJItems.GLOWCAP), RecipeCategory.MISC, Items.GLOWSTONE_DUST, 0.1F, 200)
      .unlockedBy("has_glowcap", has(PVJItems.GLOWCAP))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "glowcap_to_glowstone_dust"));
  }

  private void simpleShapeless(Consumer<FinishedRecipe> consumer, Item output, Item input, int count) {
    String outputPath = ForgeRegistries.ITEMS.getKey(output).getPath();
    String inputPath = ForgeRegistries.ITEMS.getKey(input).getPath();
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, count)
      .requires(input)
      .unlockedBy("has_" + inputPath, has(input))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, outputPath + "_from_" + inputPath));
  }

  private void simpleTwoByTwo(Consumer<FinishedRecipe> consumer, Item output, Item input, int count) {
    String outputPath = ForgeRegistries.ITEMS.getKey(output).getPath();
    String inputPath = ForgeRegistries.ITEMS.getKey(input).getPath();
    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, count)
      .pattern("##")
      .pattern("##")
      .define('#', input)
      .unlockedBy("has_" + inputPath, has(input))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, outputPath + "_from_" + inputPath));
  }

  private void simpleDye(Consumer<FinishedRecipe> consumer, Item dye, Item ingredient, int count) {
    String dyePath = ForgeRegistries.ITEMS.getKey(dye).getPath();
    String ingredientPath = ForgeRegistries.ITEMS.getKey(ingredient).getPath();
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye, count)
      .requires(ingredient)
      .unlockedBy("has_" + ingredientPath, has(ingredient))
      .save(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, dyePath + "_from_" + ingredientPath));
  }
}
