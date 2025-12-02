package com.zeutd.ad_astra_starry_sea.fabric;

import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import net.fabricmc.api.ModInitializer;

public final class AdAstraStarrySeaFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        AdAstraStarrySea.init();
    }
}
