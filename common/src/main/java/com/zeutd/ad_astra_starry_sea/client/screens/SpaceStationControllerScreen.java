package com.zeutd.ad_astra_starry_sea.client.screens;

import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.blockentities.SpaceStationControllerBlockEntity;
import com.zeutd.ad_astra_starry_sea.common.menus.SpaceStationControllerMenu;
import earth.terrarium.adastra.client.screens.base.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SpaceStationControllerScreen extends MachineScreen<SpaceStationControllerMenu, SpaceStationControllerBlockEntity> {
    public static final ResourceLocation TEXTURE = AdAstraStarrySea.id("textures/gui/space_station_controller.png");

    public SpaceStationControllerScreen(SpaceStationControllerMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, TEXTURE, IRON_SLOT, 176, 189);
    }
}
