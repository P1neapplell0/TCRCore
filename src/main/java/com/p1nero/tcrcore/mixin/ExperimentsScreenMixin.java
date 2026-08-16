package com.p1nero.tcrcore.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.p1nero.tcrcore.client.gui.TCRMenuBackgroundRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.worldselection.ExperimentsScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ExperimentsScreen.class)
public abstract class ExperimentsScreenMixin {

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"
            )
    )
    private void tcr$replaceDirtBackground(GuiGraphics guiGraphics,
                                            ResourceLocation texture,
                                            int x, int y,
                                            float uOffset, float vOffset,
                                            int width, int height,
                                            int textureWidth, int textureHeight,
                                            Operation<Void> original) {
        if (!TCRMenuBackgroundRenderer.isDirtBackground(texture)) {
            original.call(guiGraphics, texture, x, y, uOffset, vOffset,
                    width, height, textureWidth, textureHeight);
            return;
        }

        // renderBackground has already drawn the full cover before this dirt layer.
    }
}
