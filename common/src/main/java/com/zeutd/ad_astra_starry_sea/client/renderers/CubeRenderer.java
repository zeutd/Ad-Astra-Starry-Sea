package com.zeutd.ad_astra_starry_sea.client.renderers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class CubeRenderer {
    private final int color;
    private final BooleanSupplier config;
    private final Supplier<Block> block;
    private final Map<BlockPos, BoundingBox> boxes = new HashMap<>();

    public CubeRenderer(int color, BooleanSupplier config, Supplier<Block> block) {
        this.color = color;
        this.config = config;
        this.block = block;
    }

    public void addBox(BlockPos pos, BoundingBox box) {
        this.boxes.put(pos, box);
    }

    public void removeBox(BlockPos pos) {
        this.boxes.remove(pos);
    }

    public void clearPositions() {
        this.boxes.clear();
    }

    public boolean canAdd(BlockPos pos) {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.blockPosition().closerThan(pos, 320);
    }

    public void render(PoseStack poseStack, Camera camera) {
        if (this.config.getAsBoolean()) {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                if (level.getGameTime() % 40L == 0L) {
                    this.boxes.keySet().removeIf((pos) -> {
                        return !level.isLoaded(pos) || !this.canAdd(pos) || !level.getBlockState(pos).is((Block)this.block.get());
                    });
                }

                poseStack.pushPose();
                MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
                VertexConsumer consumer = bufferSource.getBuffer(RenderType.debugSectionQuads());
                RenderSystem.polygonOffset(-3.0F, -3.0F);
                RenderSystem.enablePolygonOffset();
                RenderSystem.disableCull();
                RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                poseStack.translate(-camera.getPosition().x(), -camera.getPosition().y(), -camera.getPosition().z());

                boxes.values().forEach(boundingBox -> {
                    Matrix4f matrix = poseStack.last().pose();
                    int minX = boundingBox.minX();
                    int minY = boundingBox.minY();
                    int minZ = boundingBox.minZ();
                    int maxX = boundingBox.maxX() + 1;
                    int maxY = boundingBox.maxY() + 1;
                    int maxZ = boundingBox.maxZ() + 1;

                    consumer.vertex(matrix, (float)minX, (float)minY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)minY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)minY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)minY, (float)maxZ).color(this.color).endVertex();

                    consumer.vertex(matrix, (float)minX, (float)maxY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)maxY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)maxY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)maxY, (float)minZ).color(this.color).endVertex();

                    consumer.vertex(matrix, (float)minX, (float)minY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)maxY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)maxY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)minY, (float)minZ).color(this.color).endVertex();

                    consumer.vertex(matrix, (float)maxX, (float)minY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)maxY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)maxY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)minY, (float)maxZ).color(this.color).endVertex();

                    consumer.vertex(matrix, (float)maxX, (float)minY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)maxY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)maxY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)maxX, (float)minY, (float)maxZ).color(this.color).endVertex();

                    consumer.vertex(matrix, (float)minX, (float)minY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)maxY, (float)maxZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)maxY, (float)minZ).color(this.color).endVertex();
                    consumer.vertex(matrix, (float)minX, (float)minY, (float)minZ).color(this.color).endVertex();
                });


                bufferSource.endBatch();
                RenderSystem.enableCull();
                RenderSystem.polygonOffset(0.0F, 0.0F);
                RenderSystem.disablePolygonOffset();
                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
                poseStack.popPose();
            }
        }
    }
}
