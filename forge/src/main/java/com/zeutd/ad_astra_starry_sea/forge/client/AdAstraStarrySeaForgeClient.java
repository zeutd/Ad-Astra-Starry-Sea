package com.zeutd.ad_astra_starry_sea.forge.client;

import com.zeutd.ad_astra_starry_sea.client.AdAstraStarrySeaClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AdAstraStarrySeaForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(AdAstraStarrySeaClient::init);
        MinecraftForge.EVENT_BUS.addListener(AdAstraStarrySeaForgeClient::onRenderLevelStage);
    }

    private static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            AdAstraStarrySeaClient.render(event.getPoseStack(), event.getCamera());
        }
    }
}
