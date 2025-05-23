package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.blocks.BarkMushroomBlock;
import de.lucalabs.vibrantjourneys.blocks.FallenLeavesBlock;
import de.lucalabs.vibrantjourneys.blocks.GroundcoverBlock;
import de.lucalabs.vibrantjourneys.blocks.HollowLogBlock;
import de.lucalabs.vibrantjourneys.config.PVJConfig;
import de.lucalabs.vibrantjourneys.util.PVJFeatureVars;
import de.lucalabs.vibrantjourneys.util.TreeFeatureUtils;
import de.lucalabs.vibrantjourneys.util.WorldUtils;
import de.lucalabs.vibrantjourneys.world.features.configurations.FallenTreeConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class FallenTreeFeature extends Feature<FallenTreeConfiguration> {

    public FallenTreeFeature(Codec<FallenTreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<FallenTreeConfiguration> context) {
        StructureWorldAccess level = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random rand = context.getRandom();
        BlockState hollowLog = context.getConfig().hollowLog();
        BlockState baseLog = context.getConfig().baseLog();

        String biome = level.getBiome(pos).getKey().get().getValue().toString();
        HashSet<TreeFeatureUtils.ChanceBiomeEntry> biomeEntries = getEntrySet(baseLog.getBlock());
        int chance = TreeFeatureUtils.getChance(biome, biomeEntries);
        if (chance == -1) {
            chance = 10; //set default
        }

        if (rand.nextFloat() > chance / 100.0F)
            return false;

        BlockState down = level.getBlockState(pos.down());
        if (down.isIn(BlockTags.ICE) || down.getBlock() == Blocks.SNOW_BLOCK || down.getFluidState().isStill())
            return false;

        int length = rand.nextInt(3) + 4;
        Direction dir = Direction.Type.HORIZONTAL.random(rand);
        Direction dirCounterClockwise = dir.rotateYCounterclockwise();
        Direction dirClockwise = dir.rotateYClockwise();
        boolean branched = false;

        for (int i = 0; i < length; i++) {
            if (!canReplace(level, pos) || canReplace(level, pos.down())) { // TODO check that the second condition is alright
                return false;
            }
            pos = pos.offset(dir);
        }

        pos = context.getOrigin();
        List<FallenTreeConfiguration.FallenTreeVegetation> vegetationProviders = context.getConfig().vegetationProviders();
        Random randomSource = level.getRandom();

        for (int i = 0; i < length; i++) {
            if (canReplace(level, pos)) {
                if (!(down.isReplaceable() || down.getFluidState().isOf(Fluids.WATER)) || i > (length / 2)) {
                    boolean mossy = context.getConfig().canBeMossy() && randomSource.nextBoolean();
                    WorldUtils.setBlockState(level, pos, hollowLog.with(PillarBlock.AXIS, dir.getAxis()).with(HollowLogBlock.MOSSY, mossy), 2);

                    if (level.isAir(pos.up()) && rand.nextFloat() < 0.75F) {
                        WorldUtils.setBlockState(level, pos.up(), this.getVegetationToPlace(vegetationProviders, randomSource, pos.up()), 2);
                    }

                    if (!branched && i <= (length / 2) + 1 && rand.nextFloat() < 0.5F) {
                        BlockPos branchPos = rand.nextBoolean() ? pos.offset(dirCounterClockwise) : pos.offset(dirClockwise);

                        WorldUtils.setBlockState(level, branchPos, baseLog.with(PillarBlock.AXIS, dirCounterClockwise.getAxis()), 2);
                        if (level.isAir(branchPos.up()) && rand.nextFloat() < 0.4F) {
                            WorldUtils.setBlockState(level, branchPos.up(), this.getVegetationToPlace(vegetationProviders, randomSource, branchPos.up()), 2);
                        }
                        branched = true;
                    }

                    BlockPos original = pos;

                    pos = pos.offset(dirCounterClockwise);
                    if (canReplace(level, pos)) {
                        if (rand.nextFloat() < 0.4F && Block.isFaceFullSquare(level.getBlockState(pos.down()).getCollisionShape(level, pos.down()), Direction.UP)) {
                            BlockState state = this.getVegetationToPlace(vegetationProviders, randomSource, pos);
                            if (state.canPlaceAt(level, pos)) {
                                WorldUtils.setBlockState(level, pos, state, 2);
                            }
                        } else if (rand.nextFloat() < 0.4F && PVJConfig.enableBarkMushrooms) {
                            BarkMushroomBlock mushroom = BarkMushroomBlock.getRandom(rand);
                            WorldUtils.setBlockState(level, pos, mushroom.getDefaultState().with(BarkMushroomBlock.FACING, dirCounterClockwise), 2);
                        }
                    }

                    pos = original;
                    pos = pos.offset(dirClockwise);
                    if (canReplace(level, pos)) {
                        if (rand.nextFloat() < 0.4F && Block.isFaceFullSquare(level.getBlockState(pos.down()).getCollisionShape(level, pos.down()), Direction.UP)) {
                            BlockState state = this.getVegetationToPlace(vegetationProviders, randomSource, pos);
                            if (state.canPlaceAt(level, pos)) {
                                WorldUtils.setBlockState(level, pos, state, 2);
                            }
                        } else if (rand.nextFloat() < 0.4F && PVJConfig.enableBarkMushrooms) {
                            BarkMushroomBlock mushroom = BarkMushroomBlock.getRandom(rand);
                            WorldUtils.setBlockState(level, pos, mushroom.getDefaultState().with(BarkMushroomBlock.FACING, dirClockwise), 2);
                        }
                    }

                    pos = original;
                    pos = pos.offset(dir);
                } else {
                    dir = dir.getOpposite();
                    pos = context.getOrigin().offset(dir);
                }
                down = level.getBlockState(pos.down());
            } else {
                return length - i < length;
            }
        }

        return true;
    }

    public boolean canReplace(StructureWorldAccess world, BlockPos pos) {
        return world.getBlockState(pos).isReplaceable()
                || world.isAir(pos)
                || world.getBlockState(pos).getBlock() instanceof FallenLeavesBlock
                || world.getBlockState(pos).getBlock() instanceof GroundcoverBlock;
    }

    public HashSet<TreeFeatureUtils.ChanceBiomeEntry> getEntrySet(Block log) {
        if (log == Blocks.OAK_LOG) {
            return PVJFeatureVars.OAK;
        } else if (log == Blocks.BIRCH_LOG) {
            return PVJFeatureVars.BIRCH;
        } else if (log == Blocks.SPRUCE_LOG) {
            return PVJFeatureVars.SPRUCE;
        } else if (log == Blocks.JUNGLE_LOG) {
            return PVJFeatureVars.JUNGLE;
        } else if (log == Blocks.ACACIA_LOG) {
            return PVJFeatureVars.ACACIA;
        } else if (log == Blocks.DARK_OAK_LOG) {
            return PVJFeatureVars.DARK_OAK;
        } else if (log == Blocks.MANGROVE_LOG) {
            return PVJFeatureVars.MANGROVE;
        } else if (log == Blocks.CHERRY_LOG) {
            return PVJFeatureVars.CHERRY;
        }

        return PVJFeatureVars.OAK;
    }

    private BlockState getVegetationToPlace(List<FallenTreeConfiguration.FallenTreeVegetation> vegetationProviders, Random randomSource, BlockPos pos) {
        int numProviders = vegetationProviders.size();
        FallenTreeConfiguration.FallenTreeVegetation vegetation = null;
        while (vegetation == null) {
            int random = randomSource.nextInt(numProviders);
            FallenTreeConfiguration.FallenTreeVegetation temp = vegetationProviders.get(random);
            if (temp.configOption().isPresent()) {
                Optional<String> configOption = temp.configOption();
                if (!PVJConfig.configOptions.get(configOption.get())) {
                    continue;
                }
            }
            vegetation = temp;
        }

        return vegetation.provider().get(randomSource, pos);
    }
}