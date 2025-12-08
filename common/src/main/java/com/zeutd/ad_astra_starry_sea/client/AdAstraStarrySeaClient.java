package com.zeutd.ad_astra_starry_sea.client;

import com.zeutd.ad_astra_starry_sea.client.screens.SpaceStationControllerScreen;
import com.zeutd.ad_astra_starry_sea.common.AASSMenus;
import com.zeutd.ad_astra_starry_sea.mixins.MenuScreensInvoker;

public class AdAstraStarrySeaClient {
    public static void init() {
        registerScreens();
    }

    private static void registerScreens() {
        MenuScreensInvoker.callRegister(AASSMenus.SPACE_STATION_CONTROLLER.get(), SpaceStationControllerScreen::new);
    }
}