package com.p1nero.tcrcore.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.p1nero.tcrcore.client.gui.TCRMenuBackgroundRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {

    @WrapOperation(
            method = "renderDirtBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V"
            )
    )
    private void tcr$replaceDirtBackground(GuiGraphics guiGraphics,
                                            ResourceLocation texture,
                                            int x, int y, int blitOffset,
                                            float uOffset, float vOffset,
                                            int width, int height,
                                            int textureWidth, int textureHeight,
                                            Operation<Void> original) {
        if (!TCRMenuBackgroundRenderer.isDirtBackground(texture)) {
            original.call(guiGraphics, texture, x, y, blitOffset, uOffset, vOffset,
                    width, height, textureWidth, textureHeight);
            return;
        }

        CreateWorldScreen screen = (CreateWorldScreen) (Object) this;
        TCRMenuBackgroundRenderer.renderRegion(
                guiGraphics, screen.width, screen.height, x, y, width, height);
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"
            )
    )
    private void tcr$replaceFooterSeparator(GuiGraphics guiGraphics,
                                             ResourceLocation texture,
                                             int x, int y,
                                             float uOffset, float vOffset,
                                             int width, int height,
                                             int textureWidth, int textureHeight,
                                             Operation<Void> original) {
        original.call(guiGraphics,
                TCRMenuBackgroundRenderer.replaceDirtSeparator(texture),
                x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }
}
