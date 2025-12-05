package com.zeutd.ad_astra_starry_sea.common;

import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeTab;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import earth.terrarium.adastra.common.registry.ModCreativeTab;
import earth.terrarium.adastra.common.registry.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class AASSCreativeTab {
    public static final ResourcefulRegistry<CreativeModeTab> TABS;
    public static final Supplier<CreativeModeTab> TAB;
    static {
        TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, AdAstraStarrySea.MOD_ID);
        TAB = (new ResourcefulCreativeTab(new ResourceLocation(AdAstraStarrySea.MOD_ID, "main"))).setItemIcon(AASSItems.STEEL_THRUSTER).addRegistry(AASSItems.ITEMS).build();
    }
}
