package de.lucalabs.vibrantjourneys.data;

import de.lucalabs.vibrantjourneys.blocks.HollowLogBlock;
import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.util.math.BlockPos;

public class PVJBlockLootProvider extends FabricBlockLootTableProvider {

    private static final LootCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = WITH_SHEARS.or(WITH_SILK_TOUCH);

    protected PVJBlockLootProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(PVJBlocks.BEACH_GRASS);
        addDrop(PVJBlocks.BARK_MUSHROOM);
        addDrop(PVJBlocks.ORANGE_BARK_MUSHROOM);
        addDrop(PVJBlocks.LIGHT_BROWN_BARK_MUSHROOM);
        addDrop(PVJBlocks.GLOWING_BLUE_FUNGUS);
        addDrop(PVJBlocks.SMALL_CACTUS);
        addDrop(PVJBlocks.REEDS);
        addDrop(PVJBlocks.ICICLE);
        addDrop(PVJBlocks.CATTAIL);
        addDrop(PVJBlocks.SEA_OATS);
        addDrop(PVJBlocks.GLOWCAP);
        addDrop(PVJBlocks.WARPED_NETTLE);
        addDrop(PVJBlocks.CRIMSON_NETTLE);
        addDrop(PVJBlocks.CINDERCANE);

        hollowLog(PVJBlocks.OAK_HOLLOW_LOG);
        hollowLog(PVJBlocks.BIRCH_HOLLOW_LOG);
        hollowLog(PVJBlocks.SPRUCE_HOLLOW_LOG);
        hollowLog(PVJBlocks.JUNGLE_HOLLOW_LOG);
        hollowLog(PVJBlocks.ACACIA_HOLLOW_LOG);
        hollowLog(PVJBlocks.DARK_OAK_HOLLOW_LOG);
        hollowLog(PVJBlocks.CHERRY_HOLLOW_LOG);
        hollowLog(PVJBlocks.MANGROVE_HOLLOW_LOG);

        addPottedPlantDrops(PVJBlocks.POTTED_GLOWCAP);
        addPottedPlantDrops(PVJBlocks.POTTED_CRIMSON_NETTLE);
        addPottedPlantDrops(PVJBlocks.POTTED_WARPED_NETTLE);
        addPottedPlantDrops(PVJBlocks.POTTED_CINDERCANE);
        addPottedPlantDrops(PVJBlocks.POTTED_SMALL_CACTUS);
        addPottedPlantDrops(PVJBlocks.POTTED_PRICKLY_BUSH);

        doublePlant(PVJBlocks.CATTAIL);
        doublePlant(PVJBlocks.SEA_OATS);
        doublePlant(PVJBlocks.REEDS);

        addDropWithSilkTouch(PVJBlocks.ICICLE);
        addDropWithSilkTouch(PVJBlocks.ROCKS);
        addDropWithSilkTouch(PVJBlocks.MOSSY_ROCKS);
        addDropWithSilkTouch(PVJBlocks.SANDSTONE_ROCKS);
        addDropWithSilkTouch(PVJBlocks.RED_SANDSTONE_ROCKS);
        addDropWithSilkTouch(PVJBlocks.ICE_CHUNKS);
        addDropWithSilkTouch(PVJBlocks.PINECONES);

        addDrop(PVJBlocks.BEACHED_KELP, Items.KELP);
        addDrop(PVJBlocks.DRIED_BEACHED_KELP, Items.DRIED_KELP);

        addDrop(PVJBlocks.BONES, (block) -> dropsWithSilkTouch(block, this.applyExplosionDecay(block, ItemEntry.builder(Items.BONE))));
        addDrop(PVJBlocks.CHARRED_BONES, (block) -> dropsWithSilkTouch(block, this.applyExplosionDecay(block, ItemEntry.builder(Items.BONE))));
        addDrop(PVJBlocks.TWIGS, (block) -> dropsWithSilkTouch(block, this.applyExplosionDecay(block, ItemEntry.builder(Items.STICK))));

        addDrop(PVJBlocks.PINK_VINES, BlockLootTableGenerator::dropsWithShears);
        addDrop(PVJBlocks.PINK_VINES_PLANT, block -> LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(WITH_SHEARS)
                        .with(ItemEntry.builder(PVJBlocks.PINK_VINES))));

        addDrop(PVJBlocks.SLIME_NODULE, block -> this.drops(block, Items.SLIME_BALL));

        addDrop(PVJBlocks.YELLOW_WILDFLOWERS, this.flowerbedDrops(PVJBlocks.YELLOW_WILDFLOWERS));
        addDrop(PVJBlocks.ORANGE_WILDFLOWERS, this.flowerbedDrops(PVJBlocks.ORANGE_WILDFLOWERS));
        addDrop(PVJBlocks.BLUE_WILDFLOWERS, this.flowerbedDrops(PVJBlocks.BLUE_WILDFLOWERS));
        addDrop(PVJBlocks.PURPLE_WILDFLOWERS, this.flowerbedDrops(PVJBlocks.PURPLE_WILDFLOWERS));
        addDrop(PVJBlocks.WHITE_WILDFLOWERS, this.flowerbedDrops(PVJBlocks.WHITE_WILDFLOWERS));
        addDrop(PVJBlocks.MIXED_WILDFLOWERS, this.flowerbedDrops(PVJBlocks.MIXED_WILDFLOWERS));

        shearsOrSilkTouch(PVJBlocks.FALLEN_LEAVES);
        shearsOrSilkTouch(PVJBlocks.DEAD_FALLEN_LEAVES);
        shearsOrSilkTouch(PVJBlocks.SANDY_SPROUTS);

        addDrop(PVJBlocks.NATURAL_COBWEB, (block) -> dropsWithSilkTouch(Blocks.COBWEB, this.addSurvivesExplosionCondition(Blocks.COBWEB, ItemEntry.builder(Items.STRING))));
        addDrop(PVJBlocks.PRICKLY_BUSH, (block) -> dropsWithShears(
                block,
                this.applyExplosionDecay(block, ItemEntry.builder(Items.STICK)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F))))));
        addDrop(PVJBlocks.SHORT_GRASS, this::grassDrops);
        addDrop(PVJBlocks.WATERGRASS, block -> watergrass(block, block));

        addDrop(PVJBlocks.SEASHELLS, (block) -> LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(applyExplosionDecay(block,
                                        ItemEntry.builder(Items.PRISMARINE_SHARD)
                                                .conditionally(RandomChanceLootCondition.builder(0.125F))))));

        addDrop(
                PVJBlocks.FERROUS_GRAVEL,
                block -> dropsWithSilkTouch(block,
                        this.addSurvivesExplosionCondition(block, ItemEntry.builder(Items.FLINT)
                                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                                .alternatively(ItemEntry.builder(Blocks.GRAVEL)))
                ).pool(
                        LootPool.builder()
                                .conditionally(WITHOUT_SILK_TOUCH)
                                .with(
                                        this.addSurvivesExplosionCondition(block,
                                                ItemEntry.builder(Items.RAW_IRON).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))))
                )
        );
        addDrop(
                PVJBlocks.GILDED_GRAVEL,
                block -> dropsWithSilkTouch(block,
                        this.addSurvivesExplosionCondition(block, ItemEntry.builder(Items.FLINT)
                                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                                .alternatively(ItemEntry.builder(Blocks.GRAVEL)))
                ).pool(
                        LootPool.builder()
                                .conditionally(WITHOUT_SILK_TOUCH)
                                .with(
                                        this.addSurvivesExplosionCondition(block,
                                                ItemEntry.builder(Items.RAW_GOLD).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))))
                )
        );
        addDrop(
                PVJBlocks.GILDED_RED_SAND,
                block -> dropsWithSilkTouch(block,
                        this.addSurvivesExplosionCondition(block, ItemEntry.builder(Items.RED_SAND))
                ).pool(
                        LootPool.builder()
                                .conditionally(WITHOUT_SILK_TOUCH)
                                .with(
                                        this.addSurvivesExplosionCondition(block,
                                                ItemEntry.builder(Items.RAW_GOLD).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))))
                )
        );
        addDrop(
                PVJBlocks.MUDDY_BONES,
                block -> dropsWithSilkTouch(block,
                        this.addSurvivesExplosionCondition(block, ItemEntry.builder(Blocks.MUD))
                ).pool(
                        LootPool.builder()
                                .conditionally(WITHOUT_SILK_TOUCH)
                                .with(
                                        this.addSurvivesExplosionCondition(block,
                                                ItemEntry.builder(Items.BONE)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))))
                )
        );
    }

    private void doublePlant(Block plant) {
        addDrop(plant, (block) -> dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
    }

    private void shearsOrSilkTouch(Block block) {
        addDrop(block, (b) -> LootTable.builder().pool(LootPool.builder()
                .conditionally(HAS_SHEARS_OR_SILK_TOUCH)
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .with(ItemEntry.builder(b))));
    }

    private void hollowLog(Block hollowLog) {
        addDrop(hollowLog, (block) -> LootTable.builder()
                .pool(
                        this.addSurvivesExplosionCondition(
                                block,
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(block))))
                .pool(
                        this.addSurvivesExplosionCondition(
                                block,
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Items.MOSS_CARPET))
                                        .conditionally(BlockStatePropertyLootCondition
                                                .builder(block)
                                                .properties(StatePredicate.Builder.create()
                                                        .exactMatch(HollowLogBlock.MOSSY, true)))
                        )
                ));
    }

    protected LootTable.Builder watergrass(Block p_248590_, Block p_248735_) {
        LootPoolEntry.Builder<?> builder = ItemEntry.builder(p_248735_)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                .conditionally(WITH_SHEARS)
                .alternatively(this.addSurvivesExplosionCondition(p_248590_, ItemEntry.builder(Items.WHEAT_SEEDS))
                        .conditionally(RandomChanceLootCondition.builder(0.125F)));

        return LootTable.builder()
                .pool(LootPool.builder()
                        .with(builder)
                        .conditionally(BlockStatePropertyLootCondition.builder(p_248590_)
                                .properties(StatePredicate.Builder.create()
                                        .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)))
                        .conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create()
                                        .block(BlockPredicate.Builder.create()
                                                .blocks(p_248590_)
                                                .state(StatePredicate.Builder.create()
                                                        .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER)
                                                        .build())
                                                .build()),
                                new BlockPos(0, 1, 0))))
                .pool(LootPool.builder()
                        .with(builder)
                        .conditionally(BlockStatePropertyLootCondition.builder(p_248590_)
                                .properties(StatePredicate.Builder.create()
                                        .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER)))
                        .conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder
                                        .create()
                                        .block(BlockPredicate.Builder
                                                .create()
                                                .blocks(p_248590_)
                                                .state(StatePredicate.Builder
                                                        .create()
                                                        .exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)
                                                        .build())
                                                .build()),
                                new BlockPos(0, -1, 0))));
    }
}
