package de.lucalabs.vibrantjourneys.world.features.stateproviders;

import com.mojang.serialization.Codec;
import dev.orderedchaos.projectvibrantjourneys.core.registry.PVJFeatures;

public class DirectionalStateProvider extends BlockStateProvider {
  public static final Codec<DirectionalStateProvider> CODEC = BlockState.CODEC.fieldOf("state").xmap(AbstractBlock.BlockStateBase::getBlock, Block::defaultBlockState).xmap(DirectionalStateProvider::new, (p_68793_) -> {
    return p_68793_.block;
  }).codec();
  private final Block block;

  public DirectionalStateProvider(Block block) {
    this.block = block;
  }

  protected BlockStateProviderType<?> type() {
    return PVJFeatures.StateProviders.DIRECTIONAL_STATE_PROVIDER;
  }

  public BlockState getState(Random random, BlockPos pos) {
    Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
    return this.block.getDefaultState().with(HorizontalFacingBlock.FACING, dir);
  }
}