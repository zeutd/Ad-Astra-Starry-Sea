package com.zeutd.ad_astra_starry_sea.client.renderers;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import org.apache.commons.lang3.NotImplementedException;

@Environment(EnvType.CLIENT)
public class AASSRenderTypes {
    public static final RenderType ITEM_UNLIT_TRANSLUCENT = getUnlitTranslucent(InventoryMenu.BLOCK_ATLAS);

    @ExpectPlatform
    public static RenderType getUnlitTranslucent(ResourceLocation textureLocation) {
        throw new NotImplementedException();
    }
}
