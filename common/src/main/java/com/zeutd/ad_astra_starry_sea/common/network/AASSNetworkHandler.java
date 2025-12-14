package com.zeutd.ad_astra_starry_sea.common.network;

import com.teamresourceful.resourcefullib.common.network.NetworkChannel;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;

public class AASSNetworkHandler {
    public static final NetworkChannel CHANNEL = new NetworkChannel(AdAstraStarrySea.MOD_ID, 1, "main");

    public static void init() {
        CHANNEL.register(ServerboundSetSpaceStationControllerSelectionAreaPacket.TYPE);
    }
}
