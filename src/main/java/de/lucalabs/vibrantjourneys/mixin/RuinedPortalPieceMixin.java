package de.lucalabs.vibrantjourneys.mixin;

import de.lucalabs.vibrantjourneys.world.features.ruinednetherportals.RuinedPortalDecoratorBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Function;

@Mixin(RuinedPortalStructurePiece.class)
public abstract class RuinedPortalPieceMixin extends SimpleStructurePiece {

    @Unique
    private final float MODIFY_PORTAL_CHANCE = 1f;

    public RuinedPortalPieceMixin(
            StructurePieceType type,
            NbtCompound tag,
            StructureTemplateManager manager,
            Function<Identifier, StructurePlacementData> settings) {
        super(type, tag, manager, settings);
    }

    @Inject(method = "generate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/structure/RuinedPortalStructurePiece;updateNetherracksInBound(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/WorldAccess;)V",
                    shift = At.Shift.AFTER)
    )
    public void postProcess(
            StructureWorldAccess world,
            StructureAccessor structureAccessor,
            ChunkGenerator generator,
            Random random,
            BlockBox chunkBox,
            ChunkPos chunkPos,
            BlockPos pivot,
            CallbackInfo ci) {
        if (PVJConfig.configOptions.get("enableBetterRuinedNetherPortals")) {
            boolean isInOverworld = world.toServerWorld().getRegistryKey() == World.OVERWORLD;
            boolean shouldGenerate = (1.0F - random.nextFloat() < MODIFY_PORTAL_CHANCE);
            if (isInOverworld && shouldGenerate) {
                RuinedPortalDecoratorBase decorator = RuinedPortalDecoratorBase.getRandomPortalDecorator(random);
                BlockPos.stream(this.getBoundingBox().expand(5)).forEach((pos) -> {
                    if (world.getBlockState(pos).isOf(Blocks.NETHERRACK) || world.getBlockState(pos).isOf(Blocks.MAGMA_BLOCK)) {
                        boolean isAboveEmpty = world.isAir(pos.up());
                        if (isAboveEmpty) {
                            BlockState topSoil = decorator.getTopSoil(world, random);
                            if (topSoil != null) {
                                world.setBlockState(pos, topSoil, 2);
                            }
                            decorator.decorate(world, generator, random, pos);
                        } else {
                            BlockState fillerSoil = decorator.getFillerSoil(world, random);
                            if (fillerSoil != null) {
                                world.setBlockState(pos, fillerSoil, 2);
                            }
                        }
                    }
                });
            }
        }
    }


}
