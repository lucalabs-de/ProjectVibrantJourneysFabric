package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
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

public class PVJItemTagProvider extends VanillaItemTagProvider {

    public PVJItemTagProvider(
            DataOutput packOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> provider,
            CompletableFuture<TagProvider.TagLookup<Block>> blockProvider) {
        super(packOutput, provider, blockProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
//        copy(BlockTags.LOGS, ItemTags.LOGS);
//        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        getOrCreateTagBuilder(ItemTags.LOGS).add(
                PVJItems.OAK_HOLLOW_LOG,
                PVJItems.BIRCH_HOLLOW_LOG,
                PVJItems.SPRUCE_HOLLOW_LOG,
                PVJItems.JUNGLE_HOLLOW_LOG,
                PVJItems.DARK_OAK_HOLLOW_LOG,
                PVJItems.ACACIA_HOLLOW_LOG,
                PVJItems.CHERRY_HOLLOW_LOG,
                PVJItems.MANGROVE_HOLLOW_LOG
        );
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).add(
                PVJItems.OAK_HOLLOW_LOG,
                PVJItems.BIRCH_HOLLOW_LOG,
                PVJItems.SPRUCE_HOLLOW_LOG,
                PVJItems.JUNGLE_HOLLOW_LOG,
                PVJItems.DARK_OAK_HOLLOW_LOG,
                PVJItems.ACACIA_HOLLOW_LOG,
                PVJItems.CHERRY_HOLLOW_LOG,
                PVJItems.MANGROVE_HOLLOW_LOG
        );
        getOrCreateTagBuilder(ItemTags.CREEPER_IGNITERS).add(PVJItems.CINDERCANE);
        getOrCreateTagBuilder(PVJTags.HARVESTS_MOSSY_HOLLOW_LOGS).addOptionalTag(ItemTags.SHOVELS.id());
    }
}
