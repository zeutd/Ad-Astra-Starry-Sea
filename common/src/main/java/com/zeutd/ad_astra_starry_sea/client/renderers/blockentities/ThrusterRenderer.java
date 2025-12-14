package com.zeutd.ad_astra_starry_sea.client.renderers.blockentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.blockentities.ThrusterBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import org.joml.Quaternionf;

import static com.zeutd.ad_astra_starry_sea.common.blocks.ThrusterBlock.FACING;

@Environment(EnvType.CLIENT)
public class ThrusterRenderer<T extends ThrusterBlockEntity> implements BlockEntityRenderer<T> {
    private static final ResourceLocation texture = AdAstraStarrySea.id("block/thruster_flame");

    public ThrusterRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float f, PoseStack poseStack, MultiBufferSource bufferSource, int i, int j) {
        if (!blockEntity.isLit()) return;
        VertexConsumer vc = bufferSource.getBuffer(RenderType.translucent());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(texture);
        Quaternionf rotation = blockEntity.getBlockState().getValue(FACING).getRotation();
        part(vc, poseStack, sprite, rotation);
    }

    private void part(VertexConsumer vc, PoseStack poseStack, TextureAtlasSprite sprite, Quaternionf rotation){
        float u0 = sprite.getU0(), u1 = sprite.getU1(), v0 = sprite.getV0(), v1 = sprite.getV1();
        poseStack.pushPose();
        //poseStack.mulPose(rotation);
        vc
                .vertex(poseStack.last().pose(),
                        0, 3, 0
                )
                .color(0xffffff)
                .uv(u0, v1)
                .overlayCoords(0)
                .uv2(0)
                .normal(0, 0, 0)
                .endVertex();
        vc
                .vertex(poseStack.last().pose(),
                        1, 3, 0
                )
                .color(0xffffff)
                .uv(u1, v1)
                .overlayCoords(0)
                .uv2(0)
                .normal(0, 0, 0)
                .endVertex();
        vc
                .vertex(poseStack.last().pose(),
                        1, 0, 0
                )
                .color(0xffffff)
                .uv(u1, v0)
                .overlayCoords(0)
                .uv2(0)
                .normal(0, 0, 0)
                .endVertex();
        vc
                .vertex(poseStack.last().pose(),
                        0, 0, 0
                )
                .color(0xffffff)
                .uv(u0, v0)
                .overlayCoords(0)
                .uv2(0)
                .normal(0, 0, 0)
                .endVertex();
        poseStack.popPose();
    }
}
