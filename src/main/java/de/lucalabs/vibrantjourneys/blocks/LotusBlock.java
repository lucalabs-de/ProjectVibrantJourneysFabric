package de.lucalabs.vibrantjourneys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LotusBlock extends LilyPadBlock {

    protected static final VoxelShape BOX = Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 10, 12.0);

    public LotusBlock(Settings props) {
        super(props);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
        return BOX;
    }

    @Override
    public void onEntityCollision(BlockState pState, World pLevel, BlockPos pPos, Entity pEntity) {
    }
}