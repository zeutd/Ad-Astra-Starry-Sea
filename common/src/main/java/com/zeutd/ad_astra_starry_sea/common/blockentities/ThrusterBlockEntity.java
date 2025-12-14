package com.zeutd.ad_astra_starry_sea.common.blockentities;

import earth.terrarium.adastra.common.blockentities.base.MachineBlockEntity;
import earth.terrarium.adastra.common.config.MachineConfig;
import earth.terrarium.adastra.common.tags.ModFluidTags;
import earth.terrarium.botarium.common.fluid.FluidApi;
import earth.terrarium.botarium.common.fluid.FluidConstants;
import earth.terrarium.botarium.common.fluid.base.BotariumFluidBlock;
import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.impl.InsertOnlyFluidContainer;
import earth.terrarium.botarium.common.fluid.impl.WrappedBlockFluidContainer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static com.zeutd.ad_astra_starry_sea.common.blocks.ThrusterBlock.FACING;

public class ThrusterBlockEntity extends MachineBlockEntity implements BotariumFluidBlock<WrappedBlockFluidContainer> {
    public ThrusterBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    private WrappedBlockFluidContainer fluidContainer;

    @Override
    public WrappedBlockFluidContainer getFluidContainer() {
        if (fluidContainer != null) return fluidContainer;
        return fluidContainer = new WrappedBlockFluidContainer(
                this,
                new InsertOnlyFluidContainer(
                        i -> FluidConstants.fromMillibuckets(MachineConfig.steelTierFluidCapacity),
                        1,
                        (tank, holder) -> holder.is(ModFluidTags.FUEL))
        );
    }


    public void tickSideInteractions(BlockPos pos) {
        //TransferUtils.pullFluidNearby(this, pos, getFluidContainer(), FluidConstants.fromMillibuckets(200), 0, sideConfig.get(4), filter);
        for (var dir : Direction.values()) {
            BlockEntity nearbyEntity = level().getBlockEntity(pos.relative(dir));
            if (nearbyEntity == null) continue;
            if (!FluidContainer.holdsFluid(nearbyEntity, dir)) continue;
            FluidContainer nearbyContainer = FluidContainer.of(nearbyEntity, dir.getOpposite());
            if (nearbyContainer == null) continue;
            FluidHolder holder = nearbyContainer.getFluids().get(0);
            if (holder.isEmpty()) continue;
            if (FluidApi.moveFluid(nearbyContainer, getFluidContainer(), FluidHolder.ofMillibuckets(holder.getFluid(), FluidConstants.fromMillibuckets(200)), false) > 0) {
                sync();
            }
        }
    }

    @Override
    public void internalServerTick(ServerLevel level, long time, BlockState state, BlockPos pos) {
        super.internalServerTick(level, time, state, pos);
        tickSideInteractions(pos);
    }

    @Override
    public void clientTick(ClientLevel level, long time, BlockState state, BlockPos pos) {
        super.clientTick(level, time, state, pos);
        Direction dir = state.getValue(FACING);
        Vec3 p = pos.getCenter().relative(dir, 0.6);
        level.addParticle(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                p.x,
                p.y,
                p.z,
                dir.getStepX() * 0.5 + level.random.nextGaussian() * 0.03,
                dir.getStepY() * 0.5 + level.random.nextGaussian() * 0.03,
                dir.getStepZ() * 0.5 + level.random.nextGaussian() * 0.03
        );
    }


}
