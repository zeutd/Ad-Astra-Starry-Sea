package com.zeutd.ad_astra_starry_sea.common.menus;

import com.zeutd.ad_astra_starry_sea.common.AASSMenus;
import com.zeutd.ad_astra_starry_sea.common.blockentities.SpaceStationControllerBlockEntity;
import earth.terrarium.adastra.common.menus.base.MachineMenu;
import earth.terrarium.adastra.common.menus.configuration.EnergyConfiguration;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class SpaceStationControllerMenu extends MachineMenu<SpaceStationControllerBlockEntity> {
    public SpaceStationControllerMenu(int id, Inventory inventory, SpaceStationControllerBlockEntity entity) {
        super(AASSMenus.SPACE_STATION_CONTROLLER.get(), id, inventory, entity);
    }

    protected int getContainerInputEnd() {
        return 2;
    }

    protected int getInventoryStart() {
        return 2;
    }

    protected int startIndex() {
        return 1;
    }

    public int getPlayerInvYOffset() {
        return 107;
    }

    protected void addMenuSlots() {
        super.addMenuSlots();
        this.addSlot(new Slot(this.entity, 1, 80, 33));
    }

    protected void addConfigSlots() {
        this.addConfigSlot(new EnergyConfiguration(0, 147, 24, (this.entity).getEnergyStorage()));
    }
}
