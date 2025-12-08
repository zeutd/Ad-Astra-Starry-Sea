package com.zeutd.ad_astra_starry_sea.fabric.client;

import com.zeutd.ad_astra_starry_sea.client.AdAstraStarrySeaClient;
import net.fabricmc.api.ClientModInitializer;

public final class AdAstraStarrySeaFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AdAstraStarrySeaClient.init();
    }
}
