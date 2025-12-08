package com.zeutd.ad_astra_starry_sea.mixins;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MenuScreens.class)
public interface MenuScreensInvoker {
    @Invoker
    public static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void callRegister(MenuType<? extends M> menuType, MenuScreens.ScreenConstructor<M, U> screenConstructor) {

    }
}
