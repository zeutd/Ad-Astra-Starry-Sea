package com.zeutd.ad_astra_starry_sea.forge;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.AASSConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(AdAstraStarrySea.MOD_ID)
@SuppressWarnings("removal")
public final class AdAstraStarrySeaForge {
    public AdAstraStarrySeaForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        //EventBuses.registerModEventBus(AdAstraStarrySea.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        AdAstraStarrySea.init();

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> {
                    ResourcefulConfig config = AdAstraStarrySea.CONFIGURATOR.getConfig(AASSConfig.class);
                    if (config == null) return null;
                    return new ConfigScreen(parent, null, config);
                })
        );
    }
}
