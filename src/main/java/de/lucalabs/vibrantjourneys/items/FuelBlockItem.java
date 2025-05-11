package de.lucalabs.vibrantjourneys.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class FuelBlockItem extends BlockItem {

    private final int burnTime;

    public FuelBlockItem(Block block, Item.Settings props, int burnTime) {
        super(block, props);
        this.burnTime = burnTime;
    }

    public int getBurnTime() {
        return this.burnTime;
    }
}
