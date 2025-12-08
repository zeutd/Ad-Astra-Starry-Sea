package com.zeutd.ad_astra_starry_sea.common;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.blockentities.SpaceStationControllerBlockEntity;
import com.zeutd.ad_astra_starry_sea.common.menus.SpaceStationControllerMenu;
import earth.terrarium.adastra.common.menus.base.BaseContainerMenu;
import earth.terrarium.adastra.common.registry.ModMenus;
import earth.terrarium.botarium.common.registry.RegistryHelpers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AASSMenus {
    private static <T extends BaseContainerMenu<E>, E extends BlockEntity> MenuType<T> createMenuType(ModMenus.Factory<T, E> factory, Class<E> clazz) {
        return RegistryHelpers.createMenuType((id, inventory, buf) -> {
            return factory.create(id, inventory, BaseContainerMenu.getBlockEntityFromBuf(inventory.player.level(), buf, clazz));
        });
    }

    public static final ResourcefulRegistry<MenuType<?>> MENUS = ResourcefulRegistries.create(BuiltInRegistries.MENU, AdAstraStarrySea.MOD_ID);
    public static final RegistryEntry<MenuType<SpaceStationControllerMenu>> SPACE_STATION_CONTROLLER = MENUS.register("space_station_controller_menu", () -> createMenuType(SpaceStationControllerMenu::new, SpaceStationControllerBlockEntity.class));
}
