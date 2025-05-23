package de.lucalabs.vibrantjourneys.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OceanConfiguredFeatures;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

public class HotSpringsFeature extends Feature<DefaultFeatureConfig> {
    private static final BlockState AIR = Blocks.CAVE_AIR.getDefaultState();

    public HotSpringsFeature(Codec<DefaultFeatureConfig> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> pContext) {
        BlockPos blockpos = pContext.getOrigin();
        StructureWorldAccess worldgenlevel = pContext.getWorld();
        Random random = pContext.getRandom();
        ChunkGenerator generator = pContext.getGenerator();
        if (blockpos.getY() <= worldgenlevel.getBottomY() + 4) {
            return false;
        } else {
            blockpos = blockpos.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = random.nextInt(4) + 4;

            for (int j = 0; j < i; j++) {
                double d0 = random.nextDouble() * 6.0 + 3.0;
                double d1 = random.nextDouble() * 4.0 + 2.0;
                double d2 = random.nextDouble() * 6.0 + 3.0;
                double d3 = random.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
                double d4 = random.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
                double d5 = random.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;

                for (int l = 1; l < 15; l++) {
                    for (int i1 = 1; i1 < 15; i1++) {
                        for (int j1 = 1; j1 < 7; j1++) {
                            double d6 = ((double) l - d3) / (d0 / 2.0);
                            double d7 = ((double) j1 - d4) / (d1 / 2.0);
                            double d8 = ((double) i1 - d5) / (d2 / 2.0);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                            if (d9 < 1.0) {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            BlockState blockstate1 = Blocks.WATER.getDefaultState();

            for (int k1 = 0; k1 < 16; k1++) {
                for (int k = 0; k < 16; k++) {
                    for (int l2 = 0; l2 < 8; l2++) {
                        boolean flag = !aboolean[(k1 * 16 + k) * 8 + l2]
                                && (
                                k1 < 15 && aboolean[((k1 + 1) * 16 + k) * 8 + l2]
                                        || k1 > 0 && aboolean[((k1 - 1) * 16 + k) * 8 + l2]
                                        || k < 15 && aboolean[(k1 * 16 + k + 1) * 8 + l2]
                                        || k > 0 && aboolean[(k1 * 16 + (k - 1)) * 8 + l2]
                                        || l2 < 7 && aboolean[(k1 * 16 + k) * 8 + l2 + 1]
                                        || l2 > 0 && aboolean[(k1 * 16 + k) * 8 + (l2 - 1)]
                        );
                        if (flag) {
                            BlockState blockstate3 = worldgenlevel.getBlockState(blockpos.add(k1, l2, k));
                            if (l2 >= 4 && blockstate3.isLiquid()) {
                                return false;
                            }

                            if (l2 < 4 && !blockstate3.isSolid() && worldgenlevel.getBlockState(blockpos.add(k1, l2, k)) != blockstate1) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (int l1 = 0; l1 < 16; l1++) {
                for (int i2 = 0; i2 < 16; i2++) {
                    for (int i3 = 0; i3 < 8; i3++) {
                        if (aboolean[(l1 * 16 + i2) * 8 + i3]) {
                            BlockPos blockpos1 = blockpos.add(l1, i3, i2);
                            if (this.canReplaceBlock(worldgenlevel.getBlockState(blockpos1))) {
                                boolean flag1 = i3 >= 4;
                                worldgenlevel.setBlockState(blockpos1, flag1 ? AIR : blockstate1, 2);
                                if (flag1) {
                                    worldgenlevel.scheduleBlockTick(blockpos1, AIR.getBlock(), 0);
                                    this.markBlocksAboveForPostProcessing(worldgenlevel, blockpos1);
                                }
                            }
                        }
                    }
                }
            }

            for (int j2 = 0; j2 < 16; j2++) {
                for (int j3 = 0; j3 < 16; j3++) {
                    for (int l3 = 0; l3 < 8; l3++) {
                        boolean flag2 = !aboolean[(j2 * 16 + j3) * 8 + l3]
                                && (
                                j2 < 15 && aboolean[((j2 + 1) * 16 + j3) * 8 + l3]
                                        || j2 > 0 && aboolean[((j2 - 1) * 16 + j3) * 8 + l3]
                                        || j3 < 15 && aboolean[(j2 * 16 + j3 + 1) * 8 + l3]
                                        || j3 > 0 && aboolean[(j2 * 16 + (j3 - 1)) * 8 + l3]
                                        || l3 < 7 && aboolean[(j2 * 16 + j3) * 8 + l3 + 1]
                                        || l3 > 0 && aboolean[(j2 * 16 + j3) * 8 + (l3 - 1)]
                        );
                        if (flag2 && (l3 < 4 || random.nextInt(2) != 0)) {
                            BlockState blockstate = worldgenlevel.getBlockState(blockpos.add(j2, l3, j3));
                            if (blockstate.isSolid() && !blockstate.isIn(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                BlockPos blockpos3 = blockpos.add(j2, l3, j3);
                                if (!worldgenlevel.getFluidState(blockpos3.up()).isIn(FluidTags.WATER)) {
                                    if (worldgenlevel.isAir(blockpos3.up()) || worldgenlevel.getBlockState(blockpos3.up()).isReplaceable()) {
                                        BlockState perimeterBlock = this.getRandomPerimeterBlock(random);
                                        worldgenlevel.setBlockState(blockpos3, perimeterBlock, 2);
                                        boolean flag4 = random.nextFloat() < 0.45F;
                                        if (flag4) {
                                            worldgenlevel.setBlockState(blockpos3.up(), getAmethyst(random), 2);
                                        }
                                    }
                                } else {
                                    BlockState innerBlock = this.getRandomInnerBlock(random);
                                    worldgenlevel.setBlockState(blockpos3, innerBlock, 2);
                                    boolean flag5 = random.nextFloat() < 0.3F;
                                    if (flag5) {
                                        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> watergrassFeature = worldgenlevel
                                                .getRegistryManager()
                                                .get(RegistryKeys.CONFIGURED_FEATURE)
                                                .getEntry(OceanConfiguredFeatures.SEAGRASS_SIMPLE);
                                        watergrassFeature.ifPresent((feature) -> feature.value().generate(worldgenlevel, generator, random, blockpos3.up()));
                                    }
                                }

                                this.markBlocksAboveForPostProcessing(worldgenlevel, blockpos3);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    private BlockState getRandomPerimeterBlock(Random randomSource) {
        float random = randomSource.nextFloat();
        if (random < 0.3F) {
            return Blocks.STONE.getDefaultState();
        } else if (random < 0.5F) {
            return Blocks.COBBLESTONE.getDefaultState();
        } else if (random < 0.7F) {
            return Blocks.MOSSY_COBBLESTONE.getDefaultState();
        }

        return Blocks.PODZOL.getDefaultState();
    }

    private BlockState getRandomInnerBlock(Random randomSource) {
        float random = randomSource.nextFloat();
        if (random < 0.3F) {
            return Blocks.STONE.getDefaultState();
        } else if (random < 0.5F) {
            return Blocks.COBBLESTONE.getDefaultState();
        } else if (random < 0.7F) {
            return Blocks.MOSSY_COBBLESTONE.getDefaultState();
        } else if (random < 0.83F) {
            return Blocks.MAGMA_BLOCK.getDefaultState();
        }

        return Blocks.DIORITE.getDefaultState();
    }

    private boolean canReplaceBlock(BlockState pState) {
        return !pState.isIn(BlockTags.FEATURES_CANNOT_REPLACE);
    }

    private BlockState getAmethyst(Random randomSource) {
        float random = randomSource.nextFloat();
        if (random <= 0.2F) {
            return Blocks.AMETHYST_CLUSTER.getDefaultState();
//        } else if (random <= 0.2F) {
//            return Blocks.LARGE_AMETHYST_BUD.getDefaultState();
//        } else if (random <= 0.2F) {
//            return Blocks.MEDIUM_AMETHYST_BUD.getDefaultState();
        }

        return Blocks.SMALL_AMETHYST_BUD.getDefaultState();
    }
}
