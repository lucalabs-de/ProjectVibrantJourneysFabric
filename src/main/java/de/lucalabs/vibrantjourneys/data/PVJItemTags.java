package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.registry.PVJItems;
import de.lucalabs.vibrantjourneys.tags.PVJTags;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.data.server.tag.vanilla.VanillaItemTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class PVJItemTags extends VanillaItemTagProvider {

    public PVJItemTags(
            DataOutput packOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> provider,
            CompletableFuture<TagProvider.TagLookup<Block>> blockProvider) {
        super(packOutput, provider, blockProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        getOrCreateTagBuilder(ItemTags.CREEPER_IGNITERS).add(PVJItems.CINDERCANE);
        getOrCreateTagBuilder(PVJTags.HARVESTS_MOSSY_HOLLOW_LOGS).addTag(ItemTags.SHOVELS);
    }
}
