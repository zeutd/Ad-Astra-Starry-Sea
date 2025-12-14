package com.zeutd.ad_astra_starry_sea.client;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import net.minecraft.client.Minecraft;

@Config(AdAstraStarrySea.MOD_ID + "-client")
public final class AASSConfigClient {
    @ConfigEntry(
            type = EntryType.BOOLEAN,
            id = "showSpaceStationControllerArea",
            translation = "Show Space Station Controller Area"
    )
    public static boolean showSpaceStationControllerArea;

    public static void open() {
        ResourcefulConfig config = AdAstraStarrySea.CONFIGURATOR.getConfig(AASSConfigClient.class);
        if (config == null) return;
        Minecraft.getInstance().setScreen(new ConfigScreen(Minecraft.getInstance().screen, null, config));
    }
}
