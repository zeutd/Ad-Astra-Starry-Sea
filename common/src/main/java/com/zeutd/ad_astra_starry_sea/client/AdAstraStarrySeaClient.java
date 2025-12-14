package com.zeutd.ad_astra_starry_sea.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.client.renderers.CubeRenderer;
import com.zeutd.ad_astra_starry_sea.client.renderers.blockentities.ThrusterRenderer;
import com.zeutd.ad_astra_starry_sea.client.screens.SpaceStationControllerScreen;
import com.zeutd.ad_astra_starry_sea.common.AASSBlockEntityTypes;
import com.zeutd.ad_astra_starry_sea.common.AASSBlocks;
import com.zeutd.ad_astra_starry_sea.common.AASSMenus;
import com.zeutd.ad_astra_starry_sea.mixins.BlockEntityRenderersInvoker;
import com.zeutd.ad_astra_starry_sea.mixins.MenuScreensInvoker;
import net.minecraft.client.Camera;

public class AdAstraStarrySeaClient {
    public static final CubeRenderer SPACE_STATION_AREA_OVERLAY_RENDERER = new CubeRenderer(0x90b9fcff, () -> true, AASSBlocks.SPACE_STATION_CONTROLLER);

    public static void init() {
        AdAstraStarrySea.CONFIGURATOR.registerConfig(AASSConfigClient.class);
        registerScreens();
        registerBlockEntityRenderers();
    }

    private static void registerScreens() {
        MenuScreensInvoker.callRegister(AASSMenus.SPACE_STATION_CONTROLLER.get(), SpaceStationControllerScreen::new);
    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRenderersInvoker.callRegister(AASSBlockEntityTypes.THRUSTER.get(), ThrusterRenderer::new);
    }

    public static void render(PoseStack poseStack, Camera camera){
        SPACE_STATION_AREA_OVERLAY_RENDERER.render(poseStack, camera);
    }
}