package com.zeutd.ad_astra_starry_sea;

import com.mojang.logging.LogUtils;
import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import com.zeutd.ad_astra_starry_sea.common.*;
import com.zeutd.ad_astra_starry_sea.common.network.AASSNetworkHandler;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class AdAstraStarrySea {
    public static final String MOD_ID = "ad_astra_starry_sea";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Configurator CONFIGURATOR = new Configurator();
    public static ResourceLocation id(String path){
        return new ResourceLocation(MOD_ID, path);
    }
    public static void init() {
        // Write common init code here.
        CONFIGURATOR.registerConfig(AASSConfig.class);
        AASSNetworkHandler.init();
        AASSBlocks.BLOCKS.init();
        AASSItems.ITEMS.init();
        AASSCreativeTab.TABS.init();
        AASSBlockEntityTypes.BLOCK_ENTITY_TYPES.init();
        AASSMenus.MENUS.init();
    }
}
