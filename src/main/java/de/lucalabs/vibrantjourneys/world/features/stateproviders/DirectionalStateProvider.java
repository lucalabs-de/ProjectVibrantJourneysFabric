package de.lucalabs.vibrantjourneys.world.features.stateproviders;

import com.mojang.serialization.Codec;
import de.lucalabs.vibrantjourneys.registry.PVJFeatures;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;

public class DirectionalStateProvider extends BlockStateProvider {
    public static final Codec<DirectionalStateProvider> CODEC =
            BlockState.CODEC
                    .fieldOf("state")
                    .xmap(AbstractBlock.AbstractBlockState::getBlock, Block::getDefaultState)
                    .xmap(DirectionalStateProvider::new, (p_68793_) -> p_68793_.block).codec();

    private final Block block;

    public DirectionalStateProvider(Block block) {
        this.block = block;
    }

    @Override
    protected BlockStateProviderType<?> getType() {
        return PVJFeatures.StateProviders.DIRECTIONAL_STATE_PROVIDER;
    }

    @Override
    public BlockState get(Random random, BlockPos pos) {
        Direction dir = Direction.Type.HORIZONTAL.random(random);
        return this.block.getDefaultState().with(HorizontalFacingBlock.FACING, dir);
    }
}