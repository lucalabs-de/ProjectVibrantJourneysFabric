package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

public class NetherWastesRuinedPortalDecorator extends RuinedPortalDecoratorBase {

    public NetherWastesRuinedPortalDecorator() {
        super("nether_wastes_ruined_portal_decorator");
    }

    @Nullable
    @Override
    public BlockState getTopSoil(StructureWorldAccess level, Random random) {
        float oreChance = random.nextFloat();
        if (oreChance <= 0.03F) {
            return Blocks.NETHER_QUARTZ_ORE.getDefaultState();
        } else if (oreChance <= 0.05F) {
            return Blocks.NETHER_GOLD_ORE.getDefaultState();
        }
        return null;
    }

    @Nullable
    @Override
    public BlockState getFillerSoil(StructureWorldAccess level, Random random) {
        float oreChance = random.nextFloat();
        if (oreChance <= 0.03F) {
            return Blocks.NETHER_QUARTZ_ORE.getDefaultState();
        } else if (oreChance <= 0.05F) {
            return Blocks.NETHER_GOLD_ORE.getDefaultState();
        }
        return null;
    }

    @Override
    public void decorate(StructureWorldAccess level, ChunkGenerator generator, Random random, BlockPos groundPos) {
    }
}
