package com.zeutd.ad_astra_starry_sea.common.blockentities;

import com.zeutd.ad_astra_starry_sea.client.AASSConfigClient;
import com.zeutd.ad_astra_starry_sea.client.AdAstraStarrySeaClient;
import com.zeutd.ad_astra_starry_sea.common.menus.SpaceStationControllerMenu;
import earth.terrarium.adastra.common.blockentities.base.EnergyContainerMachineBlockEntity;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.Configuration;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.ConfigurationEntry;
import earth.terrarium.adastra.common.blockentities.base.sideconfig.ConfigurationType;
import earth.terrarium.adastra.common.config.MachineConfig;
import earth.terrarium.adastra.common.constants.ConstantComponents;
import earth.terrarium.adastra.common.planets.AdAstraData;
import earth.terrarium.botarium.common.energy.impl.InsertOnlyEnergyContainer;
import earth.terrarium.botarium.common.energy.impl.WrappedBlockEnergyContainer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class SpaceStationControllerBlockEntity extends EnergyContainerMachineBlockEntity {
    public static final List<ConfigurationEntry> SIDE_CONFIG = List.of(new ConfigurationEntry(ConfigurationType.ENERGY, Configuration.PULL, ConstantComponents.SIDE_CONFIG_ENERGY));
    public static final long ENERGY_CONSUMPTION = 100;
    public final EnumMap<Direction, Byte> expand = new EnumMap<>(Direction.class);
    public BoundingBox box;
    public SpaceStationControllerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, 2);
        for (Direction value : Direction.values()) {
            expand.put(value, (byte) 1);
        }
    }


    public void setExpand(Map<Direction, Byte> map){
        expand.putAll(map);
    }
    public void setExpand(Byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            expand.put(Direction.from2DDataValue(i), bytes[i]);
        }
    }
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new SpaceStationControllerMenu(id, inventory, this);
    }

    @Override
    public boolean canFunction() {
        return super.canFunction() && AdAstraData.planets().values().stream().anyMatch(p -> p.orbit().isPresent() && p.orbit().get() == getLevel().dimension());
    }

    public WrappedBlockEnergyContainer getEnergyStorage() {
        return this.energyContainer != null ? this.energyContainer : (this.energyContainer = new WrappedBlockEnergyContainer(this, new InsertOnlyEnergyContainer(MachineConfig.ostrumTierEnergyCapacity, MachineConfig.ostrumTierMaxEnergyInOut)));
    }

    @Override
    public void serverTick(ServerLevel level, long time, BlockState state, BlockPos pos) {
        if (this.canFunction()) {
            if (time % 10L == 0L) {
                setLit(getEnergyStorage().internalExtract(ENERGY_CONSUMPTION, true) >= ENERGY_CONSUMPTION);
            }
            getEnergyStorage().extractEnergy(ENERGY_CONSUMPTION, false);
        }
    }

    @Override
    public void clientTick(ClientLevel level, long time, BlockState state, BlockPos pos) {
        if (time % 40 == 0) {
            if (AASSConfigClient.showSpaceStationControllerArea) {
                AdAstraStarrySeaClient.SPACE_STATION_AREA_OVERLAY_RENDERER.removeBox(pos);
                if (AdAstraStarrySeaClient.SPACE_STATION_AREA_OVERLAY_RENDERER.canAdd(pos)
                        && canFunction()) {
                    box = new BoundingBox(
                            pos.getX() + expand.get(Direction.EAST),
                            pos.getY() + expand.get(Direction.UP),
                            pos.getZ() + expand.get(Direction.SOUTH),
                            pos.getX() - expand.get(Direction.WEST),
                            pos.getY() - expand.get(Direction.DOWN),
                            pos.getZ() - expand.get(Direction.NORTH)
                    );
                    AdAstraStarrySeaClient.SPACE_STATION_AREA_OVERLAY_RENDERER.addBox(pos, box);
                }
            } else AdAstraStarrySeaClient.SPACE_STATION_AREA_OVERLAY_RENDERER.clearPositions();
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        for (int i = 0; i < 6; i++) {
            expand.put(Direction.from2DDataValue(i), tag.getByte("Expand" + i));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        for (int i = 0; i < 6; i++) {
            tag.putByte("Expand" + i, expand.get(Direction.from2DDataValue(i)));
        }
    }

    public List<ConfigurationEntry> getDefaultConfig() {
        return SIDE_CONFIG;
    }

    public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
        return new int[]{1};
    }
}
