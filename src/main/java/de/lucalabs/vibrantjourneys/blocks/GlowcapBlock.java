package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class GlowcapBlock extends MushroomPlantBlock {

    public GlowcapBlock(AbstractBlock.Settings props) {
        super(props, null);
        //AbstractBlock.Settings.of(Material.PLANT, MaterialColor.COLOR_YELLOW)
        //				.noCollission()
        //				.randomTicks()
        //				.instabreak()
        //				.sound(SoundType.GRASS).lightLevel((state) -> {
        //		      return 12;
        //		   }), () -> null
    }

    @Override
    public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
        return false;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.isIn(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return true;
        } else {
            return blockstate.isOf(Blocks.FARMLAND); // TODO I think this is what the below line does at the end of the day
//            return blockstate.canSustainPlant(worldIn, blockpos, Direction.UP, this);
        }
    }

    @Override
    public boolean canGrow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isFertilizable(WorldView worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }
}