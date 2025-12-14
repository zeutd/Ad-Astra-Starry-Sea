package com.zeutd.ad_astra_starry_sea.client.screens;

import com.teamresourceful.resourcefulconfig.client.options.NumberInputBox;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.client.AASSConfigClient;
import com.zeutd.ad_astra_starry_sea.client.components.ExpandNumberInputBox;
import com.zeutd.ad_astra_starry_sea.common.AASSConstantComponents;
import com.zeutd.ad_astra_starry_sea.common.blockentities.SpaceStationControllerBlockEntity;
import com.zeutd.ad_astra_starry_sea.common.menus.SpaceStationControllerMenu;
import com.zeutd.ad_astra_starry_sea.common.network.AASSNetworkHandler;
import com.zeutd.ad_astra_starry_sea.common.network.ServerboundSetSpaceStationControllerSelectionAreaPacket;
import earth.terrarium.adastra.client.components.PressableImageButton;
import earth.terrarium.adastra.client.components.machines.OptionsBarWidget;
import earth.terrarium.adastra.client.screens.base.MachineScreen;
import earth.terrarium.adastra.client.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.EnumMap;

public class SpaceStationControllerScreen extends MachineScreen<SpaceStationControllerMenu, SpaceStationControllerBlockEntity> {
    public static final ResourceLocation TEXTURE = AdAstraStarrySea.id("textures/gui/space_station_controller.png");
    //public EnumMap<Direction, Byte> expand = new EnumMap<>(Direction.class);
    public EnumMap<Direction, NumberInputBox> expandInput = new EnumMap<>(Direction.class);
    public SpaceStationControllerScreen(SpaceStationControllerMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, TEXTURE, IRON_SLOT, 212, 189);
    }

    @Override
    public OptionsBarWidget.Builder createOptionsBar() {
        return super.createOptionsBar()
                .addElement(0, createSpaceStationControllerAreaShowMode());
    }

    protected void init() {
        super.init();
        for (int i = 0; i < Direction.values().length; i++) {
            Direction dir = Direction.values()[i];
            expandInput.put(dir, addRenderableWidget(
                    new ExpandNumberInputBox(Minecraft.getInstance().font,
                            this.leftPos + 25 + (i / 3) * 32,
                            this.topPos + 22 + (i % 3) * 26,
                            20,
                            12,
                            String.valueOf(entity.expand.getOrDefault(dir, (byte) 0)),
                            value -> {
                                entity.expand.put(dir, value);
                                AASSNetworkHandler.CHANNEL.sendToServer(new ServerboundSetSpaceStationControllerSelectionAreaPacket(this.entity.getBlockPos(), ServerboundSetSpaceStationControllerSelectionAreaPacket.pack(entity.expand.values().toArray(Byte[]::new))));
                            }
            )));
            addRenderableWidget(new StringWidget(
                    this.leftPos + 25 + (i / 3) * 32 + (i >= 3 ? 20 : -20),
                    this.topPos + 22 + (i % 3) * 26,
                    20,
                    12,
                    Component.literal(dir.getAxis().toString() + (dir.getAxisDirection() == Direction.AxisDirection.POSITIVE ? '+' : '-')),
                    Minecraft.getInstance().font)
            );
            //AdAstraStarrySea.LOGGER.info(String.valueOf(expandInput.values().size()));
        }
    }

    public static PressableImageButton createSpaceStationControllerAreaShowMode() {
        return new PressableImageButton(0, 0, 18, 18, 0, 0, 18, (AASSConfigClient.showSpaceStationControllerArea ? GuiUtils.SHOW_BUTTON : GuiUtils.HIDE_BUTTON), 18, 54,
                button -> {
                    AASSConfigClient.showSpaceStationControllerArea = !AASSConfigClient.showSpaceStationControllerArea;
                    Minecraft.getInstance().tell(() -> AdAstraStarrySea.CONFIGURATOR.saveConfig(AASSConfigClient.class));
                    ((PressableImageButton) button).setTexture(AASSConfigClient.showSpaceStationControllerArea ? GuiUtils.SHOW_BUTTON : GuiUtils.HIDE_BUTTON);
                },
                AASSConstantComponents.SPACE_STATION_CONTROLLER_AREA
        );
    }
}
