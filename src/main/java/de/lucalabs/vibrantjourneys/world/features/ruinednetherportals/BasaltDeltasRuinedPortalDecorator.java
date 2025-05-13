package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import de.lucalabs.vibrantjourneys.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

public class BasaltDeltasRuinedPortalDecorator extends RuinedPortalDecoratorBase {

    public BasaltDeltasRuinedPortalDecorator() {
        super("basalt_deltas_ruined_portal_decorator");
    }

    @Nullable
    @Override
    public BlockState getTopSoil(StructureWorldAccess level, Random random) {
        float oreChance = random.nextFloat();
        if (oreChance < 0.45F) {
            return Blocks.BASALT.getDefaultState();
        } else if (oreChance < 0.9F) {
            return Blocks.BLACKSTONE.getDefaultState();
        }
        return Blocks.MAGMA_BLOCK.getDefaultState();
    }

    @Nullable
    @Override
    public BlockState getFillerSoil(StructureWorldAccess level, Random random) {
        return Blocks.BASALT.getDefaultState();
    }

    @Override
    public void decorate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos groundPos) {
        float chance = random.nextFloat();
        if (chance < 0.8F) {
            if (world.getBlockState(groundPos).isOf(Blocks.BASALT)) {
                int height = 1 + random.nextInt(4);
                for (int i = 1; i <= height; i++) {
                    BlockPos pos = groundPos.up(i);
                    if (WorldUtils.isEmptyOrReplaceable(world, pos)) {
                        world.setBlockState(pos, Blocks.BASALT.getDefaultState(), 2);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
