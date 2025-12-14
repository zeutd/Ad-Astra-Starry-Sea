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
        return 1;
    }

    protected int getInventoryStart() {
        return 1;
    }

    protected int startIndex() {
        return 0;
    }

    public int getPlayerInvYOffset() {
        return 109;
    }

    public int getPlayerInvXOffset(){
        return 27;
    }

    protected void addMenuSlots() {
        super.addMenuSlots();
        this.addSlot(new Slot(this.entity, 1, 116, 33));
    }

    protected void addConfigSlots() {
        this.addConfigSlot(new EnergyConfiguration(0, 182, 24, (this.entity).getEnergyStorage()));
    }
}
