package de.lucalabs.vibrantjourneys.blocks;

import de.lucalabs.vibrantjourneys.registry.PVJBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PinkVinesBlock extends AbstractPlantStemBlock {

    private static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public PinkVinesBlock(AbstractBlock.Settings props) {
        super(props, Direction.DOWN, SHAPE, false, 0);
        this.setDefaultState(this.getStateManager().getDefaultState().with(AGE, 0));
    }

    @Override
    protected Block getPlant() {
        return PVJBlocks.PINK_VINES_PLANT;
    }

    @Override
    protected int getGrowthLength(Random randomSource) {
        return 1;
    }

    @Override
    protected boolean chooseStemState(BlockState p_152998_) {
        return p_152998_.isAir();
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.offset(this.growthDirection.getOpposite());
        BlockState blockstate = pLevel.getBlockState(blockpos);
        return blockstate.isOf(this.getStem()) || blockstate.isOf(this.getPlant()) || blockstate.isIn(BlockTags.LEAVES);
    }

    @Override
    public void randomDisplayTick(BlockState state, World level, BlockPos pos, Random randomSource) {
        super.randomDisplayTick(state, level, pos, randomSource);
        if (randomSource.nextInt(10) == 0) {
            BlockPos blockpos = pos.down();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFullSquare(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                ParticleUtil.spawnParticle(level, pos, randomSource, ParticleTypes.CHERRY_LEAVES);
            }
        }
    }
}