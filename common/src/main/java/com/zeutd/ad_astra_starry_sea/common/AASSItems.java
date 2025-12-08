package com.zeutd.ad_astra_starry_sea.common;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import earth.terrarium.adastra.common.registry.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AASSItems {
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, AdAstraStarrySea.MOD_ID);
    public static final RegistryEntry<Item> STEEL_THRUSTER = ITEMS.register("steel_thruster", () -> {
        return new BlockItem((Block) AASSBlocks.STEEL_THRUSTER.get(), new Item.Properties());
    });
    public static final RegistryEntry<Item> SPACE_STATION_CONTROLLER = ITEMS.register("space_station_controller", () -> {
        return new BlockItem((Block) AASSBlocks.SPACE_STATION_CONTROLLER.get(), new Item.Properties());
    });
}
