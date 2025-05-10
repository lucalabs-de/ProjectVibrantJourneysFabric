package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class PVJBlocks {

    /* OVERWORLD FLORA */
    public static final Block BEACH_GRASS = register("beach_grass",
            () -> new BeachGrassBlock(replaceablePlant(MapColor.LIME)));
    public static final Block SEA_OATS = register("sea_oats",
            () -> new SeaOatsBlock(replaceablePlant(MapColor.PALE_YELLOW)));
    public static final Block CATTAIL = register("cattail",
            () -> new DoubleHighWaterPlantBlock(replaceablePlant(null), true));
    public static final Block BARK_MUSHROOM = register("bark_mushroom",
            () -> new BarkMushroomBlock(barkMushroom()));
    public static final Block LIGHT_BROWN_BARK_MUSHROOM = register("light_brown_bark_mushroom",
            () -> new BarkMushroomBlock(barkMushroom()));
    public static final Block ORANGE_BARK_MUSHROOM = register("orange_bark_mushroom",
            () -> new BarkMushroomBlock(barkMushroom()));
    public static final Block GLOWING_BLUE_FUNGUS = register("glowing_blue_fungus",
            () -> new GlowingFungusBlock(barkMushroom()
                    .luminance(state -> 6)
                    .sounds(BlockSoundGroup.SMALL_DRIPLEAF)
                    .emissiveLighting((state, level, pos) -> true)));
    public static final Block SHORT_GRASS = register("short_grass",
            () -> new ShortGrassBlock(replaceablePlant(null)));
    public static final Block SMALL_CACTUS = register("small_cactus",
            () -> new SmallCactusBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .noCollision()
                    .breakInstantly()
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)));
    public static final Block PRICKLY_BUSH = register("prickly_bush",
            () -> new ThornsBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)
                    .mapColor(MapColor.BROWN)));
    public static final Block REEDS = register("reeds",
            () -> new DoubleHighWaterPlantBlock(replaceablePlant(null), true));
    public static final Block ICICLE = register("icicle",
            () -> new IcicleBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_PURPLE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.GLASS)
                    .ticksRandomly()
                    .strength(1.5F, 3.0F)
                    .dynamicBounds()
                    .offset(AbstractBlock.OffsetType.XZ)));
    public static final Block SANDY_SPROUTS = register("sandy_sprouts",
            () -> new SandySproutsBlock(replaceablePlant(MapColor.PALE_YELLOW)));
    public static final Block WATERGRASS = register("watergrass",
            () -> new DoubleHighWaterPlantBlock(replaceablePlant(null), false));
    public static final Block PINK_LOTUS = register("pink_lotus",
            () -> new LotusBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .noCollision()
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block YELLOW_WILDFLOWERS = register("yellow_wildflowers",
            () -> new FlowerbedBlock(wildflowers()));
    public static final Block ORANGE_WILDFLOWERS = register("orange_wildflowers",
            () -> new FlowerbedBlock(wildflowers()));
    public static final Block BLUE_WILDFLOWERS = register("blue_wildflowers",
            () -> new FlowerbedBlock(wildflowers()));
    public static final Block PURPLE_WILDFLOWERS = register("purple_wildflowers",
            () -> new FlowerbedBlock(wildflowers()));
    public static final Block WHITE_WILDFLOWERS = register("white_wildflowers",
            () -> new FlowerbedBlock(wildflowers()));
    public static final Block MIXED_WILDFLOWERS = register("mixed_wildflowers",
            () -> new FlowerbedBlock(wildflowers()));

    /* NETHER FLORA */
    public static final Block CRIMSON_NETTLE = register("crimson_nettle",
            () -> new NetherPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DULL_RED)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.NETHER_SPROUTS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block WARPED_NETTLE = register("warped_nettle",
            () -> new NetherPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TEAL)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.NETHER_SPROUTS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block CINDERCANE = register("cindercane",
            () -> new CindercaneBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DULL_PINK)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WEEPING_VINES_LOW_PITCH)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GLOWCAP = register("glowcap",
            () -> new GlowcapBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .luminance((state) -> 12)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    /* GROUNDCOVER */
    public static final Block FALLEN_LEAVES = register("fallen_leaves",
            () -> new FallenLeavesBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_GREEN)
                    .noCollision()
                    .strength(0.1F, 0.0F)
                    .burnable().sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block DEAD_FALLEN_LEAVES = register("dead_fallen_leaves",
            () -> new FallenLeavesBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .noCollision()
                    .strength(0.1F, 0.0F)
                    .burnable()
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block TWIGS = register("twigs",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.CROP, true)));
    public static final Block ROCKS = register("rocks",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.STONE, false)));
    public static final Block MOSSY_ROCKS = register("mossy_rocks",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.STONE, false)));
    public static final Block SANDSTONE_GRAY_ROCKS = register("sandstone_rocks",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.STONE, false)));
    public static final Block RED_SANDSTONE_GRAY_ROCKS = register("red_sandstone_rocks",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.STONE, false)));
    public static final Block ICE_CHUNKS = register("ice_chunks",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.GLASS, false)));
    public static final Block BONES = register("bones",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.BONE, false)));
    public static final Block CHARRED_BONES = register("charred_bones",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.BONE, false)));
    public static final Block PINECONES = register("pinecones",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.CROP, true)));
    public static final Block SEASHELLS = register("seashells",
            () -> new GroundcoverBlock(groundcover(BlockSoundGroup.STONE, false)));


    /* MISC */
    public static final Block NATURAL_COBWEB = register("natural_cobweb",
            NaturalCobwebBlock::new);

    public static final Block FERROUS_GRAVEL = register("ferrous_gravel",
            () -> new GravelBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.SNARE).strength(0.6F).sounds(BlockSoundGroup.GRAVEL)));

    public static final Block GILDED_GRAVEL = register("gilded_gravel",
            () -> new SandBlock(
                    -8356741,
                    AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.SNARE).strength(0.6F).sounds(BlockSoundGroup.GRAVEL)
            )
    );

    public static final Block GILDED_RED_SAND = register("gilded_red_sand",
            () -> new SandBlock(
                    -11098145,
                    AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.SNARE).strength(0.6F).sounds(BlockSoundGroup.SAND)
            )
    );

    public static final Block BEACHED_KELP = register("beached_kelp",
            () -> new BeachedKelpBlock(AbstractBlock.Settings.create()
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .offset(AbstractBlock.OffsetType.NONE)));
    public static final Block DRIED_BEACHED_KELP = register("dried_beached_kelp",
            () -> new BeachedKelpBlock(AbstractBlock.Settings.create()
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .mapColor(MapColor.TERRACOTTA_BLACK)
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .offset(AbstractBlock.OffsetType.NONE)));
    public static final Block SLIME_NODULE = register("slime_nodule",
            () -> new SlimeNoduleBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .luminance((state) -> 5)
                    .sounds(BlockSoundGroup.SLIME)
                    .hardness(0.2F)));
    public static final Block PINK_VINES_DARK_GREEN = register("pink_vines_plant",
            () -> new PinkVinesPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block PINK_VINES = register("pink_vines",
            () -> new PinkVinesBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block OAK_HOLLOW_LOG = register("oak_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block BIRCH_HOLLOW_LOG = register("birch_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block SPRUCE_HOLLOW_LOG = register("spruce_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_LOG)));
    public static final Block JUNGLE_HOLLOW_LOG = register("jungle_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_LOG)));
    public static final Block ACACIA_HOLLOW_LOG = register("acacia_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_LOG)));
    public static final Block DARK_OAK_HOLLOW_LOG = register("dark_oak_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_LOG)));
    public static final Block CHERRY_HOLLOW_LOG = register("cherry_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LOG)));
    public static final Block MANGROVE_HOLLOW_LOG = register("mangrove_hollow_log",
            () -> new HollowLogBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_LOG)));

    public static final Block POTTED_GLOWCAP = register("potted_glowcap",
            () -> createFlowerPot(GLOWCAP));
    public static final Block POTTED_CRIMSON_NETTLE = register("potted_crimson_nettle",
            () -> createFlowerPot(CRIMSON_NETTLE));
    public static final Block POTTED_WARPED_NETTLE = register("potted_warped_nettle",
            () -> createFlowerPot(WARPED_NETTLE));
    public static final Block POTTED_CINDERCANE = register("potted_cindercane",
            () -> createFlowerPot(CINDERCANE));
    public static final Block POTTED_SMALL_CACTUS = register("potted_small_cactus",
            () -> createFlowerPot(SMALL_CACTUS));
    public static final Block POTTED_PRICKLY_BUSH = register("potted_prickly_bush",
            () -> createFlowerPot(PRICKLY_BUSH));

    public static final Block MUDDY_BONES = register("muddy_bones",
            () -> new MuddyBonesBlock(
                    AbstractBlock.Settings.copy(Blocks.MUD)
                            .mapColor(MapColor.TERRACOTTA_CYAN)
                            .allowsSpawning(PVJBlocks::always)
                            .solidBlock(PVJBlocks::always)
                            .blockVision(PVJBlocks::always)
                            .suffocates(PVJBlocks::always)
                            .sounds(BlockSoundGroup.MUD)
            )
    );

    private PVJBlocks() {
    }

    private static Boolean always(BlockState state, BlockView level, BlockPos pos, EntityType<?> type) {
        return true;
    }

    private static Boolean always(BlockState state, BlockView level, BlockPos pos) {
        return true;
    }

    private static AbstractBlock.Settings replaceablePlant(@Nullable MapColor mapColorOverride) {
        return AbstractBlock.Settings.create()
                .replaceable()
                .mapColor(mapColorOverride != null ? mapColorOverride : MapColor.DARK_GREEN)
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.GRASS)
                .offset(AbstractBlock.OffsetType.XYZ)
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY);
    }

    private static AbstractBlock.Settings barkMushroom() {
        return AbstractBlock.Settings.create()
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.WOOD)
                .hardness(0.2F);
    }

    private static AbstractBlock.Settings groundcover(BlockSoundGroup soundType, boolean isFlammable) {
        AbstractBlock.Settings groundcover = AbstractBlock.Settings.create()
                .strength(0.05F, 0.0F)
                .nonOpaque()
                .offset(AbstractBlock.OffsetType.XZ)
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(soundType);

        if (isFlammable) {
            groundcover = groundcover.burnable();
        }

        return groundcover;
    }

    private static AbstractBlock.Settings wildflowers() {
        return AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_GREEN)
                .noCollision()
                .sounds(BlockSoundGroup.PINK_PETALS)
                .pistonBehavior(PistonBehavior.DESTROY);
    }

    public static Block createFlowerPot(Block plant) {
        return new FlowerPotBlock(
                plant,
                AbstractBlock.Settings.copy(Blocks.FLOWER_POT).luminance((state) -> plant == GLOWCAP ? 12 : 0));
    }

    private static <T extends Block> T register(final String name, Supplier<T> supplier) {
        Identifier identifier = Identifier.of(ProjectVibrantJourneys.MOD_ID, name);
        return Registry.register(Registries.BLOCK, identifier, supplier.get());
    }

    public static void initialize() {
        ProjectVibrantJourneys.LOGGER.info("Registering blocks");
    }
}