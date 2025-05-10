package de.lucalabs.vibrantjourneys.data;

import java.util.HashSet;
import java.util.Set;

public class PVJBlockLootProvider extends BlockLootSubProvider {

  private HashSet<Block> knownBlocks = new HashSet<>();

  protected PVJBlockLootProvider() {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags());
  }

  private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);

  @Override
  protected void generate() {
    dropSelf(PVJBlocks.BEACH_GRASS);
    dropSelf(PVJBlocks.BARK_MUSHROOM);
    dropSelf(PVJBlocks.ORANGE_BARK_MUSHROOM);
    dropSelf(PVJBlocks.LIGHT_BROWN_BARK_MUSHROOM);
    dropSelf(PVJBlocks.GLOWING_BLUE_FUNGUS);
    dropSelf(PVJBlocks.SMALL_CACTUS);
    dropSelf(PVJBlocks.REEDS);
    dropSelf(PVJBlocks.ICICLE);
    dropSelf(PVJBlocks.CATTAIL);
    dropSelf(PVJBlocks.SEA_OATS);
    dropSelf(PVJBlocks.GLOWCAP);
    dropSelf(PVJBlocks.WARPED_NETTLE);
    dropSelf(PVJBlocks.CRIMSON_NETTLE);
    dropSelf(PVJBlocks.CINDERCANE);

    hollowLog(PVJBlocks.OAK_HOLLOW_LOG);
    hollowLog(PVJBlocks.BIRCH_HOLLOW_LOG);
    hollowLog(PVJBlocks.SPRUCE_HOLLOW_LOG);
    hollowLog(PVJBlocks.JUNGLE_HOLLOW_LOG);
    hollowLog(PVJBlocks.ACACIA_HOLLOW_LOG);
    hollowLog(PVJBlocks.DARK_OAK_HOLLOW_LOG);
    hollowLog(PVJBlocks.CHERRY_HOLLOW_LOG);
    hollowLog(PVJBlocks.MANGROVE_HOLLOW_LOG);

    dropPottedContents(PVJBlocks.POTTED_GLOWCAP);
    dropPottedContents(PVJBlocks.POTTED_CRIMSON_NETTLE);
    dropPottedContents(PVJBlocks.POTTED_WARPED_NETTLE);
    dropPottedContents(PVJBlocks.POTTED_CINDERCANE);
    dropPottedContents(PVJBlocks.POTTED_SMALL_CACTUS);
    dropPottedContents(PVJBlocks.POTTED_PRICKLY_BUSH);

    doublePlant(PVJBlocks.CATTAIL);
    doublePlant(PVJBlocks.SEA_OATS);
    doublePlant(PVJBlocks.REEDS);

    dropWhenSilkTouch(PVJBlocks.ICICLE);
    dropWhenSilkTouch(PVJBlocks.ROCKS);
    dropWhenSilkTouch(PVJBlocks.MOSSY_ROCKS);
    dropWhenSilkTouch(PVJBlocks.SANDSTONE_ROCKS);
    dropWhenSilkTouch(PVJBlocks.RED_SANDSTONE_ROCKS);
    dropWhenSilkTouch(PVJBlocks.ICE_CHUNKS);
    dropWhenSilkTouch(PVJBlocks.PINECONES);

    dropOther(PVJBlocks.BEACHED_KELP, Items.KELP);
    dropOther(PVJBlocks.DRIED_BEACHED_KELP, Items.DRIED_KELP);

    add(PVJBlocks.BONES, (block) -> createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.BONE))));
    add(PVJBlocks.CHARRED_BONES, (block) -> createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.BONE))));
    add(PVJBlocks.TWIGS, (block) -> createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK))));

    add(PVJBlocks.PINK_VINES, BlockLootSubProvider::createShearsOnlyDrop);
    add(PVJBlocks.PINK_VINES_PLANT, block -> LootTable.lootTable()
      .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(PVJBlocks.PINK_VINES))));

    add(PVJBlocks.SLIME_NODULE, block -> this.createSingleItemTableWithSilkTouch(block, Items.SLIME_BALL));

    add(PVJBlocks.YELLOW_WILDFLOWERS, this.createPetalsDrops(PVJBlocks.YELLOW_WILDFLOWERS));
    add(PVJBlocks.ORANGE_WILDFLOWERS, this.createPetalsDrops(PVJBlocks.ORANGE_WILDFLOWERS));
    add(PVJBlocks.BLUE_WILDFLOWERS, this.createPetalsDrops(PVJBlocks.BLUE_WILDFLOWERS));
    add(PVJBlocks.PURPLE_WILDFLOWERS, this.createPetalsDrops(PVJBlocks.PURPLE_WILDFLOWERS));
    add(PVJBlocks.WHITE_WILDFLOWERS, this.createPetalsDrops(PVJBlocks.WHITE_WILDFLOWERS));
    add(PVJBlocks.MIXED_WILDFLOWERS, this.createPetalsDrops(PVJBlocks.MIXED_WILDFLOWERS));

    shearsOrSilkTouch(PVJBlocks.FALLEN_LEAVES);
    shearsOrSilkTouch(PVJBlocks.DEAD_FALLEN_LEAVES);
    shearsOrSilkTouch(PVJBlocks.SANDY_SPROUTS);

    add(PVJBlocks.NATURAL_COBWEB, (block) -> createSilkTouchOrShearsDispatchTable(Blocks.COBWEB, this.applyExplosionCondition(Blocks.COBWEB, LootItem.lootTableItem(Items.STRING))));
    add(PVJBlocks.PRICKLY_BUSH, (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))));
    add(PVJBlocks.SHORT_GRASS, this::createGrassDrops);
    add(PVJBlocks.WATERGRASS, block -> watergrass(block, block));

    add(PVJBlocks.SEASHELLS, (block) -> LootTable.lootTable()
      .withPool(
        LootPool.lootPool()
          .setRolls(ConstantValue.exactly(1.0F))
          .add(applyExplosionDecay(block,
            LootItem.lootTableItem(Items.PRISMARINE_SHARD)
              .when(LootItemRandomChanceCondition.randomChance(0.125F))))));

    add(
      PVJBlocks.FERROUS_GRAVEL,
      block -> createSilkTouchDispatchTable(block,
        this.applyExplosionCondition(block, LootItem.lootTableItem(Items.FLINT)
          .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
          .otherwise(LootItem.lootTableItem(Blocks.GRAVEL)))
      ).withPool(
        LootPool.lootPool()
          .when(HAS_NO_SILK_TOUCH)
          .add(
            this.applyExplosionCondition(block,
              LootItem.lootTableItem(Items.RAW_IRON).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))
      )
    );
    add(
      PVJBlocks.GILDED_GRAVEL,
      block -> createSilkTouchDispatchTable(block,
        this.applyExplosionCondition(block, LootItem.lootTableItem(Items.FLINT)
          .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
          .otherwise(LootItem.lootTableItem(Blocks.GRAVEL)))
      ).withPool(
        LootPool.lootPool()
          .when(HAS_NO_SILK_TOUCH)
          .add(
            this.applyExplosionCondition(block,
              LootItem.lootTableItem(Items.RAW_GOLD).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))
      )
    );
    add(
      PVJBlocks.GILDED_RED_SAND,
      block -> createSilkTouchDispatchTable(block,
        this.applyExplosionCondition(block, LootItem.lootTableItem(Items.RED_SAND))
      ).withPool(
        LootPool.lootPool()
          .when(HAS_NO_SILK_TOUCH)
          .add(
            this.applyExplosionCondition(block,
              LootItem.lootTableItem(Items.RAW_GOLD).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))
      )
    );
    add(
      PVJBlocks.MUDDY_BONES,
      block -> this.createSilkTouchDispatchTable(block,
        this.applyExplosionCondition(block, LootItem.lootTableItem(Blocks.MUD))
      ).withPool(
        LootPool.lootPool()
          .when(HAS_NO_SILK_TOUCH)
          .add(
            this.applyExplosionCondition(block,
              LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
      )
    );
  }

  private void doublePlant(Block plant) {
    add(plant, (block) -> {
      return createSinglePropConditionTable(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER);
    });
  }

  private void shearsOrSilkTouch(Block block) {
    add(block, (b) -> {
      return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SHEARS_OR_SILK_TOUCH).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(b)));
    });
  }

  private void hollowLog(Block hollowLog) {
    add(hollowLog, (block) -> {
      return LootTable.lootTable()
        .withPool(
          this.applyExplosionCondition(
            block,
            LootPool.lootPool()
              .setRolls(ConstantValue.exactly(1.0F))
              .add(LootItem.lootTableItem(block))))
        .withPool(
          this.applyExplosionCondition(
            block,
            LootPool.lootPool()
              .setRolls(ConstantValue.exactly(1.0F))
              .add(LootItem.lootTableItem(Items.MOSS_CARPET))
              .when(LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                  .hasProperty(HollowLogBlock.MOSSY, true)))
          )
        );
    });
  }

  protected LootTable.Builder watergrass(Block p_248590_, Block p_248735_) {
    LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(p_248735_).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(p_248590_, LootItem.lootTableItem(Items.WHEAT_SEEDS)).when(LootItemRandomChanceCondition.randomChance(0.125F)));
    return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_248590_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallPlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlockState(BlockPredicate.Builder.block().of(p_248590_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallPlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_248590_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallPlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlockState(BlockPredicate.Builder.block().of(p_248590_).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallPlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
  }

  @Override
  protected void add(Block block, LootTable.Builder builder) {
    super.add(block, builder);
    knownBlocks.add(block);
  }

  @Override
  protected HashSet<Block> getKnownBlocks() {
    return knownBlocks;
  }
}
