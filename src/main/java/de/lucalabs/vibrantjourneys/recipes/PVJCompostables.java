package de.lucalabs.vibrantjourneys.recipes;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;

public class PVJCompostables {

  public static void inititalize() {
    ProjectVibrantJourneys.LOGGER.info("initializing compostables");

    setCompostInfo(PVJBlocks.SHORT_GRASS, 0.1F);
    setCompostInfo(PVJBlocks.FALLEN_LEAVES, 0.1F);
    setCompostInfo(PVJBlocks.DEAD_FALLEN_LEAVES, 0.1F);
    setCompostInfo(PVJBlocks.TWIGS, 0.1F);
    setCompostInfo(PVJBlocks.PINECONES, 0.1F);
    setCompostInfo(PVJBlocks.SEA_OATS, 0.65F);
    setCompostInfo(PVJBlocks.CATTAIL, 0.65F);
    setCompostInfo(PVJBlocks.BARK_MUSHROOM, 0.4F);
    setCompostInfo(PVJBlocks.LIGHT_BROWN_BARK_MUSHROOM, 0.4F);
    setCompostInfo(PVJBlocks.ORANGE_BARK_MUSHROOM, 0.4F);
    setCompostInfo(PVJBlocks.GLOWING_BLUE_FUNGUS, 0.4F);
    setCompostInfo(PVJBlocks.FALLEN_LEAVES, 0.1F);
    setCompostInfo(PVJBlocks.GLOWCAP, 0.65F);
    setCompostInfo(PVJBlocks.CRIMSON_NETTLE, 0.65F);
    setCompostInfo(PVJBlocks.WARPED_NETTLE, 0.65F);
    setCompostInfo(PVJBlocks.CINDERCANE, 0.8F);
    setCompostInfo(PVJBlocks.BEACH_GRASS, 0.25F);
    setCompostInfo(PVJBlocks.SMALL_CACTUS, 0.25F);
    setCompostInfo(PVJBlocks.REEDS, 0.65F);
    setCompostInfo(PVJBlocks.PRICKLY_BUSH, 0.2F);
    setCompostInfo(PVJBlocks.SANDY_SPROUTS, 0.15F);
    setCompostInfo(PVJBlocks.WATERGRASS, 0.1F);
    setCompostInfo(PVJBlocks.PINK_LOTUS, 0.5F);
    setCompostInfo(PVJBlocks.PINK_VINES, 0.2F);
    setCompostInfo(PVJBlocks.YELLOW_WILDFLOWERS, 0.25F);
    setCompostInfo(PVJBlocks.ORANGE_WILDFLOWERS, 0.25F);
    setCompostInfo(PVJBlocks.BLUE_WILDFLOWERS, 0.25F);
    setCompostInfo(PVJBlocks.PURPLE_WILDFLOWERS, 0.25F);
    setCompostInfo(PVJBlocks.WHITE_WILDFLOWERS, 0.25F);
    setCompostInfo(PVJBlocks.MIXED_WILDFLOWERS, 0.25F);
  }

  public static void setCompostInfo(ItemConvertible item, float chance) {
    ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), chance);
  }
}