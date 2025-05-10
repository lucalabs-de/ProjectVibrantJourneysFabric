package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CindercaneBlock extends Block {

    public static final IntProperty AGE = Properties.AGE_15;
    public static final int MAX_HEIGHT = 7;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public CindercaneBlock(AbstractBlock.Settings props) {
        super(props);
        this.setDefaultState(this.getStateManager().getDefaultState().with(AGE, 0));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public BlockState getStateForAge(int age) {
        return this.getDefaultState().with(AGE, age);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld level, BlockPos pos, Random rand) {
        if (!state.canPlaceAt(level, pos)) {
            level.removeBlock(pos, true);
        } else if (level.getTimeOfDay() % 300 == 0) {
            this.randomTick(state, level, pos, rand);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random rand) {
        if (level.isAir(pos.up())) {

            int i = 1;

            while (level.getBlockState(pos.down(i)).isOf(this)) {
                i++;
            }

//            for (i = 1; level.getBlockState(pos.down(i)).isOf(this); ++i) ;

            if (i < MAX_HEIGHT && rand.nextInt(3) == 0) {
                int j = state.get(AGE);
                // TODO verify that it's really fine to comment this out
//                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
                if (j == 15) {
                    level.setBlockState(pos.up(), this.getDefaultState());
                    level.setBlockState(pos, this.getStateForAge(0), 4);
                } else {
                    level.setBlockState(pos, this.getStateForAge(j + 1), 4);
                }
//                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, WorldAccess world, BlockPos pos, BlockPos pos2) {
        if (!state.canPlaceAt(world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, direction, state2, world, pos, pos2);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos.down());
        if (blockstate.getBlock() == this) {
            return true;
        } else {
            if (blockstate.isOf(Blocks.NETHERRACK) || blockstate.isOf(Blocks.CRIMSON_NYLIUM)
                    || blockstate.isOf(Blocks.WARPED_NYLIUM) || blockstate.isOf(Blocks.SOUL_SAND)
                    || blockstate.isOf(Blocks.SOUL_SOIL) || blockstate.isOf(Blocks.BASALT)
                    || blockstate.isOf(Blocks.BLACKSTONE)) {
                BlockPos blockpos = pos.down();

                for (Direction direction : Direction.Type.HORIZONTAL) {
                    BlockState blockstate1 = world.getBlockState(blockpos.offset(direction));
                    FluidState fluidstate = world.getFluidState(blockpos.offset(direction));
                    if (fluidstate.isIn(FluidTags.LAVA) || blockstate1.isOf(Blocks.MAGMA_BLOCK)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> container) {
        container.add(AGE);
    }

//    @Override
//    public net.minecraftforge.common.PlantType getPlantType(BlockView world, BlockPos pos) {
//        return PlantType.NETHER;
//    }

//    @Override
//    public BlockState getPlant(BlockView world, BlockPos pos) {
//        return getDefaultState();
//    }
}
