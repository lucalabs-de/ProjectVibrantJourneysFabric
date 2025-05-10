package de.lucalabs.vibrantjourneys.data;

import java.util.concurrent.CompletableFuture;

public class PVJItemTags extends ItemTagsProvider {

  public PVJItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> blockProvider, ExistingFileHelper existingFileHelper) {
    super(packOutput, provider, blockProvider, ProjectVibrantJourneys.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    copy(BlockTags.LOGS, ItemTags.LOGS);
    copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
    tag(ItemTags.CREEPER_IGNITERS).add(PVJItems.CINDERCANE);
    tag(PVJTags.HARVESTS_MOSSY_HOLLOW_LOGS).addTag(ItemTags.SHOVELS);
  }
}
