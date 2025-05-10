package de.lucalabs.vibrantjourneys.world.features.ruinednetherportals;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class RuinedPortalDecoratorBase {

  private String name;

  public RuinedPortalDecoratorBase(String name) {
    this.name = name;
  }

  @Nullable
  public abstract BlockState getTopSoil(StructureWorldAccess level, Random random);

  @Nullable
  public abstract BlockState getFillerSoil(StructureWorldAccess level, Random random);

  public abstract void decorate(StructureWorldAccess level, ChunkGenerator generator, Random random, BlockPos groundPos);

  public static final List<RuinedPortalDecoratorBase> PORTAL_DECORATORS = new ArrayList<>();
  public static void registerPortalDecorators() {
    PORTAL_DECORATORS.add(new NetherWastesRuinedPortalDecorator());
    PORTAL_DECORATORS.add(new SoulSandValleyRuinedPortalDecorator());
    PORTAL_DECORATORS.add(new BasaltDeltasRuinedPortalDecorator());
    PORTAL_DECORATORS.add(new CrimsonForestRuinedPortalDecorator());
    PORTAL_DECORATORS.add(new WarpedForestRuinedPortalDecorator());
  }

  public static RuinedPortalDecoratorBase getRandomPortalDecorator(Random random) {
    int luckyNumber = random.nextInt(PORTAL_DECORATORS.size());
    return PORTAL_DECORATORS.get(luckyNumber);
  }

  @Override
  public String toString() {
    return this.name;
  }
}
