package com.zeutd.ad_astra_starry_sea;

import com.zeutd.ad_astra_starry_sea.common.AASSBlocks;
import com.zeutd.ad_astra_starry_sea.common.AASSCreativeTab;
import com.zeutd.ad_astra_starry_sea.common.AASSItems;
import net.minecraft.resources.ResourceLocation;

public final class AdAstraStarrySea {
    public static final String MOD_ID = "ad_astra_starry_sea";

    public static ResourceLocation id(String path){
        return new ResourceLocation(MOD_ID, path);
    }
    public static void init() {
        // Write common init code here.
        AASSBlocks.BLOCKS.init();
        AASSItems.ITEMS.init();
        AASSCreativeTab.TABS.init();
    }
}
