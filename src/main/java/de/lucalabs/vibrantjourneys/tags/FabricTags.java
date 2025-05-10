package de.lucalabs.vibrantjourneys.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FabricTags {

  public static final TagKey<Block> SAND = createBlockTag("sands");

  public static final TagKey<Item> BONES = createItemTag("bones");
  public static final TagKey<Item> MUSHROOMS = createItemTag("mushrooms");
  public static final TagKey<Item> RODS = createItemTag("rods");
  public static final TagKey<Item> WOODEN_RODS = createItemTag("rods/wooden");

  private static TagKey<Block> createBlockTag(String location) {
    return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", location));
  }

  private static TagKey<Item> createItemTag(String location) {
    return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", location));
  }
}
