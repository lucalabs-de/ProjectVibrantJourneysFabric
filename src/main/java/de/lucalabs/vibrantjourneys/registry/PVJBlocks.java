package de.lucalabs.vibrantjourneys.registry;

import de.lucalabs.vibrantjourneys.ProjectVibrantJourneys;
import de.lucalabs.vibrantjourneys.blocks.*;
import net.minecraft.block.*;
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
            () -> new BeachGrassBlock(replaceablePlant(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block SEA_OATS = register("sea_oats",
            () -> new SeaOatsBlock(replaceablePlant(MapColor.SAND)));
    public static final Block CATTAIL = register("cattail",
            () -> new DoubleHighWaterPlantBlock(replaceablePlant(null), true));
    public static final Block BARK_MUSHROOM = register("bark_mushroom",
            () -> new BarkMushroomBlock(barkMushroom()));
    public static final Block LIGHT_BROWN_BARK_MUSHROOM = register("light_brown_bark_mushroom",
            () -> new BarkMushroomBlock(barkMushroom()));
    public static final Block ORANGE_BARK_MUSHROOM = register("orange_bark_mushroom",
            () -> new BarkMushroomBlock(barkMushroom()));
    public static final Block GLOWING_BLUE_FUNGUS = register("glowing_blue_fungus",
            () -> new GlowingFungusBlock(barkMushroom().lightLevel((state) -> 6).sound(SoundType.SMALL_DRIPLEAF).emissiveRendering((state, level, pos) -> true)));
    public static final Block SHORT_GRASS = register("short_grass",
            () -> new ShortGrassBlock(replaceablePlant(null)));
    public static final Block SMALL_CACTUS = register("small_cactus",
            () -> new SmallCactusBlock(AbstractBlock.Settings.of().mapColor(MapColor.COLOR_LIGHT_GREEN).noCollission().instabreak().ignitedByLava().pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
    public static final Block PRICKLY_BUSH = register("prickly_bush",
            () -> new ThornsBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).mapColor(MapColor.COLOR_BROWN)));
    public static final Block REEDS = register("reeds",
            () -> new DoubleHighWaterPlantBlock(replaceablePlant(null), true));
    public static final Block ICICLE = register("icicle",
            () -> new IcicleBlock(AbstractBlock.Settings.of().mapColor(MapColor.ICE).noOcclusion().sound(SoundType.GLASS).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(AbstractBlock.OffsetType.XZ)));
    public static final Block SANDY_SPROUTS = register("sandy_sprouts",
            () -> new SandySproutsBlock(replaceablePlant(MapColor.SAND)));
    public static final Block WATERGRASS = register("watergrass",
            () -> new DoubleHighWaterPlantBlock(replaceablePlant(null), false));
    public static final Block PINK_LOTUS = register("pink_lotus",
            () -> new LotusBlock(AbstractBlock.Settings.of().mapColor(MapColor.PLANT).instabreak().sound(SoundType.LILY_PAD).noCollission().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block YELLOW_WILDFLOWERS = register("yellow_wildflowers",
            () -> new PinkPetalsBlock(wildflowers()));
    public static final Block ORANGE_WILDFLOWERS = register("orange_wildflowers",
            () -> new PinkPetalsBlock(wildflowers()));
    public static final Block BLUE_WILDFLOWERS = register("blue_wildflowers",
            () -> new PinkPetalsBlock(wildflowers()));
    public static final Block PURPLE_WILDFLOWERS = register("purple_wildflowers",
            () -> new PinkPetalsBlock(wildflowers()));
    public static final Block WHITE_WILDFLOWERS = register("white_wildflowers",
            () -> new PinkPetalsBlock(wildflowers()));
    public static final Block MIXED_WILDFLOWERS = register("mixed_wildflowers",
            () -> new PinkPetalsBlock(wildflowers()));

    /* NETHER FLORA */
    public static final Block CRIMSON_NETTLE = register("crimson_nettle",
            () -> new NetherPlantBlock(AbstractBlock.Settings.of().mapColor(MapColor.CRIMSON_NYLIUM).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(AbstractBlock.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final Block WARPED_NETTLE = register("warped_nettle",
            () -> new NetherPlantBlock(AbstractBlock.Settings.of().mapColor(MapColor.WARPED_NYLIUM).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(AbstractBlock.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final Block CINDERCANE = register("cindercane",
            () -> new CindercaneBlock(AbstractBlock.Settings.of().mapColor(MapColor.CRIMSON_STEM).noCollission().randomTicks().instabreak().sound(SoundType.TWISTING_VINES).offsetType(AbstractBlock.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final Block GLOWCAP = register("glowcap",
            () -> new GlowcapBlock(AbstractBlock.Settings.of().mapColor(MapColor.COLOR_YELLOW).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).offsetType(AbstractBlock.OffsetType.XZ).lightLevel((state) -> 12).pushReaction(PushReaction.DESTROY)));

    /* GROUNDCOVER */
    public static final Block FALLEN_LEAVES = register("fallen_leaves",
            () -> new FallenLeavesBlock(AbstractBlock.Settings.of().mapColor(MapColor.GRASS).noCollission().strength(0.1F, 0.0F).ignitedByLava().sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block DEAD_FALLEN_LEAVES = register("dead_fallen_leaves",
            () -> new FallenLeavesBlock(AbstractBlock.Settings.of().mapColor(MapColor.COLOR_BROWN).noCollission().strength(0.1F, 0.0F).ignitedByLava().sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block TWIGS = register("twigs",
            () -> new GroundcoverBlock(groundcover(SoundType.CROP, true)));
    public static final Block ROCKS = register("rocks",
            () -> new GroundcoverBlock(groundcover(SoundType.STONE, false)));
    public static final Block MOSSY_ROCKS = register("mossy_rocks",
            () -> new GroundcoverBlock(groundcover(SoundType.STONE, false)));
    public static final Block SANDSTONE_ROCKS = register("sandstone_rocks",
            () -> new GroundcoverBlock(groundcover(SoundType.STONE, false)));
    public static final Block RED_SANDSTONE_ROCKS = register("red_sandstone_rocks",
            () -> new GroundcoverBlock(groundcover(SoundType.STONE, false)));
    public static final Block ICE_CHUNKS = register("ice_chunks",
            () -> new GroundcoverBlock(groundcover(SoundType.GLASS, false)));
    public static final Block BONES = register("bones",
            () -> new GroundcoverBlock(groundcover(SoundType.BONE_BLOCK, false)));
    public static final Block CHARRED_BONES = register("charred_bones",
            () -> new GroundcoverBlock(groundcover(SoundType.BONE_BLOCK, false)));
    public static final Block PINECONES = register("pinecones",
            () -> new GroundcoverBlock(groundcover(SoundType.CROP, true)));
    public static final Block SEASHELLS = register("seashells",
            () -> new GroundcoverBlock(groundcover(SoundType.STONE, false)));


    /* MISC */
    public static final Block NATURAL_COBWEB = register("natural_cobweb",
            NaturalCobwebBlock::new);

    public static final Block FERROUS_GRAVEL = register("ferrous_gravel",
            () -> new GravelBlock(AbstractBlock.Settings.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.6F).sound(SoundType.GRAVEL)));

    public static final Block GILDED_GRAVEL = register("gilded_gravel",
            () -> new SandBlock(
                    -8356741,
                    AbstractBlock.Settings.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.6F).sound(SoundType.GRAVEL)
            )
    );

    public static final Block GILDED_RED_SAND = register("gilded_red_sand",
            () -> new SandBlock(
                    -11098145,
                    AbstractBlock.Settings.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.6F).sound(SoundType.SAND)
            )
    );

    public static final Block BEACHED_KELP = register("beached_kelp",
            () -> new BeachedKelpBlock(AbstractBlock.Settings.of()
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.GRASS)
                    .offsetType(AbstractBlock.OffsetType.XYZ)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.WET_GRASS)
                    .offsetType(AbstractBlock.OffsetType.NONE)));
    public static final Block DRIED_BEACHED_KELP = register("dried_beached_kelp",
            () -> new BeachedKelpBlock(AbstractBlock.Settings.of()
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .mapColor(MapColor.TERRACOTTA_BLACK)
                    .sound(SoundType.GRASS)
                    .offsetType(AbstractBlock.OffsetType.XYZ)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.WET_GRASS)
                    .offsetType(AbstractBlock.OffsetType.NONE)));
    public static final Block SLIME_NODULE = register("slime_nodule",
            () -> new SlimeNoduleBlock(AbstractBlock.Settings.of()
                    .noCollission()
                    .lightLevel((state) -> 5)
                    .sound(SoundType.SLIME_BLOCK)
                    .destroyTime(0.2F)));
    public static final Block PINK_VINES_PLANT = register("pink_vines_plant",
            () -> new PinkVinesPlantBlock(AbstractBlock.Settings.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Block PINK_VINES = register("pink_vines",
            () -> new PinkVinesBlock(AbstractBlock.Settings.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));

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
                            .isValidSpawn(PVJBlocks::always)
                            .isRedstoneConductor(PVJBlocks::always)
                            .isViewBlocking(PVJBlocks::always)
                            .isSuffocating(PVJBlocks::always)
                            .sound(SoundType.MUD)
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
                .mapColor(mapColorOverride != null ? mapColorOverride : MapColor.PLANT)
                .noCollission()
                .instabreak()
                .sound(BlockSoundGroup.GRASS)
                .offsetType(AbstractBlock.OffsetType.XYZ)
                .ignitedByLava()
                .pushReaction(PistonBehavior.DESTROY);
    }

    private static AbstractBlock.Settings barkMushroom() {
        return AbstractBlock.Settings.of()
                .noCollission()
                .instabreak()
                .sound(SoundType.WOOD)
                .destroyTime(0.2F);
    }

    private static AbstractBlock.Settings groundcover(SoundType soundType, boolean isFlammable) {
        AbstractBlock.Settings groundcover = AbstractBlock.Settings.of()
                .strength(0.05F, 0.0F)
                .noOcclusion()
                .offsetType(AbstractBlock.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY)
                .sound(soundType);

        if (isFlammable) {
            groundcover = groundcover.ignitedByLava();
        }

        return groundcover;
    }

    private static AbstractBlock.Settings wildflowers() {
        return AbstractBlock.Settings.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .sound(SoundType.PINK_PETALS)
                .pushReaction(PushReaction.DESTROY);
    }

    public static Block createFlowerPot(Block plant) {
        Block block = new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> plant, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).lightLevel((state) -> plant == GLOWCAP ? 12 : 0));
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ForgeRegistries.getKey(plant), () -> block);
        return block;
    }

    private static <T extends Block> T register(final String name, Supplier<T> supplier) {
        Identifier identifier = Identifier.of(ProjectVibrantJourneys.MOD_ID, name);
        return Registry.register(Registries.BLOCK, identifier, supplier.get());
    }

    public static void initalize() {
        ProjectVibrantJourneys.LOGGER.info("Registering blocks");
    }
}