package com.zeutd.ad_astra_starry_sea.client.renderers.blockentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.client.renderers.AASSRenderTypes;
import com.zeutd.ad_astra_starry_sea.common.blockentities.ThrusterBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

import static com.zeutd.ad_astra_starry_sea.common.blocks.ThrusterBlock.FACING;

@Environment(EnvType.CLIENT)
public class ThrusterRenderer<T extends ThrusterBlockEntity> implements BlockEntityRenderer<T> {
    private static final ResourceLocation texture = AdAstraStarrySea.id("block/thruster_flame");

    public ThrusterRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float f, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!blockEntity.isLit()) return;
        VertexConsumer vc = bufferSource.getBuffer(AASSRenderTypes.ITEM_UNLIT_TRANSLUCENT);
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(texture);
        part(vc, poseStack, sprite, blockEntity.getBlockState().getValue(FACING), packedOverlay);
    }

    private void part(VertexConsumer vc, PoseStack poseStack, TextureAtlasSprite sprite, Direction dir, int packedOverlay){
        float u0 = sprite.getU0(), u1 = sprite.getU1(), v0 = sprite.getV0(), v1 = sprite.getV1();
        for (int i = 0; i < 4; i++) {
            poseStack.pushPose();
            if (dir.getAxis() != Direction.Axis.Y) {
                poseStack.translate(0.5f, 0f, 0.5f);
                poseStack.translate(dir.getStepX() * 0.5, dir.getStepY() * 0.5, dir.getStepZ() * 0.5);
                poseStack.translate(dir.getClockWise().getStepX() * 0.5, dir.getClockWise().getStepY() * 0.5, dir.getClockWise().getStepZ() * 0.5);
            }
            else if (dir == Direction.UP) {
                poseStack.translate(0, 1, 1);
            }
            poseStack.mulPose(dir.getRotation());
            poseStack.rotateAround(Axis.YP.rotationDegrees(90f * i), 0.5f, 0f, -0.5f);
            vc
                    .vertex(poseStack.last().pose(),
                            0f, 0.25f, 0f
                    )
                    .color(0xffffffff)
                    .uv(u0, v1)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            1f, 0.25f, 0f
                    )
                    .color(0xffffffff)
                    .uv(u1, v1)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            1f, 3.25f, -0.15f
                    )
                    .color(0xffffffff)
                    .uv(u1, v0)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            0f, 3.25f, -0.15f
                    )
                    .color(0xffffffff)
                    .uv(u0, v0)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            0f, 0.25f, 0f
                    )
                    .color(0xffffff)
                    .uv(u0, v1)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            1f, 0.25f, 0f
                    )
                    .color(0xffffff)
                    .uv(u1, v1)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            1f, 3f, -0.6f
                    )
                    .color(0xffffff)
                    .uv(u1, v0)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            vc
                    .vertex(poseStack.last().pose(),
                            0f, 3f, -0.6f
                    )
                    .color(0xffffff)
                    .uv(u0, v0)
                    .overlayCoords(packedOverlay)
                    .uv2(LightTexture.FULL_BRIGHT)
                    .normal(0, 0, 0)
                    .endVertex();
            poseStack.popPose();
        }
    }
}
