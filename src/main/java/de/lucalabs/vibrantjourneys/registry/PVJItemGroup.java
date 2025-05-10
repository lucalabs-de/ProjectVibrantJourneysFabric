package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashSet;

public class PVJItemGroup {

  public static HashSet<Item> TAB_ITEMS = new HashSet<>();

  public static final ItemGroup PVJ_CREATIVE_MODE_TAB = register(ProjectVibrantJourneys.MOD_ID, () -> FabricItemGroup.builder()
    .displayName(Text.translatable("itemGroup.projectvibrantjourneys"))
    .icon(PVJItems.TWIGS::getDefaultInstance)
    .displayItems((parameters, output) -> {
      TAB_ITEMS.forEach((item) -> {
        output.accept(item);
      });
    }).build());

    private static ItemGroup register(final Identifier name, final ItemGroup.Builder g) {
        return Registry.register(Registries.ITEM_GROUP, name, g.build());
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("Initializing inventory tabs");
    }
}
