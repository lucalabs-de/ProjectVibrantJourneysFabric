package de.lucalabs.vibrantjourneys.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelBlockItem extends BlockItem {

  private final int burnTime;

  public FuelBlockItem(Block block, Item.Settings props, int burnTime) {
    super(block, props);
    this.burnTime = burnTime;
  }

  // TODO this is a Forge thing, find the fabric replacement
//  @Override
//  public int getBurnTime(ItemStack stack, RecipeType type) {
//    return this.burnTime;
//  }
}
