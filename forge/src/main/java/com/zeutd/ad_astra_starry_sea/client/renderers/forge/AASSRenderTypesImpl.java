package com.zeutd.ad_astra_starry_sea.client.renderers.forge;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeRenderTypes;

public class AASSRenderTypesImpl {
    public static RenderType getUnlitTranslucent(ResourceLocation textureLocation) {
        return ForgeRenderTypes.ITEM_UNLIT_TRANSLUCENT.get();
    }
}
