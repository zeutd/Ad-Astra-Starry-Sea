package com.zeutd.ad_astra_starry_sea.client.renderers.fabric;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class AASSRenderTypesImpl {
    public static RenderType getUnlitTranslucent(ResourceLocation textureLocation) {
        return RenderType.beaconBeam(InventoryMenu.BLOCK_ATLAS, true);
    }
}
