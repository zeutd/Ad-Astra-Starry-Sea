package com.zeutd.ad_astra_starry_sea.forge;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import net.minecraftforge.fml.common.Mod;

@Mod(AdAstraStarrySea.MOD_ID)
@SuppressWarnings("removal")
public final class AdAstraStarrySeaForge {
    public AdAstraStarrySeaForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        //EventBuses.registerModEventBus(AdAstraStarrySea.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        AdAstraStarrySea.init();
    }
}
