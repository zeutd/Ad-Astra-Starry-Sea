package com.zeutd.ad_astra_starry_sea.client.components;

import com.teamresourceful.resourcefulconfig.client.options.NumberInputBox;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import it.unimi.dsi.fastutil.bytes.ByteConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.regex.Pattern;

public class ExpandNumberInputBox extends NumberInputBox {
    private static final Pattern INTEGER_PATTERN = Pattern.compile("\\d*");

    private final static ResourceLocation INPUT_BOX_TEXTURE = AdAstraStarrySea.id("textures/gui/expand_input_box.png");

    public ExpandNumberInputBox(Font font, int x, int y, int width, int height, String value, ByteConsumer consumer) {
        super(font, x, y, width, height, value, false, l -> consumer.accept((byte) l), null);
        setFilter((text) -> {
            if (INTEGER_PATTERN.matcher(text).matches()){
                byte b;
                try {
                    b = Byte.parseByte(text);
                }
                catch (NumberFormatException ignored){
                    return false;
                }
                return b <= 30 && b >= 0;
            }
            return false;
        });
    }

    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        super.renderWidget(guiGraphics, i, j, f);
        guiGraphics.blit(INPUT_BOX_TEXTURE, getX() - 4, this.getY() - 6, 0.0F, 0.0F, 28, 24, 28, 24);
    }
}
