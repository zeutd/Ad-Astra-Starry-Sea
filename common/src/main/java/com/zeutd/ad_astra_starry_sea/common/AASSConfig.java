package com.zeutd.ad_astra_starry_sea.common;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigButton;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.client.AASSConfigClient;

@Config(AdAstraStarrySea.MOD_ID)
public final class AASSConfig {
    @ConfigButton(text = "Client Config", translation = "config.ad_astra.clientConfig")
    public static void clientConfig() {
        AASSConfigClient.open();
    }
}
