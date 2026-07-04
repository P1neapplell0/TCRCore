package com.p1nero.tcrcore.mixin.summoningrituals;

import com.almostreliable.summoningrituals.altar.AltarBlockEntity;
import com.almostreliable.summoningrituals.altar.AltarRenderer;
import com.almostreliable.summoningrituals.util.MathUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AltarRenderer.class)
public class AltarRendererMixin {

    @Shadow(remap = false)
    @Final
    private ItemRenderer itemRenderer;

    @Shadow(remap = false)
    private float resetTimer;

    @Shadow(remap = false)
    private double oldCircleOffset;

    /**
     * 移除对距离的判断，为了在ponder里也能用，气笑了
     */
    @Inject(method = "render(Lcom/almostreliable/summoningrituals/altar/AltarBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$render(AltarBlockEntity entity, float partial, PoseStack stack, MultiBufferSource buffer, int light, int overlay, CallbackInfo ci) {
        ci.cancel();
        Minecraft mc = Minecraft.getInstance();
        int MAX_ITEM_HEIGHT = 2;
        int MAX_RESET = 60;
        float MAX_PROGRESS_HEIGHT = 2.5f;
        float HALF = .5f;
        float ITEM_OFFSET = 1.5f;
        if (mc.player == null || entity.getLevel() == null || mc.level == null) {
            return;
        }

        stack.pushPose();

        stack.translate(HALF, 0.8f, HALF);
        stack.scale(HALF, HALF, HALF);

        var lightAbove = LevelRenderer.getLightColor(entity.getLevel(), entity.getBlockPos().above());
        var altarPos = MathUtils.shiftToCenter(MathUtils.vectorFromPos(entity.getBlockPos()));
        var playerPos = mc.player.position();
        var playerAngle = Math.toDegrees(Math.atan2(altarPos.x - playerPos.x, playerPos.z - altarPos.z)) + 180;

        var progress = entity.getProgress();
        var processTime = entity.getProcessTime();

        stack.translate(0, MAX_PROGRESS_HEIGHT * MathUtils.modifier(progress, processTime, 0), 0);

        if (!entity.getInventory().getCatalyst().isEmpty()) {
            stack.pushPose();
            {
                stack.translate(0, 1 - 0.75f * MathUtils.modifier(progress, processTime, 0), 0);
                stack.scale(0.75f, 0.75f, 0.75f);
                stack.mulPose(Axis.YN.rotationDegrees((float) playerAngle));
                itemRenderer
                        .renderStatic(
                                entity.getInventory().getCatalyst(),
                                ItemDisplayContext.FIXED,
                                lightAbove,
                                overlay,
                                stack,
                                buffer,
                                entity.getLevel(),
                                (int) entity.getBlockPos().asLong()
                        );
            }
            stack.popPose();
        }

        //暂停了只能用现实时间了= =
        var time = mc.isPaused() ? System.currentTimeMillis() / 100 % 100000 : mc.level.getGameTime();
        var axisRotation = MathUtils.singleRotation(time);
        var scale = 1 - MathUtils.modifier(progress, processTime, 0);

        if (progress == 0 && resetTimer > 0) {
            scale = 1 - MathUtils.modifier(resetTimer, MAX_RESET, 0);
            resetTimer = Math.max(0, resetTimer - partial);
        }

        stack.scale(scale, scale, scale);

        var inputs = entity.getInventory().getNoneEmptyItems();
        for (var i = 0; i < inputs.size(); i++) {
            stack.pushPose();
            {
                var itemRotation = MathUtils.flipCircle(i * 360f / inputs.size());
                var circleOffset = 0.0;
                if (progress > 0) {
                    circleOffset = MathUtils.modifier(progress, processTime, 1) * 360 * 3 + oldCircleOffset;
                } else {
                    circleOffset = playerAngle;
                    oldCircleOffset = circleOffset;
                }

                var rotationDiff = MathUtils.singleRotation(axisRotation + itemRotation - circleOffset);
                if (rotationDiff > 180) rotationDiff = 360 - rotationDiff;
                var newHeight = (rotationDiff / 180) * MAX_ITEM_HEIGHT;

                var playerOffset = Math.max(1 - altarPos.distanceTo(playerPos) / 8, 0);
                newHeight *= (float) playerOffset;

                stack.mulPose(Axis.YN.rotationDegrees(MathUtils.singleRotation(itemRotation + axisRotation)));
                stack.translate(0, newHeight, -ITEM_OFFSET);

                var item = inputs.get(i);
                if (!item.isEmpty()) {
                    mc.getItemRenderer()
                            .renderStatic(
                                    item,
                                    ItemDisplayContext.FIXED,
                                    lightAbove,
                                    overlay,
                                    stack,
                                    buffer,
                                    entity.getLevel(),
                                    (int) entity.getBlockPos().asLong()
                            );
                }
            }
            stack.popPose();
        }

        if (processTime > 0 && progress >= processTime) {
            resetTimer = MAX_RESET;
        }

        stack.popPose();
    }

}
