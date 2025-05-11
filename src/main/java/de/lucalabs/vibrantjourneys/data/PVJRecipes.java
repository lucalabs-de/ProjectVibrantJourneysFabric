package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import de.lucalabs.vibrantjourneys.registry.PVJItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class PVJRecipes extends FabricRecipeProvider {

  public PVJRecipes(FabricDataOutput packOutput) {
    super(packOutput);
  }

  @Override
  public void generate(Consumer<RecipeJsonProvider> consumer) {
    this.buildCraftingRecipes(consumer);
    this.buildCookingRecipes(consumer);
  }

  private void buildCraftingRecipes(Consumer<RecipeJsonProvider> consumer) {
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

    ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.SHROOMLIGHT)
      .pattern(" # ")
      .pattern("#N#")
      .pattern(" # ")
      .input('#', Items.GLOWSTONE)
      .input('N', PVJItems.GLOWCAP)
      .criterion("has_glowcap", conditionsFromItem(PVJItems.GLOWCAP))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "glowcap_to_shroomlight"));

    ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, PVJItems.NETTLE_SOUP)
      .input(PVJBlocks.WARPED_NETTLE)
      .input(PVJBlocks.CRIMSON_NETTLE)
      .input(Items.BOWL)
      .criterion("has_nettle_soup", conditionsFromItem(PVJItems.NETTLE_SOUP))
      .criterion("has_bowl", conditionsFromItem(Items.BOWL))
      .criterion("has_warped_nettle", conditionsFromItem(PVJBlocks.WARPED_NETTLE))
      .criterion("has_crimson_nettle", conditionsFromItem(PVJBlocks.CRIMSON_NETTLE))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "nettle_soup"));
  }

  private void buildCookingRecipes(Consumer<RecipeJsonProvider> consumer) {
    CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(PVJItems.CINDERCANE), RecipeCategory.MISC, Items.BLAZE_POWDER, 0.3F, 800)
      .criterion("has_cindercane", conditionsFromItem(PVJItems.CINDERCANE))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "cindercane_to_blaze_powder"));
    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(PVJItems.SMALL_CACTUS), RecipeCategory.MISC, Items.GREEN_DYE, 0.1F, 200)
      .criterion("has_small_cactus", conditionsFromItem(PVJItems.SMALL_CACTUS))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "small_cactus_to_green_dye"));
    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(PVJItems.GLOWCAP), RecipeCategory.MISC, Items.GLOWSTONE_DUST, 0.1F, 200)
      .criterion("has_glowcap", conditionsFromItem(PVJItems.GLOWCAP))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, "glowcap_to_glowstone_dust"));
  }

  private void simpleShapeless(Consumer<RecipeJsonProvider> consumer, Item output, Item input, int count) {
    String outputPath = Registries.ITEM.getId(output).getPath();
    String inputPath = Registries.ITEM.getId(input).getPath();
    ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, count)
      .input(input)
      .criterion("has_" + inputPath, conditionsFromItem(input))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, outputPath + "_from_" + inputPath));
  }

  private void simpleTwoByTwo(Consumer<RecipeJsonProvider> consumer, Item output, Item input, int count) {
    String outputPath = Registries.ITEM.getId(output).getPath();
    String inputPath = Registries.ITEM.getId(input).getPath();
    ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output, count)
      .pattern("##")
      .pattern("##")
      .input('#', input)
      .criterion("has_" + inputPath, conditionsFromItem(input))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, outputPath + "_from_" + inputPath));
  }

  private void simpleDye(Consumer<RecipeJsonProvider> consumer, Item dye, Item ingredient, int count) {
    String dyePath = Registries.ITEM.getId(dye).getPath();
    String ingredientPath = Registries.ITEM.getId(ingredient).getPath();
    ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, dye, count)
      .input(ingredient)
      .criterion("has_" + ingredientPath, conditionsFromItem(ingredient))
      .offerTo(consumer, new Identifier(ProjectVibrantJourneys.MOD_ID, dyePath + "_from_" + ingredientPath));
  }
}
