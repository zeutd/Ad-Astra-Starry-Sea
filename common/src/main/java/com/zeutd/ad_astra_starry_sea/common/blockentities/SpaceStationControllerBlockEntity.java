package com.zeutd.ad_astra_starry_sea.common.blockentities;

import com.zeutd.ad_astra_starry_sea.common.menus.SpaceStationControllerMenu;
import earth.terrarium.adastra.common.blockentities.base.EnergyContainerMachineBlockEntity;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.Configuration;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.ConfigurationEntry;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.ConfigurationType;
import earth.terrarium.adastra.common.config.MachineConfig;
import earth.terrarium.adastra.common.constants.ConstantComponents;
import earth.terrarium.botarium.common.energy.impl.ExtractOnlyEnergyContainer;
import earth.terrarium.botarium.common.energy.impl.WrappedBlockEnergyContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpaceStationControllerBlockEntity extends EnergyContainerMachineBlockEntity {
    public static final List<ConfigurationEntry> SIDE_CONFIG = List.of(new ConfigurationEntry(ConfigurationType.ENERGY, Configuration.NONE, ConstantComponents.SIDE_CONFIG_ENERGY));
    public static final long ENERGY_CONSUMPTION = 5;
    public SpaceStationControllerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, 1);
    }

    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new SpaceStationControllerMenu(id, inventory, this);
    }

    public WrappedBlockEnergyContainer getEnergyStorage() {
        return this.energyContainer != null ? this.energyContainer : (this.energyContainer = new WrappedBlockEnergyContainer(this, new ExtractOnlyEnergyContainer(MachineConfig.ironTierEnergyCapacity, MachineConfig.ironTierMaxEnergyInOut)));
    }

    public void serverTick(ServerLevel level, long time, BlockState state, BlockPos pos) {
        if (this.canFunction()) {
            if (time % 10L == 0L) {
                this.setLit(this.energyContainer.extractEnergy(ENERGY_CONSUMPTION, true) >= ENERGY_CONSUMPTION);
            }
        }
    }

    public List<ConfigurationEntry> getDefaultConfig() {
        return SIDE_CONFIG;
    }

    public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
        return new int[]{1};
    }

}
